package com.example.prmmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.model.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAlbumOverviewAdapter extends RecyclerView.Adapter<RecyclerAlbumOverviewAdapter.MyViewHolder> {

    private final Context context;
    private final List<Album> albums;

    public RecyclerAlbumOverviewAdapter(Context context, List<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.album_overview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Album album = albums.get(position);
        Picasso.get().load(album.getImage()).into(holder.imageViewAlbum);
        holder.textViewAlbumName.setText(album.getName());
        holder.textViewSingerName.setText(album.getSingerName());
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewAlbum;
        TextView textViewAlbumName;
        TextView textViewSingerName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAlbum = itemView.findViewById(R.id.image_view_album);
            textViewAlbumName = itemView.findViewById(R.id.text_view_album_name);
            textViewSingerName = itemView.findViewById(R.id.text_view_singer_name);
        }
    }
}
