package com.example.new_music;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.util.ArrayList;

public class listSongFragmennt extends Fragment {
MyService myService;
boolean mBound=false;

    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder=(MyService.LocalBinder) service;
            myService=binder.getService();
            mBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound=false;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        Intent intent=new Intent(getContext(),MyService.class);
        myService.bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mBound){
            myService.unbindService(mConnection);
            mBound=false;
        }
    }
    public void onButtonClick(View v){
        if(mBound){
            //gọi 1 phương thức từ service
            //
            int num=myService.
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_baihat, container, false);
        RecyclerView recycleview = view.findViewById(R.id.recyclerview);
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

        ArrayList<Song> songs = new ArrayList<>();
        int id=1;
        while (cursor.moveToNext()) {
            songs.add(new Song(id, cursor.getString(2), Integer.parseInt(cursor.getString(5)),cursor.getString(1),cursor.getString(3)));
            Log.d("giatri", cursor.getString(3));
            id++;
        }
        SongAdapter baihatAdapter = new SongAdapter(songs, getContext());
        recycleview.setAdapter(baihatAdapter);
        return view;
    }


}




