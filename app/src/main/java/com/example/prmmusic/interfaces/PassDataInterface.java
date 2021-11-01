package com.example.prmmusic.interfaces;

import com.example.prmmusic.model.Song;

import java.util.List;

public interface PassDataInterface {
    void onReceivedListSongs(List<Song> listSongs);
}
