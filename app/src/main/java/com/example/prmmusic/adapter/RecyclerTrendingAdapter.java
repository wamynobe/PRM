package com.example.prmmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.prmmusic.R;
import com.example.prmmusic.model.Trending;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerTrendingAdapter extends PagerAdapter {

    private final Context context;
    private final List<Trending> trendingList;

    public RecyclerTrendingAdapter(Context context, List<Trending> trendingList) {
        this.context = context;
        this.trendingList = trendingList;
    }

    @Override
    public int getCount() {
        return trendingList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trending_item, null);
        ImageView imageViewTrending = view.findViewById(R.id.image_view_trending);
        ImageView imageViewSong = view.findViewById(R.id.image_view_song);
        TextView textViewSongName = view.findViewById(R.id.text_view_song_name);
        TextView textViewContent = view.findViewById(R.id.text_view_content);
        Trending trending = trendingList.get(position);
        Picasso picasso = Picasso.get();
        picasso.load(trending.getImage()).into(imageViewTrending);
        picasso.load(trending.getSongImage()).into(imageViewSong);
        textViewSongName.setText(trending.getSongName());
        textViewContent.setText(trending.getContent());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
