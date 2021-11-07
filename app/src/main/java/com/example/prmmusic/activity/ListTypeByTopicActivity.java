package com.example.prmmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.adapter.ListTypeByTopicAdapter;
import com.example.prmmusic.model.Topic;
import com.example.prmmusic.model.Type;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTypeByTopicActivity extends AppCompatActivity {

    Topic topic;
    RecyclerView recyclerViewTypeByTopic;
    Toolbar toolbarTypeByTopic;
    ListTypeByTopicAdapter listTypeByTopicAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_type_by_topic);
        GetIntent();
        init();
        GetData();
    }

    private void GetData() {

        DataService dataService = APIService.getService();
        Call<List<Type>> callback = dataService.getTypeByTopic(topic.getId());
        callback.enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(Call<List<Type>> call, Response<List<Type>> response) {
                ArrayList<Type> typeArrayList = (ArrayList<Type>) response.body();
                listTypeByTopicAdapter = new ListTypeByTopicAdapter(ListTypeByTopicActivity.this, typeArrayList);
                recyclerViewTypeByTopic.setLayoutManager(new GridLayoutManager(ListTypeByTopicActivity.this, 2));
                recyclerViewTypeByTopic.setAdapter(listTypeByTopicAdapter);
            }

            @Override
            public void onFailure(Call<List<Type>> call, Throwable t) {

            }
        });

    }

    private void init() {
        recyclerViewTypeByTopic = findViewById(R.id.recyclerviewtypebytopic);
        toolbarTypeByTopic = findViewById(R.id.toolbartypebytopic);
        setSupportActionBar(toolbarTypeByTopic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(topic.getName());
        toolbarTypeByTopic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("topic")) {
            topic = (Topic) intent.getSerializableExtra("topic");
        }
    }


}