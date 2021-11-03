package com.example.prmmusic.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
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
import com.example.prmmusic.interfaces.PassDataInterface;
import com.example.prmmusic.model.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MusicPlayerActivity extends AppCompatActivity implements PassDataInterface {
    private ObjectAnimator objectAnimator;
    private CircleImageView imvc_dianhac;
    private ImageView imv_playandpause, imv_next, imv_previous, imv_back, imv_repeat, imv_shuffle;
    private TextView tv_currenttime, tv_totalduration;
    private SeekBar sb_playseekbar;
    private MediaPlayer mediaPlayer = new MediaPlayer();;
    private Handler handler = new Handler();
    String urlSong = "";
    String urlImg = "";
    public int currentSongPosition = 0;
    boolean isRepeat = false;
    boolean isShuffle = false;
    public static List<Song> listSongs = new ArrayList<>();
    private int numSong = 0;


    public void setCurrentSongPosition(int p){
        this.currentSongPosition = p;
    }
    public void setListSongs(List<Song> listSongs){
        this.listSongs = listSongs;
    }
    public void setSampleData(){
        Song song = new Song(1,"1","https://i.ytimg.com/vi/Z25VTNjwBm0/hqdefault.jpg",0,"https://firebasestorage.googleapis.com/v0/b/prmmusiclistener.appspot.com/o/Music%2Fbonfire.mp3?alt=media&token=7fef7eea-23af-4fca-886a-b24a61112f02","Bonfire","0","Various","0");
        Song song1 = new Song(2,"1","https://i.ytimg.com/vi/Z25VTNjwBm0/hqdefault.jpg",0,"https://firebasestorage.googleapis.com/v0/b/prmmusiclistener.appspot.com/o/Music%2FCH%E1%BA%AEC%20ANH%20%C4%90ANG.mp3?alt=media&token=7f8cedd5-ac7e-40e8-8ee5-0341f6f61c41","Chắc Anh ĐAng","0","Various","0");
        Song song2 = new Song(2,"1","https://i.ytimg.com/vi/Z25VTNjwBm0/hqdefault.jpg",0,"https://firebasestorage.googleapis.com/v0/b/prmmusiclistener.appspot.com/o/Music%2FC%C6%AFA%20L%C3%80%20%C4%90%E1%BB%94.mp3?alt=media&token=a02484b1-7312-4b09-abc2-7ea16ece5989","Chắc Anh ĐAng","0","Various","0");
        Song song3 = new Song(2,"1","https://i.ytimg.com/vi/Z25VTNjwBm0/hqdefault.jpg",0,"https://firebasestorage.googleapis.com/v0/b/prmmusiclistener.appspot.com/o/Music%2FEM%20L%C3%80%20CON%20THUY%E1%BB%80N%20C%C3%94%20%C4%90%C6%A0N.mp3?alt=media&token=47560cea-b027-42fb-a522-066c308424d6","Chắc Anh ĐAng","0","Various","0");
        listSongs.add(song);
        listSongs.add(song1);
        listSongs.add(song2);
        listSongs.add(song3);

    }
    public void animatorSetup(){
        objectAnimator = ObjectAnimator.ofFloat(imvc_dianhac, "rotation", 0f, 360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }
    public void init(){
        setSampleData();
        imv_playandpause = findViewById(R.id.imv_PlayAndPause);
        tv_currenttime = findViewById(R.id.tv_currentime);
        tv_totalduration = findViewById(R.id.tv_totalduration);
        sb_playseekbar = findViewById(R.id.sb_playseekbar);
        imvc_dianhac = findViewById(R.id.imvc_dianhac);
        imv_next = findViewById(R.id.imv_next);
        imv_previous = findViewById(R.id.imv_previous);
        imv_back = findViewById(R.id.imv_back);
        imv_repeat = findViewById(R.id.imv_repeat);
        imv_shuffle = findViewById(R.id.imv_shuffle);


    }
    public void playSong(int index){
        sb_playseekbar.setMax(100);
        urlSong = listSongs.get(index).getLink();
        urlImg = listSongs.get(index).getImage();
        updateImgDiaNhac(urlImg);
        prepareMediaPlayer(urlSong);
        mediaPlayer.start();
        imv_playandpause.setImageResource(R.drawable.ic_pause);
        updateSeekBar();
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayer);
        //find id for component
        init();
        //set up animator for music CD on activity
        animatorSetup();


        playSong(0);
        currentSongPosition = 0;
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

        imv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentSongPosition > 0 && currentSongPosition < listSongs.size()){
                    mediaPlayer.reset();
                    currentSongPosition = currentSongPosition - 1;
                    playSong(currentSongPosition);
                }
            }
        });

        imv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentSongPosition >= 0 && currentSongPosition < listSongs.size()-1){
                    mediaPlayer.reset();
                    currentSongPosition = currentSongPosition + 1;
                    playSong(currentSongPosition);
                }
            }
        });

        imv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //back to previous activity
                finish();
            }
        });

        imv_shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isShuffle){
                    isShuffle = true;
                    ColorFilter filter = new LightingColorFilter( Color.BLACK, Color.BLACK);
                    imv_shuffle.setColorFilter(filter);
                }else{
                    isShuffle = false;
                    imv_shuffle.clearColorFilter();
                }
            }
        });

        imv_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRepeat){
                    isRepeat = true;
                    ColorFilter filter = new LightingColorFilter( Color.BLACK, Color.BLACK);
                    imv_repeat.setColorFilter(filter);
                }else{
                    isRepeat = false;
                    imv_repeat.clearColorFilter();
                }
            }
        });







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
                if (isShuffle) {
                    if (isRepeat) {
                        if(numSong < 1 ) {
                            playSong(currentSongPosition);
                            numSong = numSong + 1;
                        }else{
                            currentSongPosition = getRandomIndex();
                            playSong(currentSongPosition);
                        }
                    } else {
                        currentSongPosition = getRandomIndex();
                        playSong(currentSongPosition);
                    }
                }else{
                    if (isRepeat) {
                        playSong(currentSongPosition);
                    } else {
                        if (currentSongPosition >= 0 && currentSongPosition < listSongs.size() - 1) {
                            playSong(currentSongPosition + 1);
                            currentSongPosition += 1;
                        }
                        if (currentSongPosition == listSongs.size() - 1) {
                            currentSongPosition = 0;
                        }
                    }
                }

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

    public int getRandomIndex(){
        Random rand = new Random();
        int n = rand.nextInt(listSongs.size());
        return n;
    }

    @Override
    public void onReceivedListSongs(List<Song> listSongs) {

    }
}
