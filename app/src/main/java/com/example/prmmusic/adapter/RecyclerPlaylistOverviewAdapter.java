package com.example.prmmusic.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.model.Playlist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerPlaylistOverviewAdapter extends RecyclerView.Adapter<RecyclerPlaylistOverviewAdapter.MyViewHolder> {

    private final Context context;
    private final List<Playlist> playlists;
    private final OnItemClickListener listener;

    public RecyclerPlaylistOverviewAdapter(Context context, List<Playlist> playlists,
            OnItemClickListener listener) {
        this.context = context;
        this.playlists = playlists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.playlist_overview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Playlist playlist = playlists.get(position);
        Picasso picasso = Picasso.get();
        picasso.load(playlist.getImageBackgroud()).into(holder.playlistBackground);
        picasso.load(playlist.getImageIcon()).into(holder.playlistIcon);
        holder.playlistName.setText(playlist.getName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(playlist.getId()));
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView playlistBackground;
        ImageView playlistIcon;
        TextView playlistName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            playlistBackground = itemView.findViewById(R.id.image_view_playlist_background);
            playlistIcon = itemView.findViewById(R.id.image_view_playlist_icon);
            playlistName = itemView.findViewById(R.id.text_view_playlist_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String playlistId);
    }
}
