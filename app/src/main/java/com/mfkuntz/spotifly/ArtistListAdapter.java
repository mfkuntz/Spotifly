package com.mfkuntz.spotifly;

import android.content.ClipData;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Image;

public class ArtistListAdapter extends ArrayAdapter<Artist> {

    private static int Pixels = 0;

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

            v.setTag(artist.id);

            if (artist.images.size() == 0){
                return v;
            }

            int size = dpToPx(60);

            String imgUrl = "";
            //todo this only downloads the first image that fits, probably the biggest one
            for (Image image : artist.images){
                if (image.height >= size){
                    imgUrl = image.url;
                    break;
                }
            }
            //todo handle case where image is smaller than requested size

            ImageView imageView = (ImageView) v.findViewById(R.id.list_item_artist_image);

            Picasso.with(getContext())
                    .load(imgUrl)
                    .resize(size, size)
                    .centerCrop()
                    .into(imageView);

        }

        return v;
    }

    protected int dpToPx(int dp){

        if (Pixels == 0) {

            DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();

            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);

            Pixels = (int) Math.ceil(px);

            Log.w("dpToPx", "CALC");

        }

        return Pixels;
    }
}


