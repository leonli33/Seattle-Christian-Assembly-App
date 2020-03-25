package com.leon.scaapp;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.rtp.AudioStream;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Offline_Activities extends AppCompatActivity {
    Button buttonPlay;
    FileInputStream fis = null;
    private boolean playpause;
    private boolean initialstage = true;
    public ProgressDialog dlg;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //import java.io.*;
        //import sun.audio.*;
        setContentView(R.layout.offline_messages);
//        buttonPlay = findViewById(R.id.buttonPlayOffline);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        //import javafx.scene.media.Media;
        //import javafx.scene.media.MediaPlayer;

//        buttonPlay.setOnClickListener(new View.OnClickListener() {
//            InputStream in;
//            @Override
//            public void onClick(View v) {
//                try
//                {
//                    if(!playpause)
//                    {
//                        buttonPlay.setText("Pause");
//
//                        if(initialstage)
//                        {
//                            new Player().execute(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Song of the Sons of Korah - Psalms 87");
//                            //Links.getindexofLink
//                        }
//                        //
//                        else
//                        {
//                            if(!mediaPlayer.isPlaying())
//                            {
//
//                                mediaPlayer.start();
//                            //    playCycle();
//
//                            }
//                        }
//                        playpause = true;
//                    }
//                    else
//                    {
//                        buttonPlay.setText("Resume");
//
//                        if(mediaPlayer.isPlaying())
//                        {
//                            mediaPlayer.pause();
//                        }
//                        playpause = false;
//                    }
//
//
//
////                    in = new FileInputStream(new File("content://downloads/my_downloads/1343"));
////                    AudioStream audio =  new AudioStream(in);
////                    File file = new File("/storage/emulated/0/Download/Hello");
////                    String l = file.getAbsolutePath();
////                    String y = file.getPath();
//
//
//    //                fis = new FileInputStream("/storage/emulated/0/Download/Hello");
//
//                    //2018_07_08_Three Basic Aspects of the Christian Walk_Godwin Sun.mp3
////                    mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/<Song of the Sons of Korah - Psalms 87>");
//                    //fis.getFD()
//
//  //                  mediaPlayer.prepare();
//
//                    if(fis != null)
//                    {
//                        fis.close();
//                    }
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mediaPlayer.start();
//            }
//        });


    }
//    class Player extends AsyncTask<String,Void,Boolean>
//    {
//
//        @Override
//        protected Boolean doInBackground(String... strings) {
//            boolean prepared = false;
//            try
//            {
//                mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Song of the Sons of Korah - Psalms 87");
//                //Links.get(indexOfLink)
//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        initialstage = true;
//                        playpause = false;
//                        buttonPlay.setText("Play Again");
//                        mediaPlayer.stop();
//                        mediaPlayer.reset();
//                    }
//                });
//                mediaPlayer.prepare();
//                prepared = true;
//            }
//            catch(Exception e)
//            {
//                Log.e("MyAudioStringApp",e.getMessage());
//            }
//
//            return prepared;
//        }
//        @Override
//        protected void onPostExecute(Boolean aBoolean)
//        {
//            super.onPostExecute(aBoolean);
//
//            if(dlg.isShowing())
//            {
//                dlg.cancel();
//            }
//            mediaPlayer.start();
//            initialstage = false;
//        }
//        @Override
//        protected  void onPreExecute()
//        {
//            super.onPreExecute();
//            dlg.setMessage("Buffering...");
//            dlg.show();
//        }
//    }

}
