package com.mfkuntz.spotifly;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;

public class TrackListAdapter extends ArrayAdapter<Track> {

    private static int Pixels = 0;

    public TrackListAdapter(Context context, int resource, List<Track> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent){
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_album, null);
        }

        Track track = getItem(position);

        if (track != null){

            TextView temp = (TextView) v.findViewById(R.id.list_item_track_textview);
            temp.setText(track.name + "\n" + track.album.name);

            v.setTag(track.id);

            if (track.album.images.size() == 0){
                return v;
            }

            int size = dpToPx(60);

            String imgUrl = "";
            //todo this only downloads the first image that fits, probably the biggest one
            for (Image image : track.album.images){
                if (image.height >= size){
                    imgUrl = image.url;
                    break;
                }
            }
            //todo handle case where image is smaller than requested size

            ImageView imageView = (ImageView) v.findViewById(R.id.list_item_album_image);

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


