package com.example.prmmusic.service;

import com.example.prmmusic.model.Album;
import com.example.prmmusic.model.Playlist;
import com.example.prmmusic.model.Song;
import com.example.prmmusic.model.Topic;
import com.example.prmmusic.model.TopicAndType;
import com.example.prmmusic.model.Trending;
import com.example.prmmusic.model.Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("getAllPlaylist.php")
    Call<List<Playlist>> getDataPlayList();

    @GET("getAllAlbum.php")
    Call<List<Album>> getAllAlbums();

    @GET("getAllTopic.php")
    Call<List<Topic>> getAllTopics();

    @FormUrlEncoded
    @POST("getAllTypeByTopic.php")
    Call<List<Type>> getTypeByTopic(@Field("topicID") String topicID);

    @GET("getAllAlbum.php")
    Call<List<Album>> getALlAlbum();

    @FormUrlEncoded
    @POST("getListSong.php")
    Call<List<Type>> getPlayyListByAlbum(@Field("typeID") String typeID);

    @FormUrlEncoded
    @POST("getListSong.php")
    Call<List<Song>> getSongsFromAlbum(@Field("albumID") String albumID);

    @GET("getTrendingSong.php")
    Call<List<Trending>> getTrendingSong();

    @GET("getRand3Playlist.php")
    Call<List<Playlist>> get3Playlist();

    @GET("getRand4Album.php")
    Call<List<Album>> get4Albums();

    @GET("getListTypeAndTopic.php")
    Call<TopicAndType> select4TopicAnd4Type();

    @FormUrlEncoded
    @POST("getListSong.php")
    Call<List<Song>> getSongsFromTrending(@Field("trendingID") String trendingID);

    @FormUrlEncoded
    @POST("getListSong.php")
    Call<List<Song>> getSongsFromPlaylist(@Field("playlistID") String playlistID);


    @FormUrlEncoded
    @POST("getListSong.php")
    Call<List<Song>> getSongsFromType(@Field("typeID") String typeID);

    @GET("getListTypeAndTopic.php")
    Call<TopicAndType> getListTypeAndTopic();


    @FormUrlEncoded
    @POST("searchByKeyword.php")
    Call<List<Song>> getSongsByKeyword(@Field("keyword") String keyword);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Song>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);
}
