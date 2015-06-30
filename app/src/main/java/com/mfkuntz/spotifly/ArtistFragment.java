package com.mfkuntz.spotifly;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ArtistFragment extends Fragment {

    private ArrayAdapter<String> artistAdapter;

    public ArtistFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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



}
