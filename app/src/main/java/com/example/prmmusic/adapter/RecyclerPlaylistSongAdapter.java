package com.example.prmmusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.activity.PlaylistActivity;
import com.example.prmmusic.model.Song;

import java.util.List;

public class RecyclerPlaylistSongAdapter extends RecyclerView.Adapter<RecyclerPlaylistSongAdapter.MyViewHolder> {

    Context context;
    private final List<Song> songs;
    private final OnItemClickListener listener;

    public RecyclerPlaylistSongAdapter(Context context, List<Song> songs,
            OnItemClickListener listener) {
        this.context = context;
        this.songs = songs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.playlist_song_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("debug", "onResponse: playlistsongapdter");
        Song song = songs.get(position);
        holder.textViewIndex.setText(position + 1 + "");
        holder.textViewSongName.setText(song.getName());
        holder.textViewSingerName.setText(song.getSinger());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(song,holder.getLayoutPosition()));
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewIndex;
        TextView textViewSongName;
        TextView textViewSingerName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIndex = itemView.findViewById(R.id.text_view_index);
            textViewSongName = itemView.findViewById(R.id.text_view_song_name);
            textViewSingerName = itemView.findViewById(R.id.text_view_singer_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,PlaylistActivity.class);
//                    intent.putExtra("songs",songs);

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Song song, int index);
    }
}
