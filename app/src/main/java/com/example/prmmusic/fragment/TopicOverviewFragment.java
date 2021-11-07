package com.example.prmmusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.activity.AllTopicActivity;
import com.example.prmmusic.adapter.RecyclerTopicOverviewAdapter;
import com.example.prmmusic.model.Topic;
import com.example.prmmusic.model.TopicAndType;
import com.example.prmmusic.model.Type;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicOverviewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView moreTopic = view.findViewById(R.id.text_view_more_topic);
        moreTopic.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AllTopicActivity.class);
            startActivity(intent);
        });
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_topic_overview);
        DataService dataService = APIService.getService();
        Call<TopicAndType> callBack = dataService.select4TopicAnd4Type();
        callBack.enqueue(new Callback<TopicAndType>() {
            @Override
            public void onResponse(@NonNull Call<TopicAndType> call,
                    @NonNull Response<TopicAndType> response) {
                TopicAndType topicAndType = response.body();
                if (topicAndType == null)
                    return;
                List<TopicOverviewItem> items = new ArrayList<>();
                for (Topic topic : topicAndType.getTopics()) {
                    items.add(new TopicOverviewItem(topic.getId(), "topic", topic.getImage()));
                }
                for (Type genre : topicAndType.getGenres()) {
                    items.add(new TopicOverviewItem(genre.getId(), "genre", genre.getImage()));
                }
                recyclerView.setAdapter(new RecyclerTopicOverviewAdapter(getContext(), items));
            }

            @Override
            public void onFailure(@NonNull Call<TopicAndType> call, @NonNull Throwable t) {
                Log.d("fail", "fail to load data");
            }
        });
    }
}
