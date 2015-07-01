package com.mfkuntz.spotifly;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
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
        updateArtist();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ArrayList<String> temp = new ArrayList<>();
        temp.add("Test 1");
        temp.add("Test 2");
        temp.add("Test 3");

        artistAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_artist,
                R.id.list_item_artist_textview,
                temp
        );

        ListView list = (ListView) rootView.findViewById(R.id.artist_list);
        list.setAdapter(artistAdapter);

        return rootView;
    }

    private void updateArtist(){
        GetArtistDataTask task = new GetArtistDataTask();

        task.execute("Coldplay");
    }

    class GetArtistDataTask extends AsyncTask<String, Void, String[]> {


        @Override
        protected String[] doInBackground(String... params) {

            SpotifyApi api = new SpotifyApi();
            SpotifyService service = api.getService();

            service.searchArtists(params[0], new Callback<ArtistsPager>() {
                @Override
                public void success(ArtistsPager artistsPager, Response response) {
                    artistsPager.artists.items
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });


            return new String[0];
        }

        protected void onPostExecute(String[] results){
            artistAdapter.clear();
            artistAdapter.addAll(results);

            super.onPostExecute(results);
        }
    }

}

