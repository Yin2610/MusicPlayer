package com.example.week5b;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        return view;
        }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) { ;
        if(index == 1) //When Aesthetic Blue/Purple theme is selected
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        else if(index == 2) //When Dark theme is selected
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}