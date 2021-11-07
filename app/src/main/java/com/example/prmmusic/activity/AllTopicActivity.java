package com.example.prmmusic.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.adapter.ListAllTopicAdapter;
import com.example.prmmusic.model.Topic;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTopicActivity extends AppCompatActivity {

    RecyclerView recyclerViewAllTopic;
    Toolbar toolbarAllTopic;
    ListAllTopicAdapter listAllTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_topic);
        init();
        GetData();
    }

    private void init() {
        recyclerViewAllTopic = findViewById(R.id.recyclerviewalltopic);
        toolbarAllTopic = findViewById(R.id.topic_toolbar);
        setSupportActionBar(toolbarAllTopic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả chủ đề");
        toolbarAllTopic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void GetData(){
        DataService dataService = APIService.getService();
        Call<List<Topic>> callback = dataService.getAllTopics();
        callback.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                ArrayList<Topic> topicArrayList = (ArrayList<Topic>) response.body();
                listAllTopic = new ListAllTopicAdapter(AllTopicActivity.this, topicArrayList);
                recyclerViewAllTopic.setLayoutManager(new GridLayoutManager(AllTopicActivity.this,1));
                recyclerViewAllTopic.setAdapter(listAllTopic);
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {

            }
        });
    }
}