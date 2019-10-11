package com.example.new_music;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class songFragment extends Fragment {
    MyService myService;
    TextView nameSong;
    TextView nameArtist;
    ImageView potoMusic;
    SeekBar seekBar;
    TextView timeCurrent;
    TextView timeFinish;
    ImageView like;
    ImageView diskLike;
    ImageView play;
    ImageView next;
    ImageView previous;
    ImageView repeat;
    ImageView shuffle;
    int i=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.baihat,container,false);
        nameSong=(TextView)view.findViewById(R.id.namesong);
        nameArtist=(TextView)view.findViewById(R.id.nameArtist);
        timeCurrent=(TextView)view.findViewById(R.id.starttime);
        timeFinish=(TextView)view.findViewById(R.id.finishTime);
        potoMusic=(ImageView)view.findViewById(R.id.disk);
        like=(ImageView)view.findViewById(R.id.like);
        diskLike=(ImageView)view.findViewById(R.id.dislike);
        play=(ImageView)view.findViewById(R.id.play);
        next=(ImageView)view.findViewById(R.id.next);
        repeat=(ImageView)view.findViewById(R.id.repeat);
        shuffle=(ImageView)view.findViewById(R.id.shuffle);
        previous=(ImageView)view.findViewById(R.id.previous);
        updateUI();
        return view;
    }
    public void updateUI(){
        if(myService!=null){
            //Log.d("nhung2", "onCreateView: "+msong);
            i=myService.getCurrentPostion();
            Log.d("nhung1", "updateUI: "+i);
           // nameSong.setText(msong.get(i).getTitle());
        }
    }
    public void setMyService(MyService service){
        this.myService = service;
    }
}
