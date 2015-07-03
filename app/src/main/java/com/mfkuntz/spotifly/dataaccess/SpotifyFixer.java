package com.mfkuntz.spotifly.dataaccess;


import android.util.Log;

import com.mfkuntz.spotifly.TrackListAdapter;

import java.util.Hashtable;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Tracks;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
/**
 * Fixes a discrepency between the Spotify API and its wrapper
 */
public class SpotifyFixer {

    public static void getTopTracks(String artistID, final TrackListAdapter trackListAdapter){
        final String LOG_TAG = "SpotifyFixer";

        SpotifyApi api = new SpotifyApi();
        SpotifyService service = api.getService();

        Map<String, Object> options = new Hashtable<>();
        options.put("country", "US");

        service.getArtistTopTrack(artistID, options, new Callback<Tracks>() {
            @Override
            public void success(Tracks tracks, Response response) {
                trackListAdapter.clear();

                trackListAdapter.addAll(tracks.tracks);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(LOG_TAG, "Failed Serach");
            }
        });

    }

}
