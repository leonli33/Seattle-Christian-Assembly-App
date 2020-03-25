package com.leon.scaapp;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Listen_To_Message extends AppCompatActivity {
    SeekBar seekbar;
    Handler handler;
    Runnable runnable;
    TextView times,LabelTitle;
    Button buttonPlay ;
    DownloadManager dm;

    int progressGlobal;
    long queueid;
    int indexoflink;
    int done = 0;
    int MessageType = 0;
    int onD;

    ArrayList<String> Links = new ArrayList<String>();
    ArrayList<String> Titles = new ArrayList<String>();
    Integer[][] DifferentMessages = new Integer[6][1000];
    final String[] FileNames = new String[]{"Onesxx", "Twosxx", "Threesxx", "Foursxx", "Fivesxx", "Sixsxx"};

    public ProgressDialog dlg;
    public MediaPlayer mediaPlayer;
    private boolean playpause;
    private boolean initialstage = true;
    int p = 0;

    private ArrayList<String> s = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onD = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listen_to_message);

        LabelTitle = findViewById(R.id.labelTitle);
        Titles = getIntent().getStringArrayListExtra("MessageTitle");

        seekbar = findViewById(R.id.DistanceBar);
        seekbar.setEnabled(false);
        handler = new Handler();
        times = findViewById(R.id.labelTime);

        buttonPlay = findViewById(R.id.buttonPlay);
        indexoflink = getIntent().getIntExtra("index",0);
        Links = getIntent().getStringArrayListExtra("URLList");
        MessageType = getIntent().getIntExtra("MessageType",0);
        Titles.remove(0);
        LabelTitle.setText(Titles.get(indexoflink));

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        if(DifferentMessages[MessageType][indexoflink] != null)
        {
            mediaPlayer.seekTo(DifferentMessages[MessageType][indexoflink]);
            seekbar.setProgress(mediaPlayer.getCurrentPosition());
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekbar.setMax(mediaPlayer.getDuration());
                int w = mediaPlayer.getDuration();
                mediaPlayer.start();
                playCycle();
            }
        });

        dlg = new ProgressDialog(Listen_To_Message.this);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                progressGlobal = progress;
                if(input)
                {
                    mediaPlayer.seekTo(progress);
                    playCycle();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(mediaPlayer.isPlaying())
                {
                    playCycle();
                }
                else if(!mediaPlayer.isPlaying())
                {
                        playCycleWhenPaused();
                }
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
              //  mediaPlayer.seekTo(progressGlobal);
            }
        });
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekbar.setEnabled(true);
                done = 0;
                if(!playpause)
                {
                    buttonPlay.setText("Pause");

                    if(initialstage)
                    {
                        new Player().execute(Links.get(indexoflink));
                    }
                    else
                    {
                        if(!mediaPlayer.isPlaying())
                        {
                            mediaPlayer.start();
                            playCycle();
                        }
                    }
                    playpause = true;
                }
                else
                {
                    buttonPlay.setText("Resume");
                    if(mediaPlayer.isPlaying())
                    {
                        mediaPlayer.pause();
                    }
                    playpause = false;
                }
            }
        });
    }
    public  void ListDir(File f)
    {
        File[] files = f.listFiles();
        s.clear();
        for(File file : files)
        {
            s.add(file.getPath());
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        mediaPlayer.pause();
    }

    public void playCycle()
    {
        if(onD ==0)
        {
            if(mediaPlayer.isPlaying() && mediaPlayer != null && done ==0)
            {
                seekbar.setProgress(mediaPlayer.getCurrentPosition());
                p = mediaPlayer.getCurrentPosition();


                runnable = new Runnable() {
                    @Override
                    public void run() {
                        playCycle();
                        if (onD ==0 && done ==0)
                        {
                            times.setText(getCurrentTime(mediaPlayer.getCurrentPosition()) + "/" + getDuration(mediaPlayer.getDuration()));
                        }
                    }
                };
                handler.postDelayed(runnable,1000);
            }

        }
        else if(done ==1)
        {
            times.setText("");
        }

    }
    public void playCycleWhenPaused()
    {

        if(!mediaPlayer.isPlaying() && done ==0)
        {
            runnable = new Runnable() {
                @Override
                public void run() {
                    if(done == 0)
                    {
                        playCycleWhenPaused();
                        times.setText(getCurrentTime(seekbar.getProgress()) + "/" + getDuration(mediaPlayer.getDuration()));
                    }
                }
            };
            handler.postDelayed(runnable,100);
        }
        else if(done == 1)
        {
            times.setText("");
        }
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        if(playpause)
        {
            mediaPlayer.start();
            playCycle();
        }

    }
    public String getCurrentTime(int c)
    {
        String CurrentTime = "";
        String secondString = "";
        int hours = (int) (c / (1000 * 60 * 60));
        int minutes = (int) (c % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((c % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        if(hours >= 1)
        {
            CurrentTime += hours + ":";
        }
        if(minutes < 10)
        {
            CurrentTime += "0" + minutes + ":";
        }
        else if(minutes >= 10)
        {
            CurrentTime += "" + minutes + ":";
        }
        if (seconds < 10)
        {
            CurrentTime += "0" + seconds;
        }
        else if(seconds >= 10)
        {
            CurrentTime += "" + seconds;
        }
        return  CurrentTime;
    }
    public String getDuration(int c)
    {
        String CurrentTime = "";
        String secondString = "";
        int hours = (int) (c / (1000 * 60 * 60));
        int minutes = (int) (c % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((c % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if(hours >= 1)
        {
            CurrentTime += hours + ":";
        }
        if(minutes < 10)
        {
            CurrentTime += "0" + minutes + ":";
        }
        else if(minutes >= 10)
        {
            CurrentTime += "" + minutes + ":";
        }
        if (seconds < 10)
        {
            CurrentTime += "0" + seconds;
        }
        else if(seconds >= 10)
        {
            CurrentTime += "" + seconds;
        }
        return CurrentTime;

    }
    @Override
    protected void onDestroy()
    {
        onD = 1;
        super.onDestroy();
        if(mediaPlayer != null)
        {
            mediaPlayer.release();
            handler.removeCallbacks(runnable);
        }
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        if(playpause)
        {
            mediaPlayer.start();
            playCycle();
        }


    }
    class Player extends AsyncTask<String,Void,Boolean>
    {
        @Override
        protected Boolean doInBackground(String... strings) {
            boolean prepared = false;
            try
            {
                mediaPlayer.setDataSource(Links.get(indexoflink));
                //Links.get(indexOfLink)
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        done = 1;
                        initialstage = true;
                        playpause = false;
                        buttonPlay.setText("Play Again");
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        seekbar.setEnabled(false);
                        times.setText("");
                    }
                });
                mediaPlayer.prepare();
                prepared = true;
            }
            catch(Exception e)
            {
                Log.e("MyAudioStringApp",e.getMessage());
            }
            return prepared;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean)
        {
            super.onPostExecute(aBoolean);

            if(dlg.isShowing())
            {
                dlg.cancel();
            }
            mediaPlayer.start();
            initialstage = false;
        }
        @Override
        protected  void onPreExecute()
        {
            super.onPreExecute();
            dlg.setMessage("Buffering...");
            dlg.show();
        }
    }
}
