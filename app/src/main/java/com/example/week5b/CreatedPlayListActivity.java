package com.example.week5b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CreatedPlayListActivity extends AppCompatActivity {
    RecyclerView songListView;
    SongAdapter songAdapter;
    SharedPreferences sharedPreferences;
    ArrayList<Song> songList = new ArrayList<>();
    String songListName;
    TextView txtPlaylist;
    ImageButton btnRemoveSong;
    ImageButton btnPlaySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_play_list);

        txtPlaylist = findViewById(R.id.txtPlaylist);
        btnPlaySong = findViewById(R.id.btnPlaySong);
        btnRemoveSong = findViewById(R.id.btnRemoveSong);
        try {
            Bundle songListData = this.getIntent().getExtras(); //get songList as a bundle
            songListName = songListData.getString("playlistName"); //retrieve songList name from the bundle
        }
        catch (Exception e)
        {
            Log.d("temasek", "No bundle received in CreatedPlayListActivity.");
        }
        txtPlaylist.setText(songListName);
        sharedPreferences = getSharedPreferences("playList", MODE_PRIVATE);

        String albums = sharedPreferences.getString(songListName, ""); /* Assign the stored playlist json string to String type albums
                                                                            1st parameter is the key to the json value that we want to retrieve
                                                                            2nd parameter is default value null for when no song is added to songList yet */
        if(!albums.equals("")) //if albums contains strings
        {
            //retrieve playList json from SharedPreferences and put into arrayList playList
            TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>() {};
            Gson gson = new Gson();
            songList = gson.fromJson(albums, token.getType());
            Log.d("sad", songList.toString());
        }

        songListView = findViewById(R.id.recyclerView);

        songAdapter = new SongAdapter(songList, songListName); // apply songAdapter to songList in PlaySongActivity
        songListView.setAdapter(songAdapter);
        songListView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void deletePlaylist(View view)
    {
        //method for deleting the whole playlist, called when Delete Playlist button is clicked
        sharedPreferences.edit().remove(songListName).commit();
        Toast.makeText(this, songListName+" is deleted.", Toast.LENGTH_SHORT).show();
        goToPlaylistFragment(view);
    }

    public void goToPlaylistFragment(View view)
    {
        //method to go to PlaylistFragment from CreatedPlaylistActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("itemSelected", "playlistFragment");
        startActivity(intent);
    }

    public void goToHomeFragment(View view)
    {
        //method to go to HomeFragment from CreatedPlaylistActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("itemSelected", "homeFragment");
        startActivity(intent);
    }
}