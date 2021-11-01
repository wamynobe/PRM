package com.example.prmmusic.interfaces;

import com.example.prmmusic.model.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("playlist.php")
    Call<List<Playlist>> getDataPlayList();


}
