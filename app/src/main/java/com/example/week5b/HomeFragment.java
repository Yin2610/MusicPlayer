package com.example.week5b;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    SongCollection songCollection = new SongCollection();

    public HomeFragment() {
        // Required empty public constructor
    }

    public void sendDataToPlaySongActivity(int index)
    {
        //Sending index to PlaySongActivity to indicate which song to play
        Toast.makeText(getActivity(), "home", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        for (int i = 0; i < songCollection.songs.length; i++)
        {
            String imageId = "S100";
            imageId = imageId + (i+1);
            ImageView imgView = root.findViewById(getResources().getIdentifier(imageId, "id", getContext().getPackageName()));
            Picasso.with(getContext()).load(songCollection.songs[i].getDrawable()).into(imgView);
        }

        ConstraintLayout recentLayout = root.findViewById(R.id.recentLayout);

        recentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View myView) {
                //On clicking a song image button to go to PlaySongActivity
                String resourceId = getResources().getResourceName(myView.getId());
                Log.d("temasek", "resourceId " + resourceId);
                resourceId = resourceId.substring(resourceId.indexOf("/") + 1);
                int currentArrayIndex = songCollection.searchSongById(resourceId);
                Log.d("temasek", "resourceId " + resourceId);
                Log.d("temasek", "The id of the pressed image button is:" + currentArrayIndex);
                sendDataToPlaySongActivity(currentArrayIndex);
            }
        });
        return root;
    }
}