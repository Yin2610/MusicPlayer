package com.example.week5b;

public class Song {
    private String id;
    private String title;
    private String artist;
    private String fileLink;
    private double songLength;
    private int drawable;

    public Song(String id, String title, String artist, String fileLink, double songLength, int drawable)
    {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.fileLink = fileLink;
        this.songLength = songLength;
        this.drawable = drawable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getFileLink() {
        return fileLink;
    }

    public double getSongLength() {
        return songLength;
    }

    public int getDrawable() {
        return drawable;
    }
}
