package com.example.mymediaplayertest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1;

    ArrayList<Song> songArrayList;
    ListView songList;
    SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        songList = findViewById(R.id.songList);

        songArrayList = new ArrayList<Song>();


        songAdapter = new SongAdapter(this, R.layout.item_song, songArrayList);
        songList.setAdapter(songAdapter);


        if (ActivityCompat.checkSelfPermission(ListActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
        } else {
            //getSongs();
            Intent openMusic = new Intent(ListActivity.this, MainActivity.class);
            startActivity(openMusic);
        }

        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songArrayList.get(position);
                Intent openMusic = new Intent(ListActivity.this, MainActivity.class);
                openMusic.putExtra("song", song);
                startActivity(openMusic);
            }
        });


    }

    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getSongs();
            }
        }
    }

    private void getSongs(String query) {
        Cursor music = getContentResolver().query(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{ "_id", "_data" },
                "is_music != 0",
                null, null);

        songAdapter.clear();



        String path;

        while ( music.moveToNext() )
        {
            path = music.getString(1).toLowerCase();
            if ( path.contains(query) )
                songAdapter.add(new Song(music.getString(0) , music.getString(1)));
        }

        songAdapter.notifyDataSetChanged();


        }
    }

     */
}