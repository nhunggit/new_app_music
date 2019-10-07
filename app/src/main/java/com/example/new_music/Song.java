package com.example.new_music;

public class Song {
    private int id;

    private  String title;

    private  String file;

    private int artist;

    private int duration;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public String getFile() {
        return file;
    }

    public int getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }



    public Song(int id, String title,  int duration , int artist, String file) {
        this.title=title;
        this.id=id;
       this.file=file;
        this.artist = artist;
        this.duration= duration;
    }
}
