package com.example.prmmusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.activity.AlbumListActivity;
import com.example.prmmusic.activity.ListTypeByTopicActivity;
import com.example.prmmusic.activity.PlaylistActivity;
import com.example.prmmusic.model.Playlist;
import com.example.prmmusic.model.Song;
import com.example.prmmusic.model.Type;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTypeByTopicAdapter extends RecyclerView.Adapter<ListTypeByTopicAdapter.ViewHolder>{

    Context context;
    ArrayList<Type> typeArrayList;
    public ListTypeByTopicAdapter(Context context, ArrayList<Type> typeArrayList) {
        this.context = context;
        this.typeArrayList = typeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.type_by_topic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Type type = typeArrayList.get(position);
        Picasso.get().load(type.getImage()).into(holder.imageView);
        holder.textViewtypename.setText(type.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService dataService = APIService.getService();
                Call<List<Song>> call = dataService.getSongsFromType(type.getId());
                call.enqueue(new Callback<List<Song>>() {
                    @Override
                    public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                        Intent intent = new Intent(context.getApplicationContext(), PlaylistActivity.class);
                        Playlist playlist = new Playlist();
                        playlist.setImageIcon(type.getImage());
                        playlist.setImageBackgroud(type.getImage());
                        playlist.setName(type.getName());
                        intent.putExtra("playlist", playlist);
                        intent.putParcelableArrayListExtra("songs",
                                (ArrayList<? extends Parcelable>) response.body());
                        context.startActivities(new Intent[]{intent});
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
        return typeArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewtypename;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageviewtopicitem);
            textViewtypename = itemView.findViewById(R.id.textviewtypename);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListTypeByTopicActivity.class);
                    intent.putExtra("typeId", typeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
