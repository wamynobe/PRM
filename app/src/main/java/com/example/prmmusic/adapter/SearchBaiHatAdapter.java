package com.example.prmmusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.activity.MusicPlayerActivity;
import com.example.prmmusic.model.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {

    private final Context context;
    private final List<Song> results;

    public SearchBaiHatAdapter(Context context, List<Song> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ViewHolder(inflater.inflate(R.layout.dong_search_baihat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = results.get(position);
        holder.txtTenbaihat.setText(song.getName());
        holder.txtCasi.setText(song.getSinger());
        Picasso.get().load(song.getImage()).into(holder.imgbaihat);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTenbaihat;
        TextView txtCasi;
        ImageView imgbaihat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenbaihat = itemView.findViewById(R.id.textViewSearchtenbaihat);
            txtCasi = itemView.findViewById(R.id.textViewSeachCaSi);
            imgbaihat = itemView.findViewById(R.id.imageViewSearchbaihat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Song selected = results.get(getAdapterPosition());
                    List<Song> songs = new ArrayList<>();
                    songs.add(selected);
                    Intent intent = new Intent(context, MusicPlayerActivity.class);
                    intent.putParcelableArrayListExtra("songs",
                            (ArrayList<? extends Parcelable>) songs);
                    context.startActivity(intent);
                }
            });
        }
    }
}
