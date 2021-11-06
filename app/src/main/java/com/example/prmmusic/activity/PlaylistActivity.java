package com.example.prmmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.adapter.RecyclerPlaylistSongAdapter;
import com.example.prmmusic.model.Playlist;
import com.example.prmmusic.model.Song;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity implements RecyclerPlaylistSongAdapter.OnItemClickListener {
    List<Song> listSongs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Intent caller = getIntent();
        Playlist playlist = (Playlist) caller.getSerializableExtra("playlist");
        listSongs = caller.getParcelableArrayListExtra("songs");

        ImageView iconBack = findViewById(R.id.icon_back);
        ImageView playlistBackground = findViewById(R.id.image_view_playlist_background);
        ImageView playlistIcon = findViewById(R.id.image_view_playlist_icon);
        FloatingActionButton buttonPlayAll = findViewById(R.id.button_play_all);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_playlist);

        Picasso picasso = Picasso.get();
        picasso.load(playlist.getImageBackgroud()).into(playlistBackground);
        picasso.load(playlist.getImageIcon()).into(playlistIcon);
        recyclerView.setAdapter(new RecyclerPlaylistSongAdapter(this, listSongs, this));

        iconBack.setOnClickListener(v -> this.finish());
        buttonPlayAll.setOnClickListener(v -> handlePlayAllButtonClick(listSongs));
    }

    private void handlePlayAllButtonClick(List<Song> songs) {
        Intent intent = new Intent(this, MusicPlayerActivity.class);
        intent.putParcelableArrayListExtra("songs", (ArrayList<? extends Parcelable>) songs);
        intent.putExtra("index", 0);
        startActivity(intent);
    }


    @Override
    public void onItemClick(Song song, int index) {
        Log.d("debug", "onResponse: playlistsong");
        Intent intent = new Intent(this, MusicPlayerActivity.class);
        intent.putParcelableArrayListExtra("songs", (ArrayList<? extends Parcelable>) listSongs);
        intent.putExtra("index", index);
        startActivity(intent);
    }
}
