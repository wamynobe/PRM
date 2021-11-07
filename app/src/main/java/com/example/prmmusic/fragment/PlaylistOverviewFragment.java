package com.example.prmmusic.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.activity.MusicPlayerActivity;
import com.example.prmmusic.adapter.RecyclerPlaylistOverviewAdapter;
import com.example.prmmusic.model.Playlist;
import com.example.prmmusic.model.Song;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistOverviewFragment extends Fragment implements RecyclerPlaylistOverviewAdapter.OnItemClickListener {
    private static MediaPlayer player;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_playlist_overview);
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callBack = dataService.get3Playlist();
        callBack.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(@NonNull Call<List<Playlist>> call,
                    @NonNull Response<List<Playlist>> response) {
                recyclerView.setAdapter(
                        new RecyclerPlaylistOverviewAdapter(getContext(), response.body(),
                                PlaylistOverviewFragment.this));
            }

            @Override
            public void onFailure(@NonNull Call<List<Playlist>> call, @NonNull Throwable t) {
                Log.d("fail", "fail to load data");
            }
        });
    }


    @Override
    public void onItemClick(String playlistId) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callBack = dataService.getSongsFromPlaylist(playlistId);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(@NonNull Call<List<Song>> call,
                    @NonNull Response<List<Song>> response) {
                if(player != null){
                    player.stop();
                    player.release();
                    Log.d("release", "onResponse: release");
                }
                Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                intent.putParcelableArrayListExtra("songs",
                        (ArrayList<? extends Parcelable>) response.body());
                startActivity(intent);

            }

            @Override
            public void onFailure(@NonNull Call<List<Song>> call, @NonNull Throwable t) {
                Log.d("fail", "fail to load data");
            }
        });
    }
}
