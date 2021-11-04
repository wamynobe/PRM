package com.example.prmmusic.fragment;

public class TopicOverviewItem {

    private final String id;
    private final String type;
    private final String backgroundUrl;

    public TopicOverviewItem(String id, String type, String backgroundUrl) {
        this.id = id;
        this.type = type;
        this.backgroundUrl = backgroundUrl;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }
}
