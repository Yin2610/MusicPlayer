package com.example.week5b;

import java.util.ArrayList;

public class SongCollection {
    ArrayList<Song> songs = new ArrayList<>();
    public SongCollection()
    {
        Song santaBaby = new Song("S1001",
                "Santa Baby",
                "Michael Buble",
                "https://p.scdn.co/mp3-preview/d977058ee3d510b12f695a9cfe173cc2b5b10132?cid=2afe87a64b0042dabf51f37318616965",
                3.86,
                R.drawable.michael_buble_collection,
                "Jazz"
                );

        Song billieJean = new Song("S1002",
                "Billie Jean",
                "Michael Jackson",
                "https://p.scdn.co/mp3-preview/c0c01b622fac0397573c121a2024dff65d0161a3?cid=2afe87a64b0042dabf51f37318616965",
                5.97,
                R.drawable.billie_jean,
                "Disco");

        Song one = new Song("S1003",
                "One",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/3bd5770260a31d2791c2b6a201f5d1147ff23367?cid=2afe87a64b0042dabf51f37318616965",
                4.9,
                R.drawable.photograph,
                "Pop");

        Song needToKnow = new Song("S1004",
                "Need to Know",
                "Doja Cat",
                "https://p.scdn.co/mp3-preview/c707247354131537f5d7b0935f38db827711e76c?cid=2afe87a64b0042dabf51f37318616965",
                3.51,
                R.drawable.need_to_know_doja_cat,
                "Pop");

        Song chunky = new Song("S1005",
                "Chunky",
                "Bruno Mars",
                "https://p.scdn.co/mp3-preview/2f68cadfee4a33e3a7d0d4248745fe1c914f3a9f?cid=2afe87a64b0042dabf51f37318616965",
                3.12,
                R.drawable.chunky_bruno_mars,
                "R&B");

        Song leaveTheDoorOpen = new Song("S1006",
                "Leave The Door Open",
                "Bruno Mars",
                "https://p.scdn.co/mp3-preview/6e905ca8273bd2c5464c35d0ce10da306a86b11b?cid=2afe87a64b0042dabf51f37318616965",
                4.03,
                R.drawable.leave_the_door_open,
                "R&B");

        Song sleepAtNight = new Song("S1007",
                "Sleep At Night",
                "Chris Brown",
                "https://p.scdn.co/mp3-preview/f5353e09a54cec6c71e99b0480c59a65e69c7143?cid=2afe87a64b0042dabf51f37318616965",
                2.24,
                R.drawable.problem_with_you,
                "R&B");

        Song iAmThatGirl = new Song("S1008",
                "I'm That Girl",
                "Beyoncé",
                "https://p.scdn.co/mp3-preview/c7cece6b1b9cb3637fc48924f23baf9c7e1ec15c?cid=2afe87a64b0042dabf51f37318616965",
                3.47,
                R.drawable.i_am_that_girl,
                "R&B");

        songs.add(santaBaby);
        songs.add(billieJean);
        songs.add(one);
        songs.add(needToKnow);
        songs.add(chunky);
        songs.add(leaveTheDoorOpen);
        songs.add(sleepAtNight);
        songs.add(iAmThatGirl);
    }

    public int searchSongById(String id)
    {
        for (int index=0; index < songs.size(); index++)
        {
            Song tempSong = songs.get(index);
            if(tempSong.getId().equals(id))
            {
                return index;
            }
        }
        return -1;
    }

    public Song getCurrentSong(int currentSongId)
    {
        return songs.get(currentSongId);
    }

    public int getNextSong(int currentSongIndex)
    {
        if(currentSongIndex >= songs.size()-1)
        {
            return currentSongIndex;
        }
        else
        {
            return currentSongIndex + 1;
        }
    }

    public int getPrevSong(int currentSongIndex)
    {
        if(currentSongIndex <= 0)
        {
            return currentSongIndex;
        }
        else
        {
            return currentSongIndex - 1;
        }
    }
}
