package com.example.new_music;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class baseListSong extends Fragment {
    private RecyclerView mRecyclerView;
    protected SongAdapter mAdapter;
    private ImageButton mClickPlay;
    private TextView mNameSong, mArtist;
    private ImageView mdisk;
    private ArrayList<Song> mListSongs = new ArrayList<>();
    private boolean mExitService = false;
    private ConstraintLayout constraintLayout;
    private SharedPreferences mSharePreferences;
    private String SHARED_PREFERENCES_NAME = "com.example.newmusic";
    private int position = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.list_baihat,container,false);
        initView(view);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        setHasOptionsMenu(true);
        mRecyclerView=view.findViewById(R.id.recyclerview);
        mAdapter=new SongAdapter(getContext(),mListSongs);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        return view;
    }
    protected void setlist(ArrayList<Song> list){
        this.mListSongs = list;
    }

    void initView(View view){
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mClickPlay = view.findViewById(R.id.play);
        mArtist = view.findViewById(R.id.Artist);
        mdisk = view.findViewById(R.id.disk);
        mNameSong = view.findViewById(R.id.namePlaySong);
        mNameSong.setSelected(true);
        constraintLayout = view.findViewById(R.id.constraintLayout);
    }
}
