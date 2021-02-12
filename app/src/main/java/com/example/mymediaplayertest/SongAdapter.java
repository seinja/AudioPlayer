package com.example.mymediaplayertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(@NonNull Context context , int resource , @NonNull List<Song> objects){
        super(context,resource,objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song,null);

        TextView songTitle = convertView.findViewById(R.id.songTitle);
        TextView artistTitle = convertView.findViewById(R.id.songArtist);

        Song song = getItem(position);
        artistTitle.setText(song.getArtist());
        songTitle.setText(song.getTitle());
        return convertView;
    }
}
