package com.example.prmmusic.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.adapter.SearchBaiHatAdapter;
import com.example.prmmusic.model.Song;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    public static final String TAG = "SearchFragment";
    private RecyclerView recyclerView;
    private TextView textViewNoResult;
    private ProgressBar progressCircular;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerviewSearchbaihat);
        textViewNoResult = view.findViewById(R.id.textViewkhongcodulieu);
        progressCircular = view.findViewById(R.id.progress_circular);
    }

    public void searchTuKhoaBaiHat(String query) {
        progressCircular.setVisibility(View.VISIBLE);
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.getSongsByKeyword(query);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(@NonNull Call<List<Song>> call,
                    @NonNull Response<List<Song>> response) {
                progressCircular.setVisibility(View.INVISIBLE);
                List<Song> songs = response.body();
                if (songs == null || songs.isEmpty()) {
                    textViewNoResult.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setAdapter(new SearchBaiHatAdapter(getActivity(), songs));
                    textViewNoResult.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Song>> call, @NonNull Throwable t) {
                Log.d("fail", "fail to load data");
            }
        });
    }

    public void clearResult() {
        recyclerView.setAdapter(new SearchBaiHatAdapter(getActivity(), new ArrayList<>()));
    }
}