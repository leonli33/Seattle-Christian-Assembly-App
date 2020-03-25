package com.leon.scaapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

public class ChooseMessageType extends AppCompatActivity {
    Button SundayMessages,WCCCMessages,GodwinSunMessages,BibleStudyMessages,SpecialMeetingMessages,StephenKuangMessages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.choose_message_type);


        SundayMessages = findViewById(R.id.buttonSundayMessages);
        WCCCMessages = findViewById(R.id.buttonWCCC);
        GodwinSunMessages = findViewById(R.id.buttonGodwinMessages);
        BibleStudyMessages = findViewById(R.id.buttonBibleStudy);
        SpecialMeetingMessages = findViewById(R.id.buttonSpecialMeeting);
        StephenKuangMessages = findViewById(R.id.buttonStephenKuang);

        WCCCMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newIntent = new Intent(ChooseMessageType.this,WCCCMessages.class);
                startActivity(newIntent);
            }
        });
        SundayMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(ChooseMessageType.this,FormMessages.class);
                startActivity(newIntent);
            }
        });
        GodwinSunMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(ChooseMessageType.this,godwin_sun_Messages.class);
                startActivity(newIntent);
            }
        });
        SpecialMeetingMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent newIntent = new Intent(ChooseMessageType.this,SpecialMeetingMessages.class);
                startActivity(newIntent);
            }
        });
        StephenKuangMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(ChooseMessageType.this,StephenKuangMessages.class);
                startActivity(newIntent);
            }
        });
        BibleStudyMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(ChooseMessageType.this,BibleStudyMessages.class);
                startActivity(newIntent);
            }
        });
    }
}
