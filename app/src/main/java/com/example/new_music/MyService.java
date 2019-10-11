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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MyService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private final Random mRandom = new Random();
    private MediaPlayer mediaPlayer;
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

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public boolean isMusicPlay() {
        if (mediaPlayer != null) {
            return true;
        }
        return false;
    }
    public void pauseSong(Song song){
        mediaPlayer.pause();
    }

    public void playSong(Song song) throws IOException {
        mediaPlayer = new MediaPlayer();
//        if (mediaPlayer.isPlaying()) {
//            mediaPlayer.pause();
//        } else {
            Uri uri = Uri.parse(song.getFile());
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
            mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.start();
        }
 //   }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    //phương thức cho client


}
