package com.example.prmmusic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.fragment.AlbumOverviewFragment;
import com.example.prmmusic.model.Album;
import com.example.prmmusic.model.Playlist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAlbumOverviewAdapter extends RecyclerView.Adapter<RecyclerAlbumOverviewAdapter.MyViewHolder> {

    private final Context context;
    private final List<Album> albums;
    private final RecyclerAlbumOverviewAdapter.OnItemClickListener listener;

    public RecyclerAlbumOverviewAdapter(Context context, List<Album> albums, RecyclerAlbumOverviewAdapter.OnItemClickListener listener) {
        this.context = context;
        this.albums = albums;
        this.listener = listener;
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
        Log.d("OUT", album.getId());
        Picasso.get().load(album.getImage()).into(holder.imageViewAlbum);
        holder.textViewAlbumName.setText(album.getName());
        holder.textViewSingerName.setText(album.getSingerName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(album));
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
    public interface OnItemClickListener {
        void onItemClick(Album album);
    }
}
