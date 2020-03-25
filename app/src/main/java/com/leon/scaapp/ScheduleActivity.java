package com.leon.scaapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ScheduleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formschedule);

//    @Override
//    protected String doInBackground(String...urls) {
//        String total = "";
//        String address = "http://www.seattlechristianassembly.org/sunday_msgs.html";
//        try {
//            URL pageLocation = new URL(address);
//            try
//            {
//                try (Scanner in = new Scanner(pageLocation.openStream()))
//                {
//                    while(in.hasNext())
//                    {
//                        String line = in.next();
//                        if(line.contains("href=\"http://"))
//                        {
//                            int from = line.indexOf("\"");
//                            int to = line.lastIndexOf("\"");
//                            total += line.substring(from,to);
//                            //System.out.println(line.substring(from,to));
//                        }
//                        return total;
//                    }
//                }
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return null;
     }

}
