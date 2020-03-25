package com.leon.scaapp;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class OptionFromOne extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.optionform1);
        final Button ButtonMessages,buttonMeetingSchedule,buttonContact;
        buttonMeetingSchedule = findViewById(R.id.buttonSchedule);
        ButtonMessages =findViewById(R.id.buttonMessages);
        buttonContact = findViewById(R.id.buttonContact);

        buttonMeetingSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(OptionFromOne.this,ScheduleActivity.class);
                startActivity(newIntent);
                //ScheduleActivity activity = new ScheduleActivity();

            }
        });
        ButtonMessages.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.seattlechristianassembly.org/sunday_msgs.html"));
//                startActivity(browserIntent);
                Intent myIntent = new Intent(OptionFromOne.this, ChooseMessageType.class);
                //ChooseMessageType
                startActivity(myIntent);
            }
        });
        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(OptionFromOne.this, Contact_Activity.class);
                startActivity(myIntent);
            }
        });

    }


}
