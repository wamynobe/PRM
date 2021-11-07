package com.example.prmmusic.service;

import com.example.prmmusic.model.Topic;
import com.example.prmmusic.model.Type;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopicAndType {
    @SerializedName("type")
    @Expose
    private List<Type> type = null;
    @SerializedName("topic")
    @Expose
    private List<Topic> topic = null;

    public List<Type> getType() {
        return type;
    }

    public void setType(List<Type> type) {
        this.type = type;
    }

    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }
}