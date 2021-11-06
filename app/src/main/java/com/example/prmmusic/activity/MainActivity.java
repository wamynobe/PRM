package com.example.prmmusic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchBaiHatAdapter searchBaiHatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }
 private void SearchTuKhoaBaiHat(String query){
     DataService dataService = APIService.getService();
     Call<List<Song>> callback = dataService.getSongsByKeyword(query);
     callback.enqueue(new Callback<List<Song>>() {
         @Override
         public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
             ArrayList<Song> mangbaihat = (ArrayList<Song>) response.body();
             if(mangbaihat.size()>0){
                 searchBaiHatAdapter = new SearchBaiHatAdapter(MainActivity.this,mangbaihat);
                 LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(getApplicationContext());
             }
         }

         @Override
         public void onFailure(Call<List<Song>> call, Throwable t) {

         }
     });
 }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}