package com.example.new_music;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class listSongFragmennt extends Fragment implements SongAdapter.OnClickItemView, LoaderManager.LoaderCallbacks<Cursor> {
    MyService myService;
    ConstraintLayout constraintLayout;
    ConstraintLayout mConstraitLayout;
    TextView NameSongPlaying;
    TextView nameSong;
    TextView artist;
    ImageButton buttonPlay;
    ImageView disk;
    SongAdapter baihatAdapter;
    RecyclerView recycleview;
    private int position=0;
    private songFragment songFragment=new songFragment();
    ArrayList<Song> songs = new ArrayList<>();
    public ArrayList<Song> getListsong() {
        return songs;
    }
    private static final int LOADER_ID = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_baihat, container, false);
        recycleview = view.findViewById(R.id.recyclerview);
        constraintLayout=view.findViewById(R.id.constraintLayoutItem);
        NameSongPlaying=view.findViewById(R.id.namePlaySong);
        buttonPlay=view.findViewById(R.id.play);
        artist=view.findViewById(R.id.Artist);
        mConstraitLayout=view.findViewById(R.id.constraintLayout);
        disk=view.findViewById(R.id.disk);
        nameSong=view.findViewById(R.id.namesong);
        recycleview.setHasFixedSize(true);
        @SuppressLint("WrongConstant") LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleview.setLayoutManager(linearLayoutManager);
        LoaderManager.getInstance(this).initLoader(LOADER_ID, null, this);

        mConstraitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songFragment.setMyService(myService);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment1,songFragment).commit();
            }
        });
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myService.isPlaying()){
                    myService.pauseSong();
                }else{
                    try {
                        myService.playSong(songs.get(position));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                updateUI();
            }
        });
        if(myService!=null){
            updateUI();
        }

        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        baihatAdapter.setMyService(myService);
//    }
    //    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        LoaderManager.getInstance(this).initLoader(LOADER_ID, null, this);
//
//    }

    public void updateUI(){

        if(myService.isMusicPlay()){
            Log.d("abc1", "ClickItem: "+myService.getNameSong());
            //constraintLayout.setVisibility(View.VISIBLE);
            myService.updateTime();
            disk.setImageBitmap(myService.getAlbumn(myService.getFile()));
            NameSongPlaying.setText(myService.getNameSong());
            artist.setText(myService.getNameArtist());
            if(myService.isPlaying()){
                buttonPlay.setImageResource(R.drawable.ic_pause);
            }else
                buttonPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);

        }
    }

    @Override
    public void ClickItem(int position) {

        baihatAdapter.setK(0);
        try {
            myService.setListSong(songs);
        if (myService.isMusicPlay()) {
        if (!myService.isPlaying()) {
                    myService.playSong(songs.get(position));
                } else {
                    myService.pauseSong();
                    myService.playSong(songs.get(position));
                }
        }
        else {
            myService.playSong(songs.get(position));
        }
            updateUI();
        } catch (IOException e) {
        e.printStackTrace();
    }

    }

    public void setMyService(MyService service){
        this.myService = service;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection={MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION};
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        CursorLoader cursorLoader=new CursorLoader(getContext(),MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection,selection,null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

        int id = 0;
        String title="";
        String file="";
        String artist="";
        int duration=0;
        Song song=new Song();
        if(data!=null && data.getCount()>0){
           data.moveToFirst();
            while(data.moveToNext()){
                id ++;
                song.setId(id);
                song.setTitle(data.getString(data.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                song.setFile(data.getString(data.getColumnIndex(MediaStore.Audio.Media.DATA)));
                song.setArtist(data.getString(data.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                song.setDuration(data.getInt(data.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                title=song.getTitle();
                file=song.getFile();
                artist=song.getArtist();
                duration=song.getDuration();
                songs.add(new Song(id,title,file,artist,duration));
            }
            Log.d("size", "ClickItem: "+songs.size());
            baihatAdapter = new SongAdapter(songs, getContext());
            recycleview.setAdapter(baihatAdapter);
            baihatAdapter.setOnClickItemView(this);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}




