package com.mfkuntz.spotifly;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Artists;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ArtistFragment extends Fragment {

    private ArrayAdapter<String> artistAdapter;

    public ArtistFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onStart(){
        super.onStart();

//        updateArtist("Coldplay");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);




        artistAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.list_item_artist,
                R.id.list_item_artist_textview,
                new ArrayList<String>()
        );

        ListView list = (ListView) rootView.findViewById(R.id.artist_list);
        list.setAdapter(artistAdapter);


        EditText textbox = (EditText) rootView.findViewById(R.id.artist_text);
        textbox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                updateArtist(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return rootView;
    }

    private void updateArtist(String artistName){
        final String LOG_TAG = this.getClass().getSimpleName();

        SpotifyApi api = new SpotifyApi();
        SpotifyService service = api.getService();

        service.searchArtists(artistName, new Callback<ArtistsPager>() {
            @Override
            public void success(ArtistsPager artistsPager, Response response) {
                ArrayList<String> artistNames = new ArrayList<>();

                for(Artist artist : artistsPager.artists.items){
                    artistNames.add(artist.name);
                }
                artistAdapter.clear();
                artistAdapter.addAll(artistNames);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(LOG_TAG, "Could not load search");
            }
        });
    }




}



