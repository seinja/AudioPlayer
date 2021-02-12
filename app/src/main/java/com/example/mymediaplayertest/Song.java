package com.example.mymediaplayertest;

import android.net.Uri;

import java.io.Serializable;

public class Song implements Serializable {
    private Uri songUri;
    private String title;
    private String artist;
    private String path;
    private String id;



    public Song(String title, String artist, String path){
        this.title = title;
        this.artist = artist;
        this.path = path;
    }

    public Song(Uri songUri){
        this.songUri = songUri;
    }

    public Song(String id, String data){
        this.id = id;
        this.path = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
