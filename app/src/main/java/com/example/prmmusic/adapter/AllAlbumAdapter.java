package com.example.prmmusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.activity.MusicPlayList;
import com.example.prmmusic.model.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albumArrayList;

    public AllAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
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

    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MusicPlayList.class);
                    intent.putExtra("albumID", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }


}
