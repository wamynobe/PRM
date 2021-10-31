//package com.example.prmmusic.activity;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.prmmusic.R;
//import com.example.prmmusic.adapter.RecyclePlayListAdapter;
//import com.example.prmmusic.model.Playlist;
//import com.example.prmmusic.service.APIService;
//import com.example.prmmusic.service.DataService;
//
//import java.util.ArrayList;
//import java.util.List;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MusicPlayList extends AppCompatActivity {
//    RecyclerView rcview;
//    ImageView imv_playlistimg;
//    TextView tv_playlistname;
//    List<Playlist> playlists = new ArrayList<>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_music_play_list);
//        rcview = findViewById(R.id.rv_playlist);
//        imv_playlistimg = findViewById(R.id.imv_playlistimage);
//        tv_playlistname = findViewById(R.id.tv_playlistname);
//        getDataPlayList();
//
//    }
//
//    public void getDataPlayList(){
//        DataService dataService = APIService.getService();
//        Call<List<Playlist>> callBack = dataService.getDataPlayList();
//        callBack.enqueue(new Callback<List<Playlist>>() {
//            @Override
//            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
//                playlists = (ArrayList<Playlist>) response.body();
//                RecyclePlayListAdapter adapter = new RecyclePlayListAdapter(MusicPlayList.this, playlists);
//                rcview.setLayoutManager(new LinearLayoutManager(MusicPlayList.this));
//                rcview.setAdapter(adapter);
//            }
//            @Override
//            public void onFailure(Call<List<Playlist>> call, Throwable t) {
//                Log.d("fail", "fail to load data");
//            }
//        });
//    }
//
//
//}