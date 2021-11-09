package com.example.prmmusic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.adapter.AllAlbumAdapter;
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

public class AlbumListActivity extends AppCompatActivity implements AllAlbumAdapter.OnItemClickListener {

    RecyclerView recyclerViewAlbum;
    Toolbar toolbarAlbum;
    AllAlbumAdapter allAlbumAdapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.getALlAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                allAlbumAdapter = new AllAlbumAdapter(AlbumListActivity.this, albumArrayList, (AllAlbumAdapter.OnItemClickListener) AlbumListActivity.this);


                recyclerViewAlbum.setLayoutManager(new GridLayoutManager(AlbumListActivity.this, 2));
                recyclerViewAlbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    public void init() {
        recyclerViewAlbum = findViewById(R.id.recyclerviewallalbum);
        toolbarAlbum = findViewById(R.id.toolbaralbum);
        setSupportActionBar(toolbarAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Các Album");
        toolbarAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void onItemClick(Album album) {
        DataService dataService = APIService.getService();
                    Call<List<Song>> callBack = dataService.getSongsFromAlbum(album.getId());
                    callBack.enqueue(new Callback<List<Song>>() {
                        @Override
                        public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                            Intent intent = new Intent(AlbumListActivity.this, PlaylistActivity.class);
                            Playlist playlist = new Playlist();
                            playlist.setImageIcon(album.getImage());
                            playlist.setImageBackgroud(album.getImage());
                            playlist.setName(album.getName());
                            intent.putExtra("playlist", playlist);
                            intent.putParcelableArrayListExtra("songs",
                                    (ArrayList<? extends Parcelable>) response.body());
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<List<Song>> call, Throwable t) {
                            Log.d("fail", "fail to load data");
                        }
                    });
    }
}