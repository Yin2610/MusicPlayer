package com.example.week5b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    PlaylistFragment playlistFragment = new PlaylistFragment();
    ExploreFragment exploreFragment = new ExploreFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    SearchFragment searchFragment = new SearchFragment();
    SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home); //By default, select home navigation menu
        try
        {
            //Retrieve which screen leads here in bundle and set the appropriate navigation menu item
            Bundle itemSelectedData = this.getIntent().getExtras();
            String itemSelected = itemSelectedData.getString("itemSelected");
            if(itemSelected.equals("playlistFragment"))
            {
                bottomNavigationView.setSelectedItemId(R.id.playlists);
            }
        }
        catch(Exception e)
        {
            Log.d("temasek", "No bundle received in MainActivity.");
        }
    }

    public void sendDataToActivity(int index)
    {
        //Method to move to PlaySongActivity
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("fromWhichScreen", "MainActivity");
        startActivity(intent);
    }

    public void handleSelection(View myView)
    {
        //Handling selection of a song
        String resourceId = getResources().getResourceName(myView.getId());
        Log.d("temasek", "resourceId " + resourceId);
        resourceId = resourceId.substring(resourceId.indexOf("/") + 1);
        int currentArrayIndex = songCollection.searchSongById(resourceId);
        Log.d("temasek", "resourceId " + resourceId);
        Log.d("temasek", "The id of the pressed image button is:" + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);
    }

    public void goToWeb(View view)
    {
        //Accessed from ExploreFragment to go to a singer's biography when his or her image is clicked
        String url = view.getTag().toString();
        Uri webPage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
        startActivity(webIntent);
    }

    public void searchSong(View view)
    {
        //Accessed from HomeFragment to go to SearchFragment when the search button is clicked
        ImageButton btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToFragment(searchFragment);
            }
        });
    }

    public void goToFragment(Fragment fragmentName)
    {
        //A method to go to Fragment for efficiency, bundleData can be artist name or song genre
        androidx.fragment.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.replace(R.id.fragment_container, fragmentName);
        transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction1.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        //For selecting bottom navigation menu
        switch(item.getItemId())
        {
            case R.id.home:
                goToFragment(homeFragment);
                return true;
            case R.id.playlists:
                goToFragment(playlistFragment);
                return true;
            case R.id.explore:
                goToFragment(exploreFragment);
                return true;
            case R.id.settings:
                goToFragment(settingsFragment);
                return true;
        }
        return false;
    }
}