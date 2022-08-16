package com.example.week5b;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {

    GridView searchGridView;
    SongCollection songCollection = new SongCollection();
    private List<Song> songList;
    SearchSongAdapter searchSongAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        searchGridView = root.findViewById(R.id.searchGridView);
        TextView txtSearchResult = root.findViewById(R.id.txtSearchResult);

        songList = Arrays.asList(songCollection.songs);
        searchSongAdapter = new SearchSongAdapter(songList);
        Resources r = getResources();
        int width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 410, r.getDisplayMetrics()));
        int height;
        if(songList.size() % 2 == 0)
        {
            height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (songList.size()/2)*250, r.getDisplayMetrics()));
        }
        else
        {
            height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, ((songList.size()+1)/2)*250, r.getDisplayMetrics()));
        }
        searchGridView.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        searchGridView.setAdapter(searchSongAdapter);

        SearchView searchView = root.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchString) {
                searchSongAdapter.getFilter().filter(searchString);
                return false;
            }
        });
        return root;
    }

    public class SearchSongAdapter extends BaseAdapter implements Filterable
    {
        private List<Song> songList;
        private List<Song> filteredSongList; //list returned when filtered

        public SearchSongAdapter(List<Song> songList)
        {
            this.songList = songList;
            this.filteredSongList = songList;
        }

        @Override
        public int getCount() {
            return filteredSongList.size();
        }

        @Override
        public Object getItem(int i) {
            return filteredSongList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int index, View view, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            View songItemView = inflater.inflate(R.layout.grid_item_song, parent, false);
            ImageView imageView = songItemView.findViewById(R.id.imgCover);
            TextView txtSong = songItemView.findViewById(R.id.txtSong);
            TextView txtArtistName = songItemView.findViewById(R.id.txtArtistName);

            Song song = filteredSongList.get(index);
            Picasso.with(getContext()).load(song.getDrawable()).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), PlaySongActivity.class);
                    String songId = filteredSongList.get(index).getId();
                    songId = songId.substring(songId.indexOf("/") + 1);
                    int index = songCollection.searchSongById(songId);
                    intent.putExtra("index", index);
                    intent.putExtra("fromWhichScreen", "MainActivity");
                    startActivity(intent);
                }
            });
            txtSong.setText(filteredSongList.get(index).getTitle());
            txtArtistName.setText(filteredSongList.get(index).getArtist());
            return songItemView;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String searchString = constraint.toString();
                    if(searchString.isEmpty())
                    {
                        filteredSongList = songList; //Set the search result view to all songs by default,
                                                    // when the user hasn't searched yet
                    }
                    else
                    {
                        List<Song> filteredList = new ArrayList<Song>();
                        for (int i = 0; i < songList.size(); i++)
                        {
                            if(songList.get(i).getTitle().toLowerCase().contains(searchString.toLowerCase()) ||
                                    songList.get(i).getArtist().toLowerCase().contains(searchString.toLowerCase()))
                            //if the string user search and the song title or artist or genre from songList match
                            {
                                filteredList.add(songList.get(i));
                            }
                        }
                        filteredSongList = filteredList;
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = filteredSongList;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    //Change the Search Result to show filtered song list
                    filteredSongList = (List<Song>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }
}