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

// thanh lam class nao
public class AllPlayListAdapter extends RecyclerView.Adapter<AllPlayListAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> playlistArrayList;

    public AllPlayListAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.play_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlistArrayList.get(position);
        Picasso.get().load(playlist.getImageIcon()).into(holder.imageViewAllPlayList);
        holder.textViewAllPlaylist.setText(playlist.getName());
        holder.textviewPlaylistDesc.setText(playlist.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService dataService = APIService.getService();
                Call<List<Song>> call = dataService.getSongsFromPlaylist(playlist.getId());
                call.enqueue(new Callback<List<Song>>() {
                    @Override
                    public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                        Intent intent = new Intent(v.getContext(), PlaylistActivity.class);
                        intent.putExtra("playlist", playlist);
                        intent.putParcelableArrayListExtra("songs",
                                (ArrayList<? extends Parcelable>) response.body());
                        context.startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<List<Song>> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAllPlayList;
        TextView textViewAllPlaylist;
        TextView textviewPlaylistDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAllPlayList = itemView.findViewById(R.id.imv_playlistimage);
            textViewAllPlaylist = itemView.findViewById(R.id.tv_playlistname);
            textviewPlaylistDesc = itemView.findViewById(R.id.tv_playlistdescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MusicPlayList.class);
                    intent.putExtra("albumID", playlistArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }


}
