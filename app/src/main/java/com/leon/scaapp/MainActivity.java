package com.leon.scaapp;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Scanner;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  {
    ImageView myImageView;
    Animation animationFadeIn;
    int[] images = new int[]{R.drawable.grace_black_actual,R.drawable.theme_verse_black_actual,R.drawable.receives_black,R.drawable.for_god_black,R.drawable.isaiah_black,R.drawable.matt_black,
    R.drawable.jer_black,R.drawable.prov_black};
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        setContentView(R.layout.activity_main);
        final Button buttonEnglish;
        myImageView = findViewById(R.id.imageView);
        buttonEnglish = findViewById(R.id.buttonBegin);
        buttonEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ChooseMessageType.class);
                startActivity(myIntent);
            }
        });
        h();
    }

    public void h()
    {

        if(i < images.length)
        {
            Animation img = new AlphaAnimation(1.00f,0.00f);
            img.setDuration(5000);
            img.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    myImageView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    myImageView.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            myImageView.startAnimation(img);
            myImageView.setImageResource(images[i]);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    h();
                }
            }, 5000);
            i++;
        }
        else if(i >= images.length)
        {
            i = 0;
            h();
        }

    }
    public void updateImage()
    {
        if(i < images.length)
        {
            myImageView.setImageResource(images[i]);
        }
        i++;
    }

}
