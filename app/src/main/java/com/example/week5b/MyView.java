package com.example.week5b;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyView extends RecyclerView.ViewHolder
{
    public TextView txtPlaylistSong;
    public TextView txtPlaylistArtist;
    public ImageView imgPlaylistCover;
    public ImageButton btnPlaySong;
    public ImageButton btnRemoveSong;
    public MyView(@NonNull View itemView) {
        super(itemView);
        txtPlaylistSong = itemView.findViewById(R.id.txtPlaylistSong);
        txtPlaylistArtist = itemView.findViewById(R.id.txtPlaylistArtist);
        imgPlaylistCover = itemView.findViewById(R.id.imgPlaylistCover);
        btnPlaySong = itemView.findViewById(R.id.btnPlaySong);
        btnRemoveSong = itemView.findViewById(R.id.btnRemoveSong);
    }
}
