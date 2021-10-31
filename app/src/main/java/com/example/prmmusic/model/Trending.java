package com.example.prmmusic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trending {

@SerializedName("id")
@Expose
private String id;
@SerializedName("content")
@Expose
private String content;
@SerializedName("image")
@Expose
private String image;
@SerializedName("songID")
@Expose
private String songID;
@SerializedName("songName")
@Expose
private String songName;
@SerializedName("songImage")
@Expose
private String songImage;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getContent() {
return content;
}

public void setContent(String content) {
this.content = content;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getSongID() {
return songID;
}

public void setSongID(String songID) {
this.songID = songID;
}

public String getSongName() {
return songName;
}

public void setSongName(String songName) {
this.songName = songName;
}

public String getSongImage() {
return songImage;
}

public void setSongImage(String songImage) {
this.songImage = songImage;
}

}