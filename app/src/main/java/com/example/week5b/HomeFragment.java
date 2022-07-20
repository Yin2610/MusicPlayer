package com.example.week5b;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    SongCollection songCollection = new SongCollection();


    public HomeFragment() {
        // Required empty public constructor
    }

//    public void sendDataToPlaySongActivity(int index)
//    {
//        Intent intent = new Intent(getActivity(), PlaySongActivity.class);
//        intent.putExtra("index", index);
//        startActivity(intent);
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        String[] songs = {"Perfect", "Memories", "Heather", "Treat you better"}; //song array to record the songs in the app
        // Inflate the layout for this fragment
    //    ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        //LinearLayout recentLayout = root.findViewById(R.id.recentLayout);
//        recentLayout.setOnTouchListener(new View.setOnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Whatever
//            }
//        });
        //recentLayout.setOnClickListener(new View.OnClickListener(){

//            @Override
//            public void onClick(View myView) {
//                Toast.makeText(getView().getContext(), ""+myView.getId(), Toast.LENGTH_SHORT).show();
////                String resourceId = getResources().getResourceName(myView.getId());
////                Log.d("temasek", "resourceId " + resourceId);
////                resourceId = resourceId.substring(resourceId.indexOf("/") + 1);
////                int currentArrayIndex = songCollection.searchSongById(resourceId);
////                Log.d("temasek", "resourceId " + resourceId);
////                Log.d("temasek", "The id of the pressed image button is:" + currentArrayIndex);
////                sendDataToPlaySongActivity(currentArrayIndex);
////                Intent intent = new Intent(getActivity(), PlaySongActivity.class);
////                intent.putExtra("index", myView.getId());
////                startActivity(intent);
//            }
//        });
//        return root;
//    }


//
//    public void handleSelection(View myView)
//    {
//        String resourceId = getResources().getResourceName(myView.getId());
//        Log.d("temasek", "resourceId " + resourceId);
//        resourceId = resourceId.substring(resourceId.indexOf("/") + 1);
//        int currentArrayIndex = songCollection.searchSongById(resourceId);
//        Log.d("temasek", "resourceId " + resourceId);
//        Log.d("temasek", "The id of the pressed image button is:" + currentArrayIndex);
//        sendDataToPlaySongActivity(currentArrayIndex);
//    }
//
//    public void search(View myView)
//    {
//
//    }
//
//    public void setHomeTab(long id)
//    {
//
//    }
}