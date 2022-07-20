package com.example.week5b;

public class SongCollection {
    private Song songs[] = new Song[3];
    public SongCollection()
    {
        Song theWayYouLookTonight = new Song("S1001",
                "1. The Way You Look Tonight",
                "Michael Buble",
                "https://p.scdn.co/mp3-preview/a9b5a59ea8d4b914309fde92b3d73fed3c172f7b?cid=2afe87a64b0042dabf51f37318616965",
                4.66,
                R.drawable.michael_buble_collection);

        Song billieJean = new Song("S1002",
                "2. Billie Jean",
                "Michael Jackson",
                "https://p.scdn.co/mp3-preview/c0c01b622fac0397573c121a2024dff65d0161a3?cid=2afe87a64b0042dabf51f37318616965",
                5.97,
                R.drawable.billie_jean);

        Song ed = new Song("S1003",
                "3. One",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/3bd5770260a31d2791c2b6a201f5d1147ff23367?cid=2afe87a64b0042dabf51f37318616965",
                4.9,
                R.drawable.photograph);

        songs[0] = theWayYouLookTonight;
        songs[1] = billieJean;
        songs[2] = ed;
    }

    public int searchSongById(String id)
    {
        for (int index=0; index < songs.length; index++)
        {
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
        return songs[currentSongId];
    }

    public int getNextSong(int currentSongIndex)
    {
        if(currentSongIndex >= songs.length-1)
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
