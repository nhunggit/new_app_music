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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class listSongFragmennt extends Fragment implements SongAdapter.OnClickItemView {
    MyService myService;
    ConstraintLayout constraintLayout;
    ConstraintLayout mConstraitLayout;
    TextView NameSongPlaying;
    ImageButton buttonPlay;
    ImageView disk;
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
        RecyclerView recycleview = view.findViewById(R.id.recyclerview);
        constraintLayout=view.findViewById(R.id.constraintLayoutItem);
        NameSongPlaying=view.findViewById(R.id.namePlaySong);
        buttonPlay=view.findViewById(R.id.play);
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

        int id=1;
        while (cursor.moveToNext()) {
            songs.add(new Song(id, cursor.getString(2), Integer.parseInt(cursor.getString(5)),cursor.getString(1),cursor.getString(3)));
            Log.d("giatri", cursor.getString(3));
            id++;
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
                    buttonPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                }else{
                    try {
                        myService.playSong(songs.get(position));
                        buttonPlay.setImageResource(R.drawable.ic_pause);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return view;
    }
    private Bitmap getAlbumn(String path){
        MediaMetadataRetriever metadataRetriever=new MediaMetadataRetriever();
        metadataRetriever.setDataSource(path);
        byte[] data=metadataRetriever.getEmbeddedPicture();
        return data==null?null: BitmapFactory.decodeByteArray(data,0,data.length);
    }

    @Override
    public void ClickItem(int position) {
        Log.d("abc", "ClickItem: "+myService);
        NameSongPlaying.setText(songs.get(position).getTitle());
        Bitmap bitmap=getAlbumn(songs.get(position).getFile());
        Log.d("nhung", "ClickItem: "+bitmap);
        disk.setImageBitmap(bitmap);
        try {
            if(!myService.isMusicPlay()) {
                myService.playSong(songs.get(position));
                buttonPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }else{
                myService.pauseSong();
                myService.playSong(songs.get(position));
                buttonPlay.setImageResource(R.drawable.ic_pause);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setMyService(MyService service){
        this.myService = service;
    }
}




