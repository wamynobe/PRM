package com.example.prmmusic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playlist {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("imageBackgroud")
    @Expose
    private String imageBackgroud;
    @SerializedName("imageIcon")
    @Expose
    private String imageIcon;
    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageBackgroud() {
        return imageBackgroud;
    }

    public void setImageBackgroud(String imageBackgroud) {
        this.imageBackgroud = imageBackgroud;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}