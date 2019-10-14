package com.example.new_music;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    private int currentPosition;
    private boolean shuffleSong=false;
    private int minIndex;

    public void setMinIndex(int minIndex) {
        this.minIndex = minIndex;
    }

    public int getCurrentPostion(){
        currentPosition=mediaPlayer.getCurrentPosition();
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

    public ArrayList<Song> getListsong() {
        return listsong;
    }

    public int getPosition() {
        return position;
    }

    public void getPosition(int position) {
        this.position = position;
    }
    public int getCurrentSong(){
        return mediaPlayer.getDuration();
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
        }
    }

    public void nextSong() throws IOException {
        mediaPlayer.pause();
        playSong(listsong.get(position++));
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
