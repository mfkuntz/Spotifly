package com.mfkuntz.spotifly;

import android.content.ClipData;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;

public class ArtistListAdapter extends ArrayAdapter<Artist> {

    public ArtistListAdapter(Context context, int resource, List<Artist> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent){
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_artist, null);
        }

        Artist artist = getItem(position);

        if (artist != null){

            TextView temp = (TextView) v.findViewById(R.id.list_item_artist_textview);
            temp.setText(artist.name);


            ImageView image = (ImageView) v.findViewById(R.id.list_item_artist_image);

            if (artist.images.size() == 0){
                return v;
            }

            String imgUrl = artist.images.get(artist.images.size() - 1).url;

            Picasso.with(getContext())
                    .load(imgUrl)
                    .resize(45, 45)
                    .centerCrop()
                    .into(image);

        }

        return v;
    }
}
