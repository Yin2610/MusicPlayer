package com.example.week5b;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlaylistFragment extends Fragment {

    public PlaylistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        String[] playlistsMenus = {"Create new playlist", "Downloaded", "Favourites"};

        ListView listView = view.findViewById(R.id.playlistsMenus);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                playlistsMenus
        );
        listView.setAdapter(listViewAdapter);

        // Inflate the layout for this fragment
        return view;
    }
}