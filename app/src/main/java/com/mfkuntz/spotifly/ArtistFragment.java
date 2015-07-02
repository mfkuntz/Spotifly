package com.mfkuntz.spotifly;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Artists;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ArtistFragment extends Fragment {

    private ArtistListAdapter artistAdapter;

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




        artistAdapter = new ArtistListAdapter(
                getActivity(),
                R.layout.list_item_artist,
                new ArrayList<Artist>()
        );

        ListView list = (ListView) rootView.findViewById(R.id.artist_list);
        list.setAdapter(artistAdapter);


        AdapterView.OnItemClickListener listItemClickedHandler = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = (String) view.getTag();

                Intent artistViewIntent = new Intent(getActivity(), ArtistView.class);
                startActivity(artistViewIntent);
            }
        };

        list.setOnItemClickListener(listItemClickedHandler);


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

                artistAdapter.clear();
                artistAdapter.addAll(artistsPager.artists.items);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(LOG_TAG, "Could not load search");
            }
        });
    }




}



