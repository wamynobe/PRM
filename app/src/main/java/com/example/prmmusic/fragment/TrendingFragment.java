package com.example.prmmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.prmmusic.R;
import com.example.prmmusic.adapter.RecyclerTrendingAdapter;
import com.example.prmmusic.model.Trending;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingFragment extends Fragment {

    private int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trending, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = view.findViewById(R.id.view_pager_trending);
        CircleIndicator indicator = view.findViewById(R.id.indicator_trending);
        DataService dataService = APIService.getService();
        Call<List<Trending>> callBack = dataService.getTrendingSong();
        callBack.enqueue(new Callback<List<Trending>>() {
            @Override
            public void onResponse(@NonNull Call<List<Trending>> call,
                    @NonNull Response<List<Trending>> response) {
                RecyclerTrendingAdapter adapter = new RecyclerTrendingAdapter(getContext(),
                        response.body());
                viewPager.setAdapter(adapter);
                indicator.setViewPager(viewPager);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if (currentItem >= adapter.getCount()) {
                            currentItem = 0;
                        }
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(this, 4500);
                    }
                }, 4500);
            }

            @Override
            public void onFailure(@NonNull Call<List<Trending>> call, @NonNull Throwable t) {
                Log.d("fail", "fail to load data");
            }
        });
    }
}
