package com.example.week5b;

import java.util.ArrayList;

public class SongCollection {
    public Song[] songs = new Song[8];
//    ArrayList<Song> songs = new ArrayList<>();
    public SongCollection()
    {
        Song santaBaby = new Song("S1001",
                "Santa Baby",
                "Michael Buble",
                "https://p.scdn.co/mp3-preview/d977058ee3d510b12f695a9cfe173cc2b5b10132?cid=2afe87a64b0042dabf51f37318616965",
                3.86,
                "https://i.scdn.co/image/ab67616d0000b2730211d806261a42bf2bb7ebd7",
                "Jazz"
                );

        Song billieJean = new Song("S1002",
                "Billie Jean",
                "Michael Jackson",
                "https://p.scdn.co/mp3-preview/c0c01b622fac0397573c121a2024dff65d0161a3?cid=2afe87a64b0042dabf51f37318616965",
                5.97,
                "https://i.scdn.co/image/ab67616d0000b2734121faee8df82c526cbab2be",
                "Disco");

        Song one = new Song("S1003",
                "One",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/3bd5770260a31d2791c2b6a201f5d1147ff23367?cid=2afe87a64b0042dabf51f37318616965",
                4.9,
                "https://i.scdn.co/image/ab67616d0000b273407981084d79d283e24d428e",
                "Pop");

        Song needToKnow = new Song("S1004",
                "Need to Know",
                "Doja Cat",
                "https://p.scdn.co/mp3-preview/c707247354131537f5d7b0935f38db827711e76c?cid=2afe87a64b0042dabf51f37318616965",
                3.51,
                "https://i.scdn.co/image/ab67616d0000b27347315d2919e6e8173ac5ca4a",
                "Pop");

        Song chunky = new Song("S1005",
                "Chunky",
                "Bruno Mars",
                "https://p.scdn.co/mp3-preview/2f68cadfee4a33e3a7d0d4248745fe1c914f3a9f?cid=2afe87a64b0042dabf51f37318616965",
                3.12,
                "https://i.scdn.co/image/ab67616d0000b273232711f7d66a1e19e89e28c5",
                "R&B");

        Song leaveTheDoorOpen = new Song("S1006",
                "Leave The Door Open",
                "Bruno Mars",
                "https://p.scdn.co/mp3-preview/6e905ca8273bd2c5464c35d0ce10da306a86b11b?cid=2afe87a64b0042dabf51f37318616965",
                4.03,
                "https://i.scdn.co/image/ab67616d0000b273fcf75ead8a32ac0020d2ce86",
                "R&B");

        Song sleepAtNight = new Song("S1007",
                "Sleep At Night",
                "Chris Brown",
                "https://p.scdn.co/mp3-preview/f5353e09a54cec6c71e99b0480c59a65e69c7143?cid=2afe87a64b0042dabf51f37318616965",
                2.24,
                "https://i.scdn.co/image/ab67616d0000b2739a494f7d8909a6cc4ceb74ac",
                "R&B");

        Song iAmThatGirl = new Song("S1008",
                "I'm That Girl",
                "Beyoncé",
                "https://p.scdn.co/mp3-preview/c7cece6b1b9cb3637fc48924f23baf9c7e1ec15c?cid=2afe87a64b0042dabf51f37318616965",
                3.47,
                "https://i.scdn.co/image/ab67616d0000b2730e58a0f8308c1ad403d105e7",
                "R&B");

        songs[0] = santaBaby;
        songs[1] = billieJean;
        songs[2] = one;
        songs[3] = needToKnow;
        songs[4] = chunky;
        songs[5] = leaveTheDoorOpen;
        songs[6] = sleepAtNight;
        songs[7] = iAmThatGirl;
//        songs.add(santaBaby);
//        songs.add(billieJean);
//        songs.add(one);
//        songs.add(needToKnow);
//        songs.add(chunky);
//        songs.add(leaveTheDoorOpen);
//        songs.add(sleepAtNight);
//        songs.add(iAmThatGirl);
    }

    public int searchSongById(String id)
    {
//        for (int index=0; index < songs.size(); index++)
        for (int index=0; index < songs.length; index++)
        {
//            Song tempSong = songs.get(index);
            Song tempSong = songs[index];
            if(tempSong.getId().equals(id))
            {
                return index;
            }
        }
        return -1;
    }

    public Song getCurrentSong(int currentSongId)
    {
//        return songs.get(currentSongId);
        return songs[currentSongId];
    }

    public int getNextSong(int currentSongIndex)
    {
        if(currentSongIndex >= songs.length-1)
//        if(currentSongIndex >= songs.size()-1)
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
