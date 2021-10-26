package com.example.prmmusic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.prmmusic.R;
import com.example.prmmusic.adapter.RecycleDataAdapter;
import com.example.prmmusic.model.Playlist;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicPlayList extends AppCompatActivity {
    RecyclerView rcview;
    List<Playlist> playlists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play_list);
        rcview = findViewById(R.id.rv_playlist);
        getDataPlayList();

    }

    private void getDataPlayList(){
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callBack = dataService.getDataPlayList();
        callBack.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlists = (ArrayList<Playlist>) response.body();
                RecycleDataAdapter adapter = new RecycleDataAdapter(MusicPlayList.this, playlists);
                rcview.setLayoutManager(new LinearLayoutManager(MusicPlayList.this));
                rcview.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.d("fail", "fail to load data");
            }
        });
    }
}