package com.example.prmmusic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prmmusic.R;
import com.example.prmmusic.adapter.AllPlayListAdapter;
import com.example.prmmusic.adapter.RecyclerPlaylistSongAdapter;
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
    AllPlayListAdapter allPlayListAdapter;
    ArrayList<Playlist> playlists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play_list);
        rcview = findViewById(R.id.rv_playlist);
        imv_playlistimg = findViewById(R.id.imv_playlistimage);
        tv_playlistname = findViewById(R.id.tv_playlistname);
//        getDataPlayList();
        getPlaylistData();

    }

    public void getDataPlayList() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callBack = dataService.getDataPlayList();
        callBack.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                Toast.makeText(MusicPlayList.this, "playlistal.get(0).getName()", Toast.LENGTH_LONG).show();
                playlists = (ArrayList<Playlist>) response.body();
//                rcview = new RecyclerPlaylistSongAdapter();
//                ListAllPlayListAdapter listAllPlayListAdapter = new ListAllPlayListAdapter(MusicPlayList.this, playlists);
//                recyclerViewAllPlaylist = new RecyclerView(MusicPlayList.this, playlists);
//                recyclerViewAllPlaylist.se
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    public void getPlaylistData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callBack = dataService.getDataPlayList();
        callBack.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> playlistal = (ArrayList<Playlist>) response.body();
                allPlayListAdapter = new AllPlayListAdapter(MusicPlayList.this, playlistal);
                Log.d("listdata", "onResponse: " + playlistal.get(0).getName());
                rcview.setLayoutManager(new GridLayoutManager(MusicPlayList.this, 1));
                rcview.setAdapter(allPlayListAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }


}