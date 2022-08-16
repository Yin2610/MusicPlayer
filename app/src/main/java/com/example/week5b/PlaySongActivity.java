package com.example.week5b;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PlaySongActivity extends AppCompatActivity {

    private String title = "";
    private String artist = "";
    private String fileLink = "";
    private String drawable;
    private int currentIndex = -1;

    private MediaPlayer player = new MediaPlayer();
    private ImageButton btnPlayPause = null;
    private SongCollection songCollection = new SongCollection();
    private SongCollection originalSongCollection = new SongCollection();
    List<Song> shuffleList = Arrays.asList(songCollection.songs);

    static ArrayList<Song> playlist = new ArrayList<>();
    static SharedPreferences sharedPreferences;

    SeekBar seekBar;
    Handler handler = new Handler();
    String playlistName;
    String fromWhichScreen;

    ImageButton btnRepeat;
    ImageButton btnShuffle;
    boolean repeat = false;
    boolean shuffle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        sharedPreferences = getSharedPreferences("playList", MODE_PRIVATE); /* creating a file called playList
                                                                                    and give access to only MyMusic app */

        btnPlayPause = findViewById(R.id.btnPlayPause);
        seekBar = findViewById(R.id.seekBar);
        btnRepeat = findViewById(R.id.btnRepeat);
        btnShuffle = findViewById(R.id.btnShuffle);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //for when the seekbar thumb is scrubbed to a certain point
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //for when the seekbar thumb is touched
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //for when the seekbar thumb is let go
                if (player != null && player.isPlaying())
                {
                    player.seekTo(seekBar.getProgress()); //to set the player to play from the point where the seekbar progress is
                }
            }
        });

        try {
            Bundle songData = this.getIntent().getExtras(); //get song data as a bundle
            currentIndex = songData.getInt("index"); //retrieve the song index from the bundle
            fromWhichScreen = songData.getString("fromWhichScreen"); //retrieve the previous screen information

            displaySongBasedOnIndex(currentIndex);
            playSong(fileLink);
        }
        catch (Exception e)
        {
            Log.d("temasek", "No bundle received in PlaySongActivity.");
        }
    }

    Runnable p_bar = new Runnable() {
        @Override
        public void run()
        {
            if (player != null && player.isPlaying())
            {
                seekBar.setProgress(player.getCurrentPosition());
            }
            handler.postDelayed(this, 1000);
        }
    };

    public void displaySongBasedOnIndex(int currentIndex)
    {
        Song song = songCollection.getCurrentSong(currentIndex);
        title = song.getTitle();
        artist = song.getArtist();
        fileLink = song.getFileLink();
        drawable = song.getDrawable();
        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);
        TextView txtArtist = findViewById(R.id.txtArtist);
        txtArtist.setText(artist);
        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        Picasso.with(this).load(drawable).into(iCoverArt);
    }

    public Song retrieveSongObject(View view)
    {
        return songCollection.getCurrentSong(currentIndex);
    }

    public void addToPlaylist(View view)
    {
        Song song = retrieveSongObject(view);
        if(view == findViewById(R.id.btnShuffle))
        {
            Toast.makeText(this, "fav", Toast.LENGTH_SHORT).show();
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.create();
        dialog.setTitle(Html.fromHtml("<font color='black'>Choose playlist to add song:</font>"));
        try {
            //get the keys(playlist names) from SharedPreferences and add to the playlists variable
            Map<String, ?> keys = sharedPreferences.getAll();
            String playlists[] = new String[keys.size()];
            int i = 0;
            for(Map.Entry<String, ?> entry: keys.entrySet())
            {
                playlists[i] = entry.getKey();
                i++;
            }
            if(playlists.length==0)
            {
                Toast.makeText(this, "Create a new playlist first.", Toast.LENGTH_SHORT).show();
                goToPlaylistFragment(view);
            }
            else
            {
                dialog.setMultiChoiceItems(playlists, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index, boolean isChecked)
                    {
                        if(isChecked)
                        {
                            playlist = new ArrayList<>();
                            playlistName = playlists[index];
                            String albums = sharedPreferences.getString(playlistName, ""); /* Assign the stored playList json string to String type albums
                                                                            1st parameter is the key to the json value that we want to retrieve
                                                                            2nd parameter is default value null for when no song is added to playlist yet */


                            Gson gson = new Gson();
                            if(!albums.isEmpty())
                            {
                                Log.d("temasek", playlistName);
                                TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>() {};
                                playlist = gson.fromJson(albums, token.getType());
                            }
                            //retrieve playList json from SharedPreferences and put into arrayList playList
                            if (playlistName != null)
                            {
                                playlist.add(song);
                                displayToastForPlaylist();
                                String json = gson.toJson(playlist); //change and store ArrayList type playlist to String type json
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(playlistName, json); /* write the json string into editor with the key "playList"
                                                                     so that it can be retrieved later when the app is closed and reopened*/
                                editor.apply();
                            }
                        }
                    }
                });
                dialog.show();
            }
        }
        catch (Exception e)
        {
            Log.d("temasek", "No file is created in sharedPreferences yet.");
        }
    }

    public void displayToastForPlaylist()
    {
        Toast.makeText(this, "Song is added to " + playlistName + ".", Toast.LENGTH_SHORT).show();
    }

    public void shareSong(View view)
    {
        //shareSong method is called when the share button is clicked
        Song song = retrieveSongObject(view);
        String songLink = song.getFileLink();
        String songTitle = song.getTitle();
        String artist = song.getArtist();
        String message = "Listen to " + songTitle + " by " + artist + ".\n" + songLink;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void playSong(String songUrl)
    {
        try {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            if(player != null && player.isPlaying())
            {
                seekBar.setMax(player.getDuration()); //get the song duration and set it to the maximum point of seekbar
            }
            handler.postDelayed(p_bar, 1000);
            gracefullyStopsWhenMusicEnds();

            btnPlayPause.setImageResource(R.drawable.pause_icon);
            setTitle(title);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void playOrPauseMusic(View view)
    {
        if(player.isPlaying())
        {
            //if player is playing
            player.pause();
            btnPlayPause.setImageResource(R.drawable.play_icon);
        }
        else
        {
            player.start();
            handler.removeCallbacks(p_bar); //to regulate the delaying in 1 second, to prevent from several method calls resulting faster post delaying
            handler.postDelayed(p_bar, 1000);
            btnPlayPause.setImageResource(R.drawable.pause_icon);
        }
    }

    private void gracefullyStopsWhenMusicEnds()
    {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                if(repeat)
                {
                    playOrPauseMusic(null); //if repeat is on and the song ends, the playOrPauseMusic is run again to repeat
                }
                else
                {
                    Toast.makeText(getBaseContext(), "The song has ended.", Toast.LENGTH_LONG).show();
                    btnPlayPause.setImageResource(R.drawable.icon_play_circle);
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        //method for preventing app crash because of seekbar and player when back is pressed
        super.onBackPressed();
        if(player != null)
        {
            handler.removeCallbacks(p_bar);
            player.stop();
            player.release();
            player = null;
        }
    }

    public void backClicked(View view)
    {
        //Custom back button for user's smooth navigation
        if(player != null)
        {
            handler.removeCallbacks(p_bar);
            player.stop();
            player.release();
            player = null;
        }
        if (fromWhichScreen.equals("MainActivity"))
        {
            goToHomeFragment(view);
        }
        else
        {
            goToPlaylistFragment(view);
        }
    }

    public void goToHomeFragment(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("itemSelected", "homeFragment");
        startActivity(intent);
    }

    public void goToPlaylistFragment(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("itemSelected", "playlistFragment");
        startActivity(intent);
    }

    public void playNext(View view)
    {
        currentIndex = songCollection.getNextSong(currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void playPrev(View view)
    {
        currentIndex = songCollection.getPrevSong(currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void repeatSong(View view)
    {
        if (repeat)
        {
            btnRepeat.setImageResource(R.drawable.icon_repeat);
        }
        else
        {
            btnRepeat.setImageResource(R.drawable.icon_repeat_on);
        }
        repeat = !repeat; /* for inversing the boolean state of repeat when the repeat button is clicked
                            true -> false, false -> true */
    }

    public void shuffleSong(View view)
    {
        if (shuffle)
        {
            btnShuffle.setImageResource(R.drawable.shuffle_icon);
            songCollection = new SongCollection();
        }
        else
        {
            btnShuffle.setImageResource(R.drawable.shuffle_on);
            Collections.shuffle(shuffleList);
            shuffleList.toArray(songCollection.songs);
        }
        shuffle = !shuffle; /* for inversing the boolean state of shuffle when the repeat button is clicked
                            true -> false, false -> true */
    }
}