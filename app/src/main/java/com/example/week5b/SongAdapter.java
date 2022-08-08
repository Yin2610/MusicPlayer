package com.example.week5b;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SongAdapter extends RecyclerView.Adapter<MyView>
{
    List<Song> songs;
    Context context;
    SharedPreferences sharedPreferences;
    Gson gson = new Gson();
    SongCollection songCollection = new SongCollection();
    String songListName;
    ArrayList<Song> songList = new ArrayList<>();

    public SongAdapter(List<Song> songs, String songListName) {
        this.songs = songs;
        this.songListName = songListName;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context); //to render item_song.xml file
        View songView = inflater.inflate(R.layout.item_song, parent, false);
        MyView viewHolder = new MyView(songView);
        sharedPreferences = context.getSharedPreferences("playList", MODE_PRIVATE);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, @SuppressLint("RecyclerView") final int position) {
        Song song = songs.get(position);
        TextView txtArtist = holder.txtPlaylistArtist;
        txtArtist.setText(song.getArtist());
        TextView txtSong = holder.txtPlaylistSong;
        txtSong.setText(song.getTitle());
        int imageId = AppUtil.getImageIdFromDrawable(context, Integer.toString(song.getDrawable()));
        holder.imgPlaylistCover.setImageResource(imageId);
        holder.btnPlaySong.setTag(song.getId());
        holder.btnRemoveSong.setTag(position);
        holder.btnPlaySong.setOnClickListener(new View.OnClickListener() {
            //for setting onClickListener method to each song play button with the help of adapter
            @Override
                public void onClick(View view) {
                    String songID = holder.btnPlaySong.getTag().toString();
                    Intent intent = new Intent(context, PlaySongActivity.class);
                    int index = songCollection.searchSongById(songID);
                    intent.putExtra("index", index);
                    intent.putExtra("fromWhichScreen", "CreatedPlaylistActivity");
                    context.startActivity(intent);
            }
        });
        holder.btnRemoveSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String albums = sharedPreferences.getString(songListName, "");
                TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>() {};
                songList = gson.fromJson(albums, token.getType());
                Toast.makeText(context, songList.get(position).getTitle()+" is removed.", Toast.LENGTH_SHORT).show();
                songList.remove(position);
                String json = gson.toJson(songList); //change and store ArrayList type songList to String type json
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(songListName, json); /* write the json string into editor with the key of songListName
                                                                     so that it can be retrieved later when the app is closed and reopened*/
                editor.apply();
                notifyDataSetChanged();

                Intent intent = new Intent(context, CreatedPlayListActivity.class); //Reloading CreatedPlaylistActivity after a song is removed
                intent.putExtra("playlistName", songListName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}
