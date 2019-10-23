package com.example.new_music;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class MediaPlaybackService extends Service implements  MediaPlayer.OnCompletionListener{
    private static final String NOTIFICATION_CHANNEL_ID="1";
    public static final String ACTION_PERVIOUS = "xxx.yyy.zzz.ACTION_PERVIOUS";
    public static final String ACTION_PLAY = "xxx.yyy.zzz.ACTION_PLAY";
    public static final String ACTION_NEXT = "xxx.yyy.zzz.ACTION_NEXT";
    private final IBinder mBinder = new LocalBinder();
    private final Random mRandom = new Random();
    private MediaPlayer mediaPlayer;
    private String mNameSong ="";
    private String mArtistt ="";
    private String  mPotoMusic ="";
    private String mFile ="";
    private boolean mShuffleSong =false;
    private int mMinIndex;
    private int mLoopSong =0;

    public int getLoopSong() {
        return mLoopSong;
    }
    public void setLoopSong(int loopSong) {
        this.mLoopSong = loopSong;
    }
    public String getFile() {
        return mFile;
    }
    public int getMinIndex() {
        return mMinIndex;
    }
    public String getNameArtist(){
        return mArtistt;
    }
    public String getPotoMusic(){
        return mPotoMusic;
    }
    public String getNameSong(){
        return mNameSong;
    }
    private ArrayList<Song> listsong = new ArrayList<>();
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public void setListSong(ArrayList<Song> mListAllSong) {
        this.listsong = mListAllSong;
    }
    public ArrayList<Song> getListsong() {
        return listsong;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        try {
            onCompletionSong();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class LocalBinder extends Binder {
        MediaPlaybackService getService() {
            return MediaPlaybackService.this;
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("it", "onStartCommand: "+intent.getAction());
        if(isMusicPlay()){
            switch (intent.getAction()){
                case ACTION_PERVIOUS:
                    try {
                        previousSong();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case ACTION_NEXT:
                    try {
                        nextSong();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case ACTION_PLAY:
                    if(mediaPlayer.isPlaying())
                        pauseSong();
                    else {
                        try {
                            playSong(getListsong().get(getMinIndex()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public Bitmap getAlbumn(String path){
        MediaMetadataRetriever metadataRetriever=new MediaMetadataRetriever();
        metadataRetriever.setDataSource(path);
        byte[] data=metadataRetriever.getEmbeddedPicture();
        return data==null?null: BitmapFactory.decodeByteArray(data,0,data.length);
    }
    public int actionShuffleSong(){
        Random rd = new Random();
        int result = rd.nextInt(listsong.size() - 1);
        return result;
    }
    public boolean isShuffleSong(){
        return mShuffleSong;
    }
    public void setShuffleSong(boolean shuffleSong){
        this.mShuffleSong =shuffleSong;
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
        showNotification(mNameSong, mArtistt, mPotoMusic);
    }
    public int getDurationSong(){
        return mediaPlayer.getDuration();
    }
    public int getCurrentTime(){
        return mediaPlayer.getCurrentPosition();
    }
    public String getDuration() {
        SimpleDateFormat formmatTime = new SimpleDateFormat("mm:ss");
        return formmatTime.format(mediaPlayer.getDuration());
    }

    public void showNotification(String nameSong, String nameArtist, String path){
        createNotificationChanel();

        Intent notificationIntent=new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this, 0,notificationIntent,0);

        Intent previousIntent = new Intent(this, MediaPlaybackService.class);
        previousIntent.setAction(ACTION_PERVIOUS);
        PendingIntent previousPendingIntent = null;

        Intent playIntent = new Intent(this, MediaPlaybackService.class);
        playIntent.setAction(ACTION_PLAY);
        PendingIntent playPendingIntent = null;

        Intent nextIntent = new Intent(this, MediaPlaybackService.class);
        nextIntent.setAction(ACTION_NEXT);
        PendingIntent nextPendingIntent = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            previousPendingIntent = PendingIntent.getForegroundService(this, 0, previousIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            playPendingIntent = PendingIntent.getForegroundService(getApplicationContext(), 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            nextPendingIntent = PendingIntent.getForegroundService(getApplicationContext(), 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        RemoteViews mSmallNotification=new RemoteViews(getPackageName(),R.layout.small_noyification);
        RemoteViews mNotification=new RemoteViews(getPackageName(),R.layout.notification);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_music_note_black_24dp);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setCustomContentView(mSmallNotification);
        builder.setCustomBigContentView(mNotification);
        builder.setContentIntent(pendingIntent);

        mNotification.setTextViewText(R.id.title_ntf,nameSong);
        mNotification.setTextViewText(R.id.artist_ntf,nameArtist);
        mNotification.setOnClickPendingIntent(R.id.previous_ntf,previousPendingIntent);
        mNotification.setOnClickPendingIntent(R.id.next_ntf,nextPendingIntent);
        mNotification.setOnClickPendingIntent(R.id.play_ntf,playPendingIntent);
        mNotification.setImageViewResource(R.id.play_ntf,isPlaying()? R.drawable.ic_pause_circle_filled_black_50dp : R.drawable.ic_play_circle_filled_black_50dp);
        if(getAlbumn(path)!=null){
            mNotification.setImageViewBitmap(R.id.img,getAlbumn(path));
        }else{
            mNotification.setImageViewResource(R.id.img,R.drawable.default_cover_art);
        }
        mSmallNotification.setOnClickPendingIntent(R.id.play_smallntf,playPendingIntent);
        mSmallNotification.setOnClickPendingIntent(R.id.previous_smallntf,previousPendingIntent);
        mSmallNotification.setOnClickPendingIntent(R.id.next_smallntf,nextPendingIntent);
        mSmallNotification.setImageViewResource(R.id.play_smallntf, isPlaying() ?  R.drawable.ic_pause_circle_filled_black_50dp : R.drawable.ic_play_circle_filled_black_50dp );
        if(getAlbumn(path)!=null){
            mSmallNotification.setImageViewBitmap(R.id.image,getAlbumn(path));
        }else{
            mSmallNotification.setImageViewResource(R.id.image,R.drawable.default_cover_art);
        }
        startForeground(1, builder.build());
    }

    public void createNotificationChanel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "mUSIC SERVICE CHANNEL",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            NotificationManager manager=getSystemService(NotificationManager.class);
           manager.createNotificationChannel(notificationChannel);
        }
    }

    public void playSong(Song song) throws IOException {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        if (isPlaying()) {
            mediaPlayer.pause();
        } else {
            Uri uri = Uri.parse(song.getFile());
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
            mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.start();
            mNameSong =song.getTitle();
            mArtistt =song.getArtist();
            mPotoMusic =song.getFile();
            mFile =song.getFile();
            mMinIndex =song.getId()-1;
        }
        showNotification(mNameSong, mArtistt, mPotoMusic);
    }

    public void nextSong() throws IOException {
        mediaPlayer.stop();
        if(mShuffleSong ==true){
            mMinIndex =actionShuffleSong();
        }
        else{
            mMinIndex++;
            if(mMinIndex ==listsong.size())
                mMinIndex =0;
        }
        Log.d("ab", "nextSong: "+ mMinIndex);
        playSong(listsong.get(mMinIndex));

    }
    public void previousSong() throws IOException {
        mediaPlayer.stop();
        if(mShuffleSong ==true){
            mMinIndex =actionShuffleSong();
        }
        else{
            mMinIndex--;
            if(mMinIndex ==0)
                mMinIndex =listsong.size()-1;
        }
        Log.d("ab", "nextSong: "+ mMinIndex);
        playSong(listsong.get(mMinIndex));

    }

//    public void updateTime(){
//        final Handler handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        try {
//                            onCompletionSong();
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                handler.postDelayed(this,500);
//            }
//        },100);
//    }
    public void onCompletionSong() throws IOException {
        mediaPlayer.pause();
        if(mLoopSong ==0){
            if(mMinIndex <listsong.size()-1){
                mMinIndex++;
            }
        }else{
            if(mLoopSong ==-1){
                if(mMinIndex ==listsong.size()-1){
                    mMinIndex =0;
                }else{
                    mMinIndex++;
                }
            }
        }
        playSong(listsong.get(mMinIndex));
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    //phương thức cho client


}
