package com.example.new_music;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
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

public class listSongFragmennt extends Fragment implements SongAdapter.OnClickItemView {
    MyService myService;
    ConstraintLayout constraintLayout;
    ConstraintLayout mConstraitLayout;
    TextView NameSongPlaying;
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
        recycleview.setHasFixedSize(true);
        @SuppressLint("WrongConstant") LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleview.setLayoutManager(linearLayoutManager);
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };

        Cursor cursor = getActivity().managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);
        int id=0;
        while (cursor.moveToNext()) {
            id++;
            songs.add(new Song(id,cursor.getString(2),cursor.getString(3),cursor.getString(1),Integer.parseInt(cursor.getString(5))));
            Log.d("giatri", cursor.getString(3));
        }
        SongAdapter baihatAdapter = new SongAdapter(songs, getContext());
        recycleview.setAdapter(baihatAdapter);
        baihatAdapter.setOnClickItemView(this);
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

    public void updateUI(){

        if(myService.isMusicPlay()){
            Log.d("abc1", "ClickItem: "+myService.getNameSong());
            //constraintLayout.setVisibility(View.VISIBLE);
            myService.updateTime();
            if(myService.isPlaying()){
                buttonPlay.setImageResource(R.drawable.ic_pause);
            }else
                buttonPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            disk.setImageBitmap(myService.getAlbumn(myService.getFile()));
            NameSongPlaying.setText(myService.getNameSong());
            artist.setText(myService.getNameArtist());
        }
    }

    @Override
    public void ClickItem(int position) {
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

//    @NonNull
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
//        String[] projection={MediaStore.Audio.Media._ID,
//                MediaStore.Audio.Media.ARTIST,
//                MediaStore.Audio.Media.TITLE,
//                MediaStore.Audio.Media.DATA,
//                MediaStore.Audio.Media.DISPLAY_NAME,
//                MediaStore.Audio.Media.DURATION};
//        CursorLoader cursorLoader=new CursorLoader(getContext(),MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection,null,null,null);
//        return cursorLoader;
//    }
//
//    @Override
//    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
//        ArrayList<Song> listSong=new ArrayList<>();
//        if(data!=null && data.getCount()>0){
//            data.moveToFirst();
//            do{
//                int id=Integer.parseInt(data.getString(0));
//                String title=data.getString(2);
//                String file=data.getString(3);
//                String artist=data.getString(1);
//                int duration=Integer.parseInt(data.getString(5));
//                Song song=new Song(id,title,file,artist,duration);
//            }while(data.moveToNext());
//            baihatAdapter = new SongAdapter(songs, getContext());
//            recycleview.setAdapter(baihatAdapter);
//            baihatAdapter.setOnClickItemView(this);
//        }
//    }
//
//    @Override
//    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
//
//    }
}




