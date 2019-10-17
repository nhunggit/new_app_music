package com.example.new_music;

public class Song {
    private int id;

    private  String title;

    private  String file;

    private String artist;

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

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }



    public Song(int id, String title, String file , String artist, int duration) {
        this.title=title;
        this.id=id;
       this.file=file;
        this.artist = artist;
        this.duration= duration;
    }

    public Song(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
