package com.example.prmmusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.activity.ListTypeByTopicActivity;
import com.example.prmmusic.model.Topic;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAllTopicAdapter extends RecyclerView.Adapter<ListAllTopicAdapter.ViewHolder> {
    Context context;
    ArrayList<Topic> topicArrayList;

    public ListAllTopicAdapter(Context context, ArrayList<Topic> topicArrayList) {
        this.context = context;
        this.topicArrayList = topicArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.topic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic topic = topicArrayList.get(position);
        Picasso.get().load(topic.getImage()).into(holder.imgTopic);
    }

    @Override
    public int getItemCount() {
        return topicArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTopic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTopic = itemView.findViewById(R.id.imageviewtopicitem);
            imgTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListTypeByTopicActivity.class);
                    intent.putExtra("topic", topicArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
