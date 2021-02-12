package com.example.mymediaplayertest;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView startTime, endTime, songTitle, songName;
    SeekBar progressBarr, volumeBarr;
    Button btnPlay;

    MediaPlayer musicPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        //Song song = (Song) getIntent().getSerializableExtra("song");


        startTime = findViewById(R.id.timeStart);
        endTime = findViewById(R.id.timeEnd);

        songTitle = findViewById(R.id.songTitle);
        songName = findViewById(R.id.songArtist);

        progressBarr = findViewById(R.id.progressBarr);
        volumeBarr = findViewById(R.id.volumeBarr);

        btnPlay = findViewById(R.id.playAndStopButton);

        //songTitle.setText(song.getTitle());
        //songName.setText(song.getArtist());


        btnPlay.setOnClickListener(this);




        musicPlayer = MediaPlayer.create(this, R.raw.i_try);
        /*
        musicPlayer = new MediaPlayer();
        try {
            musicPlayer.setDataSource(song.getPath());
        } catch (IOException e) {
            Log.d("Path ERRROR", "Немогу найти путь к фалу");
            e.printStackTrace();
        }

         */

        String duration = milliSecToString(musicPlayer.getDuration());
        endTime.setText(duration);
        musicPlayer.setLooping(true);
        musicPlayer.seekTo(0);
        musicPlayer.setVolume(0.5f, 0.5f);

        volumeBarr.setProgress(50);
        volumeBarr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 100f;
                musicPlayer.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        progressBarr.setMax(musicPlayer.getDuration());
        progressBarr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {

                    musicPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (musicPlayer != null) {
                    if (musicPlayer.isPlaying()) {
                        try {
                            final double current = musicPlayer.getCurrentPosition();
                            final String elapsedTime = milliSecToString((int) current);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    startTime.setText(elapsedTime);
                                    progressBarr.setProgress((int) current);
                                }
                            });
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                            Log.d("Thread ERROR", "Ошибка в потоке прогресс бара");
                        }
                    }
                }
            }
        }).start();

    }

    public String milliSecToString(int time) {
        String elapsedTime = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;
        elapsedTime = min + ":";
        if (sec < 10) {
            elapsedTime += "0";
        }
        elapsedTime += sec;
        return elapsedTime;

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.playAndStopButton) {
            if (musicPlayer.isPlaying()) {
                musicPlayer.pause();
                btnPlay.setText("PLAY");
            } else {
                musicPlayer.start();
                btnPlay.setText("STOP");
            }
        }
    }
}