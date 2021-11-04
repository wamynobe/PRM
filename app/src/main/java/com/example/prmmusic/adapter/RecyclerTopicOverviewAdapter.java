package com.example.prmmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.fragment.TopicOverviewItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerTopicOverviewAdapter extends RecyclerView.Adapter<RecyclerTopicOverviewAdapter.MyViewHolder> {

    private final Context context;
    private final List<TopicOverviewItem> items;

    public RecyclerTopicOverviewAdapter(Context context, List<TopicOverviewItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.topic_overview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TopicOverviewItem item = items.get(position);
        Picasso.get().load(item.getBackgroundUrl()).into(holder.imageViewBackground);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewBackground;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.image_view_background);
        }
    }
}
