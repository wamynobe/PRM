package com.example.prmmusic.service;

import com.example.prmmusic.model.Topic;
import com.example.prmmusic.model.Type;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopicAndType {
    @SerializedName("Genres")
    @Expose
    private List<Type> genres = null;

    //JSON name mapper
    @SerializedName("Topics")
    @Expose
    private List<Topic> topics = null;

    public List<Type> getGenres() {
        return genres;
    }

    public void setGenres(List<Type> genres) {
        this.genres = genres;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
