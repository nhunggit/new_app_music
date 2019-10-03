package com.example.new_music;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listSongFragmennt extends baseListSong implements LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView mRecyclerView;
    protected SongAdapter mAdapter;
    private static final int LOADER_ID = 1;
    private String[] projection={MediaStore.Audio.AudioColumns.DATA,MediaStore.Audio.AudioColumns.ALBUM,MediaStore.Audio.AudioColumns.ARTIST,MediaStore.Audio.Media.TITLE,MediaStore.Audio.Media.DURATION};
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        if(id==1)
         return  new CursorLoader(getContext(),MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,null,null,null);
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.list_baihat,container,false);
        mRecyclerView=view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        getLoaderManager().initLoader(LOADER_ID, null, this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor c) {
       ArrayList<Song> listMusic = new ArrayList<>();
        int id = 0;
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String path = c.getString(1);
                String album = c.getString(2);
                String artist = c.getString(3);
                String name = c.getString(4);
                String duration = c.getString(5);
                listMusic.add(new Song(id, name, path, artist, Integer.parseInt(duration)));
                Log.d("info", " Album :" + album);
                Log.d("Path :" + path, " Artist :" + artist + " Duration " + duration);
                id++;
            } while (c.moveToNext());
            mAdapter=new SongAdapter(listMusic);
            mRecyclerView.setAdapter(mAdapter);
        }
//        mAdapter.updateList(listMusic);
//        //mAdapter.setSong(listMusic);
//        mAdapter.setmTypeSong("AllSong");
    }


    @Override
    public void onLoaderReset(@NonNull Loader loader) {
        if (mAdapter != null) {
            mAdapter.setSong(new ArrayList<Song>());
        }
    }
}


