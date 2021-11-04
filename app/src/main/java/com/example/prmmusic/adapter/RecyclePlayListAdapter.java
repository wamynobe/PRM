package com.example.prmmusic.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prmmusic.R;
import com.example.prmmusic.activity.PlayListSong;
import com.example.prmmusic.model.Playlist;

import java.util.List;

public class RecyclePlayListAdapter extends RecyclerView.Adapter<RecyclePlayListAdapter.MyViewHolder> {
    private List<Playlist> playlists;
    private Context context;

    public RecyclePlayListAdapter(Context context, List<Playlist> playlists) {
        this.context = context;
        this.playlists = playlists;
        Log.d("contructor inside", "RecycleDataAdapter: ");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.play_list_item, parent, false);
        Log.d("oncreate", "onCreateViewHolder: ");
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclePlayListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(playlists.get(position).getImageIcon()).into(holder.imv_image);
        holder.tv_name.setText(playlists.get(position).getName());
        holder.tv_description.setText("description");
        holder.imv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }




    /**
     * Data ViewHolder class.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_image;
        TextView tv_name;
        TextView tv_description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_image = itemView.findViewById(R.id.imv_playlistimage);
            tv_name = itemView.findViewById(R.id.tv_playlistname);
            tv_description = itemView.findViewById(R.id.tv_playlistdescription);
        }
    }
}
