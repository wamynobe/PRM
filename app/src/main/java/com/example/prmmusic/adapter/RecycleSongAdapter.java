//package com.example.prmmusic.adapter;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.prmmusic.R;
//import com.example.prmmusic.activity.PlayListSong;
//import com.example.prmmusic.model.Playlist;
//import com.example.prmmusic.model.Song;
//
//import java.util.List;
//
//public class RecycleSongAdapter extends RecyclerView.Adapter<RecycleSongAdapter.MyViewHolder>{
//    private List<Song> listSong;
//    private Context context;
//
//    public RecycleSongAdapter(Context context, List<Song> listSong) {
//        this.context = context;
//        this.listSong = listSong;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.list_song_item, parent, false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecycleSongAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        Glide.with(context).load(listSong.get(position).getImageIcon()).into(holder.imv_image);
//        holder.tv_name.setText(listSong.get(position).getName());
//        holder.tv_description.setText("description");
//        holder.imv_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, PlayListSong.class);
//                i.putExtra("playlist", playlists.get(position));
//                context.startActivity(i);
//            }
//        });
//        holder.tv_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, PlayListSong.class);
//                i.putExtra("playlist", playlists.get(position));
//                context.startActivity(i);
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return playlists.size();
//    }
//
//
//
//
//    /**
//     * Data ViewHolder class.
//     */
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        ImageView imv_image;
//        TextView tv_name;
//        TextView tv_description;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imv_image = itemView.findViewById(R.id.imv_playlistimage);
//            tv_name = itemView.findViewById(R.id.tv_playlistname);
//            tv_description = itemView.findViewById(R.id.tv_playlistdescription);
//        }
//    }
//}
//
