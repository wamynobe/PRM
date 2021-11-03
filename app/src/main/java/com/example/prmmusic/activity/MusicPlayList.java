package com.example.prmmusic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prmmusic.R;
import com.example.prmmusic.adapter.RecyclePlayListAdapter;
import com.example.prmmusic.model.Album;
import com.example.prmmusic.model.Playlist;
import com.example.prmmusic.model.Song;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicPlayList extends AppCompatActivity {
    RecyclerView rcview;
    ImageView imv_playlistimg;
    TextView tv_playlistname;
    List<Playlist> playlists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play_list);
        rcview = findViewById(R.id.rv_playlist);
        imv_playlistimg = findViewById(R.id.imv_playlistimage);
        tv_playlistname = findViewById(R.id.tv_playlistname);
        getDataPlayList();
        getAllAlbum();

    }

    public void getDataPlayList(){
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callBack = dataService.getDataPlayList();
        callBack.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlists = (ArrayList<Playlist>) response.body();
                Log.d("listdata", "onResponse: " + playlists.get(0).getName());
            }
            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.d("fail", "fail to load data");
            }
        });
    }
    public void getAllAlbum(){
        DataService dataService = APIService.getService();
        Call<List<Song>> callBack = dataService.getSongsFromAlbum("1");
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                List<Song> playlistal = (ArrayList<Song>) response.body();
                Log.d("listdata", "onResponse: " + playlistal.get(0).getName());
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.d("fail", "fail to load data");
            }
        });
    }


}