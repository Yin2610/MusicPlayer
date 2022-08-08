package com.example.week5b;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class PlaylistFragment extends Fragment {

    public static ArrayList<String> playlistsMenus = new ArrayList<>();
    private String playlistName;
    static SharedPreferences sharedPreferences;

    public PlaylistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        playlistsMenus = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        playlistsMenus.add("Create new playlist");

        try {
            //get the keys(playlist names) from SharedPreferences and add to the playlistsMenus
            sharedPreferences = getActivity().getSharedPreferences("playList", MODE_PRIVATE);
            Map<String, ?> keys = sharedPreferences.getAll();
            for(Map.Entry<String, ?> entry: keys.entrySet())
            {
                playlistsMenus.add(entry.getKey());
            }
        }
        catch (Exception e)
        {
            Log.d("temasek", "No file is created in sharedPreferences yet.");
        }

        ListView listView = view.findViewById(R.id.playlistsMenus);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                playlistsMenus
        );
        listView.setAdapter(listViewAdapter); //setting adapter for list view of showing playlist names

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int listViewIndex, long l) {
                if(listViewIndex == 0) //When Create New Playlist is clicked
                {
                    showCreateNewPlaylistDialog(listViewAdapter);
                }
                else //When a playlist is clicked to go to the CreatedPlaylistActivity
                {
                    playlistName = (String) listView.getItemAtPosition(listViewIndex); /*parameter is the index of listView item selected
                                                                                        and converted to String*/
                    Intent intent = new Intent(getActivity(), CreatedPlayListActivity.class);
                    intent.putExtra("playlistName", playlistName); //pass playlistName as Extra to specify which playlist
                    startActivity(intent);
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void showCreateNewPlaylistDialog(ArrayAdapter<String> listViewAdapter)
    {
        //Method for when Create New Playlist button is clicked
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(Html.fromHtml("<font color='black'>Create new playlist</font>"));
        final EditText txtPlaylistName = new EditText(getActivity());
        txtPlaylistName.setInputType(InputType.TYPE_CLASS_TEXT);
        txtPlaylistName.setText("Playlist Name");

        dialog.setView(txtPlaylistName);

        dialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                playlistName = txtPlaylistName.getText().toString();
                playlistsMenus.add(playlistName);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Toast.makeText(getActivity(), playlistName + " is created.", Toast.LENGTH_SHORT).show();
                editor.putString(playlistName, "");
                editor.apply();
                listViewAdapter.notifyDataSetChanged();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialog.show();
    }
}

