package com.example.prmmusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.activity.MusicPlayList;
import com.example.prmmusic.activity.PlaylistActivity;
import com.example.prmmusic.model.Album;
import com.example.prmmusic.model.Playlist;
import com.example.prmmusic.model.Song;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albumArrayList;
    private final OnItemClickListener listener;
    public AllAlbumAdapter(Context context, ArrayList<Album> albumArrayList, OnItemClickListener listener) {
        this.context = context;
        this.albumArrayList = albumArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        Picasso.get().load(album.getImage()).into(holder.imageViewAllAlbum);
        holder.textViewAllAlbum.setText(album.getName());
        holder.textviewsingername.setText(album.getSingerName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(album));
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Album album);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAllAlbum;
        TextView textViewAllAlbum;
        TextView textviewsingername;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAllAlbum = itemView.findViewById(R.id.imageviewalbumitem);
            textViewAllAlbum = itemView.findViewById(R.id.textviewalbumitem);
            textviewsingername = itemView.findViewById(R.id.textviewsingername);
        }
    }


}
