package com.mfkuntz.spotifly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mfkuntz.spotifly.dataaccess.SpotifyFixer;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Track;


/**
 * A placeholder fragment containing a simple view.
 */
public class ArtistViewFragment extends Fragment {

    TrackListAdapter trackListAdapter;

    public ArtistViewFragment() {
    }

    @Override
    public void onStart(){
        super.onStart();

        loadAlbums();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_artist_view, container, false);

        ListView albumList = (ListView) v.findViewById(R.id.artist_album_list);


        trackListAdapter = new TrackListAdapter(
            getActivity(),
            R.layout.list_item_album,
            new ArrayList<Track>()
        );

        albumList.setAdapter(trackListAdapter);


        return v;
    }


    private void loadAlbums() {

        Intent intent = getActivity().getIntent();
        String message = intent.getStringExtra(Intent.EXTRA_TEXT);

        SpotifyFixer.getTopTracks(message, trackListAdapter);

    }



}
