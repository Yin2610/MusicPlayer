package com.example.week5b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    PlaylistFragment playlistFragment = new PlaylistFragment();
    ExploreFragment exploreFragment = new ExploreFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] songs = {"Perfect", "Memories", "Heather", "Treat you better"};
//        AutoCompleteTextView txtBoxSearch = findViewById(R.id.searchView);
//        ArrayAdapter<String> arrayAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, songs);
//        txtBoxSearch.setAdapter(arrayAdapt);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    public void sendDataToActivity(int index)
    {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void handleSelection(View myView)
    {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        String resourceId = getResources().getResourceName(myView.getId());
        Log.d("temasek", "resourceId " + resourceId);
        resourceId = resourceId.substring(resourceId.indexOf("/") + 1);
        int currentArrayIndex = songCollection.searchSongById(resourceId);
        Log.d("temasek", "resourceId " + resourceId);
        Log.d("temasek", "The id of the pressed image button is:" + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);
    }

    public void search(View myView)
    {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.home:
                androidx.fragment.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.fragment_container, homeFragment);
                transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction1.commit();
                return true;
            case R.id.playlists:
                androidx.fragment.app.FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.fragment_container, playlistFragment);
                transaction2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction2.commit();
                return true;
            case R.id.explore:
                androidx.fragment.app.FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                transaction3.replace(R.id.fragment_container, exploreFragment);
                transaction3.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction3.commit();
                return true;
            case R.id.settings:
                androidx.fragment.app.FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                transaction4.replace(R.id.fragment_container, settingsFragment);
                transaction4.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction4.commit();
                return true;
        }
        return false;
    }
}