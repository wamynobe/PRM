package com.example.prmmusic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.prmmusic.R;
import com.example.prmmusic.adapter.SearchBaiHatAdapter;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchBaiHatAdapter searchBaiHatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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