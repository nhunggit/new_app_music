package com.example.new_music;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import java.util.ArrayList;

public class listSongFragmennt extends baseListSong implements LoaderManager.LoaderCallbacks<Cursor> {
    private SongAdapter mAdapter;
    private static final int LOADER_ID = 1;
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection={MediaStore.Audio.AudioColumns.DATA,MediaStore.Audio.AudioColumns.ALBUM,MediaStore.Audio.AudioColumns.ARTIST,MediaStore.Audio.Media.TITLE,MediaStore.Audio.Media.DURATION};
        CursorLoader cursorLoader=new CursorLoader(getContext(),MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,null,null,null);
        return cursorLoader;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID, null, this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor c) {
        ArrayList<Song> listMusic = new ArrayList<>();
        int id = 0;
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String path = c.getString(0);
                String album = c.getString(1);
                String artist = c.getString(2);
                String name = c.getString(3);
                String duration = c.getString(4);
                listMusic.add(new Song(id, name, path, artist, Integer.parseInt(duration)));
                Log.d("info", " Album :" + album);
                Log.d("Path :" + path, " Artist :" + artist + " Duration " + duration);
                id++;
            } while (c.moveToNext());
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
