package com.example.prmmusic;

import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MusicPlayerActivity extends AppCompatActivity {
    private ImageView imv_playandpause;
    private TextView tv_currenttime, tv_totalduration;
    private SeekBar sb_playseekbar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayer);
        imv_playandpause = findViewById(R.id.imv_PlayAndPause);
        tv_currenttime = findViewById(R.id.tv_currentime);
        tv_totalduration = findViewById(R.id.tv_totalduration);
        sb_playseekbar = findViewById(R.id.sb_playseekbar);
        mediaPlayer = new MediaPlayer();

        sb_playseekbar.setMax(100);

        imv_playandpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    imv_playandpause.setImageResource(R.drawable.ic_play);

                }else{
                    mediaPlayer.start();
                    imv_playandpause.setImageResource(R.drawable.ic_pause);
                    updateSeekBar();
                }
            }
        });
        String urlSong = "https://firebasestorage.googleapis.com/v0/b/prmmusiclistener.appspot.com/o/Music%2Ftest.mp3?alt=media&token=60a18dc9-25f4-4c33-8193-a895a8940f67";
        prepareMediaPlayer(urlSong);

    }

    private void prepareMediaPlayer(String url){
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            tv_totalduration.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            tv_currenttime.setText(milliSecondsToTimer(currentDuration));
        }
    };

    private void updateSeekBar(){
        if(mediaPlayer.isPlaying()){
            sb_playseekbar.setProgress((int) (((float)mediaPlayer.getCurrentPosition() /mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }

    private String milliSecondsToTimer(long milliSeconds){
        String timerString ="";
        String secondsString;
        int hours = (int) (milliSeconds/(1000*3600));
        int minutes = (int) (milliSeconds%(1000*3600))/(1000*60);
        int seconds = (int) ((milliSeconds%(1000*3600))%(1000*60) / 1000);
        if(hours > 0){
            timerString = hours + ":";
        }
        if(seconds < 10){
            secondsString = "0" + seconds;
        }else {
            secondsString = "" + seconds;
        }

        timerString = timerString + minutes + ":"+secondsString;
        return timerString;
    }
}
