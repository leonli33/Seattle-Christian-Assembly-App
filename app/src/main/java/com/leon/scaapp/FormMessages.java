package com.leon.scaapp;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

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

public class FormMessages extends AppCompatActivity {
    public ArrayList<Object> messages;
    public ArrayList<String> Speaker ;
    public ArrayList<String> Date;
    public ArrayList<String> Title ;
    public ArrayList<String> URLs;
    MessageAdapter adapter;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.message_item);

        messages = new ArrayList<Object>();
        Speaker = new ArrayList<String>();
        Date = new ArrayList<String>();
        Title = new ArrayList<String>();
        URLs = new ArrayList<String>();

        final CountDownLatch latch = new CountDownLatch(1);

        for (int i=0;i<1;i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String address = "http://www.seattlechristianassembly.org/sunday_msgs.html";
                    try {
                        Document doc = null;
                        try {
                            doc = Jsoup.connect(address).timeout(10 * 1000).get();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                        Elements title = doc.select("div.messagesubject");
                        for (Element el : title)
                        {
                            Title.add(el.text());
                        }
                        Elements Author = doc.select("div.messagespeaker");
                        for (Element el : Author)
                        {
                            Speaker.add(el.text());
                        }
                        Elements date = doc.select("div.messagedate");
                        for (Element el : date)
                        {
                            Date.add(el.text());
                        }
                        URL pageLocation = new URL(address);
                        try {
                            try (Scanner in = new Scanner(pageLocation.openStream())) {

                                while (in.hasNext()) {
                                    String line;
                                    line = in.nextLine();
                                    if (line.contains("href=\"http://")) {
                                        String[] linksplit;
                                        String[] secondlinksplit;
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
                    }
                    catch (MalformedURLException e)
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
            for(int i=1;i<Date.size();i++)
            {
                Message message = new Message(Speaker.get(i),Title.get(i),Date.get(i));
                messages.add(message);
            }
            adapter = new MessageAdapter(this, messages,URLs,Title,4);
            lv = findViewById(R.id.listview1);
            lv.setAdapter(adapter);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
