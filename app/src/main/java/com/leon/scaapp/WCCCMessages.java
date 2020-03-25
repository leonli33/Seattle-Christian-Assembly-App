package com.leon.scaapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class WCCCMessages extends AppCompatActivity {

    public ArrayList<String> Titles;
    public ArrayList<Message> messages;
    public ArrayList<String> Speaker ;
    public ArrayList<String> Date;
    public ArrayList<String> Title ;
    ArrayList<Integer> positions = new ArrayList<Integer>();
    ArrayList<String> confName = new ArrayList<String>();
    ArrayList<String> confTitle = new ArrayList<String>();
    ArrayList<String> URLs;
    ArrayList<String> ActualURLList;
    MessageAdapter adapter;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.message_item);
        URLs = new ArrayList<String>();
        ActualURLList = new ArrayList<String>();
        messages = new ArrayList<Message>();
        Speaker = new ArrayList<String>();
        Date = new ArrayList<String>();
        Title = new ArrayList<String>();
        Titles = new ArrayList<String>();
        final CountDownLatch latch = new CountDownLatch(1);

        for (int i=0;i<1;i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String address2 = "http://www.seattlechristianassembly.org/wccc_msgs.html";
                    try
                    {
                        Document docs = null;
                        try
                        {
                            docs = Jsoup.connect(address2).timeout(10 * 1000).get();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                        Elements title = docs.select("div.messagesubject");
                        for (Element el : title)
                        {
                            Title.add(el.text());

                        }
                        Elements Author = docs.select("div.messagespeaker");
                        for (Element el : Author)
                        {
                            Speaker.add(el.text());
                        }
                        Elements date = docs.select("div.messagedate");
                        for (Element el : date)
                        {
                            Date.add(el.text());
                        }
                        Elements confNames = docs.select("div.confname");
                        Elements conferences = docs.select("div.confdiv");
                        String div;
                        URL pageLocation = new URL("http://www.seattlechristianassembly.org/wccc_msgs.html");
                        try {
                            try (Scanner in = new Scanner(pageLocation.openStream())) {

                                while (in.hasNext()) {
                                    String line;
                                    line = in.nextLine();
                                    if (line.contains("href=\"http://")) {
                                        String[] linksplit;
                                        String[] secondlinksplit;

//                                        int from = line.indexOf("\"");
//                                        int to = line.lastIndexOf("\"");

                                        linksplit = line.split("http");
                                        secondlinksplit = linksplit[1].split("\"");
                                        URLs.add("http" + secondlinksplit[0]);
                                    }

                                }
                            }
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }


                        for (int j =0;j<confNames.size();j++)
                        {
                            Elements hasTitle;

                            if( (hasTitle = conferences.get(j).select("div[class=conftitle]")) != null && !conferences.get(j).select("div[class=conftitle]").text().equals("") )
                            {

                                div = conferences.get(j).select("div[class=conftitle]").text();
                                confName.add(confNames.get(j).text());
                                confTitle.add(" | " +div);
                            }
                            else if((hasTitle = conferences.get(j).select("div[class=conftitle]")) == null || conferences.get(j).select("div[class=conftitle]").text().equals("") )
                            {
                                confName.add(confNames.get(j).text());
                                confTitle.add(" ");
                            }
                        }
                        int position = 0;
                        try {
                            URL pageLocation2 = new URL(address2);
                            try {
                                try (Scanner in = new Scanner(pageLocation2.openStream()))
                                {
                                    while(in.hasNext())
                                    {
                                        String line = in.next();
                                        if(line.contains("class=\"messagediv"))
                                        {
                                            position++;
                                        }
                                        if(line.contains("class=\"confdiv"))
                                        {
                                            positions.add(position + 1);
                                        }
                                    }
                                }
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    } catch (MalformedURLException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        latch.countDown();
                    }
                }
            });
            thread.start();
        }
        try
        {
            latch.await();
            lv = findViewById(R.id.listview1);
            Titles.add("1");
            MessageAdapter adapter  = new MessageAdapter(this,getMessage(),ActualURLList,Titles,0);
            //adapter.setMessageLinks(ActualURLList);

            lv.setAdapter(adapter);


        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    private ArrayList<Object> getMessage() throws IOException {
        ArrayList<Object> allmessages = new ArrayList<Object>();
        int varToUseForConfNames = 0;
        int i= 0;
        for(int j =0; j< positions.size();j++)
        {
            for(;i<positions.get(j);i++)
            {
                Message message = new Message(Speaker.get(i), Title.get(i), Date.get(i));
                if(i>0)
                {
                    Titles.add(Title.get(i));
                    ActualURLList.add(URLs.get(i-1));
                    //ActualURLList.add(URLs.get(i));
                }

                allmessages.add(message);
                if(i == positions.get(j) - 1 )
                {
                    Titles.add(" ");
                    ActualURLList.add(" ");
                    allmessages.add("  " + confName.get(varToUseForConfNames)  + confTitle.get(varToUseForConfNames));
                    varToUseForConfNames++;
                }
            }
            i = positions.get(j);
        }
        int totalSize = Date.size();
        for (; i < totalSize; i++)
        {
            Message message = new Message(Speaker.get(i), Title.get(i), Date.get(i));
            allmessages.add(message);
            ActualURLList.add(URLs.get(i-1));
            Titles.add(Title.get(i));
        }
//        ActualURLList.add(URLs.get(last - 1));


        allmessages.remove(0);
        return allmessages;
    }
}
