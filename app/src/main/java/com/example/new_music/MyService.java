package com.example.new_music;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class MyService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private final Random mRandom = new Random();
    private MediaPlayer mediaPlayer;
    private String nameSong="";
    private String nameArtist="";
    private String potoMusic="";
    private int timeFinish=0;
    private int currentPosition=0;
    private boolean shuffleSong=false;
    private int minIndex;
    private int loopSong=0;

    public void setMinIndex(int minIndex) {
        this.minIndex = minIndex;
    }

    public int getLoopSong() {
        return loopSong;
    }

    public void setLoopSong(int loopSong) {
        this.loopSong = loopSong;
    }

    public int getCurrentPostion(){
        return currentPosition;
    }
    public String getNameArtist(){
        return nameArtist;
    }
    public String getPotoMusic(){
        return potoMusic;
    }
    public int getTimeFinish(){
        return timeFinish;
    }
    public String getNameSong(){
        return nameSong;
    }

    private ArrayList<Song> listsong = new ArrayList<>();
    private int position;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setListSong(ArrayList<Song> mListAllSong) {
        this.listsong = mListAllSong;
    }

    public ArrayList<Song> getListsong() {
        return listsong;
    }

    public int getPosition() {
        return position;
    }

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public int actionShuffleSong(){
        Random rd = new Random();
        int result = rd.nextInt(listsong.size() - 1);
        return result;
    }
    public boolean isShuffleSong(){
        return shuffleSong;
    }
    public void setShuffleSong(boolean shuffleSong){
        this.shuffleSong=shuffleSong;
    }


    public boolean isMusicPlay() {
        if (mediaPlayer != null) {
            return true;
        }
        return false;
    }
    public boolean isPlaying(){
        if(mediaPlayer.isPlaying())
            return true;
        return false;
    }
    public void pauseSong(){
        mediaPlayer.stop();
    }

    public void playSong(Song song) throws IOException {
        mediaPlayer = new MediaPlayer();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            Uri uri = Uri.parse(song.getFile());
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
            mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.start();
            nameSong=song.getTitle();
            nameArtist=song.getArtist();
            potoMusic=song.getFile();
            timeFinish=song.getDuration();
            minIndex=song.getId()-1;
        }
    }

    private void updateTime(){

    }

    public void nextSong() throws IOException {
        mediaPlayer.stop();
        if(shuffleSong==true){
            minIndex=actionShuffleSong();
        }
        else{
            minIndex++;
            if(minIndex==listsong.size())
                minIndex=0;
        }
        Log.d("ab", "nextSong: "+minIndex);
        playSong(listsong.get(minIndex));

    }
    public void previousSong() throws IOException {
        mediaPlayer.stop();
        if(shuffleSong==true){
            minIndex=actionShuffleSong();
        }
        else{
            minIndex--;
            if(minIndex==0)
                minIndex=listsong.size()-1;
        }
        Log.d("ab", "nextSong: "+minIndex);
        playSong(listsong.get(minIndex));

    }
    public int getDurationSong(){
        return mediaPlayer.getDuration();
    }

    public String getDuration() {
        SimpleDateFormat formmatTime = new SimpleDateFormat("mm:ss");
        return formmatTime.format(mediaPlayer.getDuration());
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    //phương thức cho client


}
