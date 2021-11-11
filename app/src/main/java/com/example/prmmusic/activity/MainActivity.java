package com.example.prmmusic.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.prmmusic.R;
import com.example.prmmusic.fragment.MainFragment;
import com.example.prmmusic.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        MenuItem.OnActionExpandListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, new MainFragment(), MainFragment.TAG);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        menuItem.setOnActionExpandListener(this);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager()
                .findFragmentByTag(SearchFragment.TAG);
        if (searchFragment != null) {
            searchFragment.searchTuKhoaBaiHat(query);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        Fragment mainFragment = getSupportFragmentManager().findFragmentByTag(MainFragment.TAG);
        Fragment searchFragment = getSupportFragmentManager().findFragmentByTag(SearchFragment.TAG);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
        if (searchFragment == null) {
            transaction.add(R.id.fragment_container, new SearchFragment(), SearchFragment.TAG);
        } else {
            transaction.show(searchFragment);
        }
        transaction.commit();
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        Fragment searchFragment = getSupportFragmentManager().findFragmentByTag(SearchFragment.TAG);
        Fragment mainFragment = getSupportFragmentManager().findFragmentByTag(MainFragment.TAG);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (searchFragment != null) {
            transaction.hide(searchFragment);
            ((SearchFragment) searchFragment).clearResult();
        }
        if (mainFragment == null) {
            transaction.add(R.id.fragment_container, new MainFragment(), MainFragment.TAG);
        } else {
            transaction.show(mainFragment);
        }
        transaction.commit();
        return true;
    }
}