package com.example.prmmusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultRegistry;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.activity.PlaylistActivity;
import com.example.prmmusic.model.Song;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;

public class SearchBaiHatAdapter extends  RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> mangbaihat;

    public SearchBaiHatAdapter(ActivityResultRegistry context, ArrayList<Song> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_baihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = mangbaihat.get(position);
        holder.txtTenbaihat.setText(song.getName());
        holder.txtCasi.setText(song.getSinger());
        Picasso.get().load(song.getLink()).into(holder.imgbaihat);


    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenbaihat,txtCasi;
        ImageView imgbaihat,imgluotthich;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenbaihat = itemView.findViewById(R.id.textViewSearchtenbaihat);
            txtCasi = itemView.findViewById(R.id.textViewSeachCaSi);
            imgbaihat = itemView.findViewById(R.id.imageViewSearchbaihat);
            imgluotthich = itemView.findViewById(R.id.imageViewSearchluotthich);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlaylistActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
//            imgluotthich.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    imgluotthich.setImageResource(R.drawable.ic_next);
//                    DataService dataService = APIService.getService();
//                    Call<String> callback = dataService.
//                }
//            });
        }
    }
}
