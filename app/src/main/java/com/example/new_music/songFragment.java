package com.example.new_music;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.Transliterator;
import android.media.MediaMetadataRetriever;
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

import java.io.IOException;
import java.util.ArrayList;

public class songFragment extends Fragment {
    MyService myService;
    TextView nameSong;
    TextView nameArtist;
    ImageView potoMusic;
    ImageView potoMusic2;
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
        seekBar=(SeekBar)view.findViewById(R.id.seekbar);
        potoMusic2=(ImageView)view.findViewById(R.id.imgBackGround);
        nameSong=(TextView)view.findViewById(R.id.namesong);
        nameArtist=(TextView)view.findViewById(R.id.nameArtist);
        timeCurrent=(TextView)view.findViewById(R.id.starttime);
        timeFinish=(TextView)view.findViewById(R.id.finishTime);
        potoMusic=(ImageView)view.findViewById(R.id.disk);
        like=(ImageView)view.findViewById(R.id.like);
        diskLike=(ImageView)view.findViewById(R.id.dislike);
        play=(ImageView)view.findViewById(R.id.Play);
        next=(ImageView)view.findViewById(R.id.next);
        repeat=(ImageView)view.findViewById(R.id.repeat);
        shuffle=(ImageView)view.findViewById(R.id.shuffle);
        previous=(ImageView)view.findViewById(R.id.previous);
//        next.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                if (myService != null) {
//                    try {
//                        myService.nextSong();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    seekBar.setMax(Integer.parseInt(myService.getDuration()));
//                    updateUI();
//                }
//            }
//        });
        updateUI();
        return view;
        }
    private Bitmap getAlbumn(String path){
        MediaMetadataRetriever metadataRetriever=new MediaMetadataRetriever();
        metadataRetriever.setDataSource(path);
        byte[] data=metadataRetriever.getEmbeddedPicture();
        return data==null?null: BitmapFactory.decodeByteArray(data,0,data.length);
    }
    public void updateUI(){
        if(myService!=null && seekBar!=null){
            if(myService.isMusicPlay()){
                nameSong.setText(myService.getNameSong());
                nameArtist.setText(myService.getNameArtist());
                Bitmap bitmap=getAlbumn(myService.getPotoMusic());
                potoMusic.setImageBitmap(bitmap);
                potoMusic2.setImageBitmap(bitmap);
                timeFinish.setText(myService.getDuration());
                if (myService.isPlaying()) {
                   play.setImageResource(R.drawable.ic_pause_circle_filled_black_50dp);

                } else {
                    play.setImageResource(R.drawable.ic_play_circle_filled_black_50dp);
                }
            }
        }
    }
    public void setMyService(MyService service){
        this.myService = service;
    }
}
