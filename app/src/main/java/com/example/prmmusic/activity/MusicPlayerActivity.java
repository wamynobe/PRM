package com.example.prmmusic.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prmmusic.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MusicPlayerActivity extends AppCompatActivity {
    private ObjectAnimator objectAnimator;
    private CircleImageView imvc_dianhac;
    private ImageView imv_playandpause;
    private TextView tv_currenttime, tv_totalduration;
    private SeekBar sb_playseekbar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    public void animatorSetup(){
        objectAnimator = ObjectAnimator.ofFloat(imvc_dianhac, "rotation", 0f, 360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
        objectAnimator.pause();
    }
    public void init(){
        imv_playandpause = findViewById(R.id.imv_PlayAndPause);
        tv_currenttime = findViewById(R.id.tv_currentime);
        tv_totalduration = findViewById(R.id.tv_totalduration);
        sb_playseekbar = findViewById(R.id.sb_playseekbar);
        imvc_dianhac = findViewById(R.id.imvc_dianhac);
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayer);
        //find id for component
        init();
        //set up animator for music CD on activity
        animatorSetup();

        mediaPlayer = new MediaPlayer();

        sb_playseekbar.setMax(100);

        imv_playandpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    objectAnimator.pause();
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    imv_playandpause.setImageResource(R.drawable.ic_play);

                }else{
                    objectAnimator.resume();
                    mediaPlayer.start();
                    imv_playandpause.setImageResource(R.drawable.ic_pause);
                    updateSeekBar();

                }
            }
        });

        String urlSong = "https://firebasestorage.googleapis.com/v0/b/prmmusiclistener.appspot.com/o/Music%2Ftest.mp3?alt=media&token=60a18dc9-25f4-4c33-8193-a895a8940f67";
        String urlImg = "https://yt3.ggpht.com/Vn34V_RSZY7jBw3UzivU_93m35T-___z2ZSDkV2I41Z30RnQqGhtQ0O8RCeNzHtRY1GTRbl-aiE=s48-c-k-c0x00ffffff-no-rj";
        updateImgDiaNhac(urlImg);
        prepareMediaPlayer(urlSong);


        sb_playseekbar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SeekBar seekBar = (SeekBar) view;
                int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                tv_currenttime.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });


        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                sb_playseekbar.setSecondaryProgress(i);

            }
        });


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                sb_playseekbar.setProgress(0);
                imv_playandpause.setImageResource(R.drawable.ic_play);
                tv_currenttime.setText(R.string.zero);
                tv_totalduration.setText(R.string.zero);
                mediaPlayer.reset();
                prepareMediaPlayer(urlSong);
            }
        });

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
    public void updateImgDiaNhac(String imageUrl) {
        Picasso.get().load(imageUrl).into(imvc_dianhac);
    }
}
