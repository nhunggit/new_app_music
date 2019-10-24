package com.example.new_music;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity  {

    MediaPlaybackService myService;
    boolean mBound=false;
    Fragment mAllSongFragment;
    Fragment mMediaPlayBackFragment;
    SongAdapter songAdapter;
   // private IConnectActivityAndBaseSong iConnectActivityAndBaseSong;
    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MediaPlaybackService.LocalBinder binder=(MediaPlaybackService.LocalBinder) service;
            myService=binder.getService();
            Log.d("BKAV DucLQ", " Bkav DucLQ bind service myService "+ myService);
            ((AllSongsFragment) mAllSongFragment).setMyService(myService);
            ((MediaPlaybackFragment)mMediaPlayBackFragment).setMyService(myService);
            //iConnectActivityAndBaseSong.connectActivityAndBaseSong();
           // songAdapter.setMyService(myService);
            mBound=true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound=false;
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean ispotraist=getResources().getBoolean(R.bool.isPortrait);
       //boolean n=getResources().getBoolean(R.bool.nhung);
        mAllSongFragment = new AllSongsFragment();
        mMediaPlayBackFragment = new MediaPlaybackFragment();
        Intent intent=new Intent(this, MediaPlaybackService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        if(ispotraist==false) {
            if(findViewById(R.id.fragment2)!=null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment1, mAllSongFragment).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment2, mMediaPlayBackFragment).commit();
            }
            else
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment1, mAllSongFragment).commit();

        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment1, mAllSongFragment).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tuychon_menu,menu);
        MenuItem search=menu.findItem(R.id.timkiem);
        SearchView searchView=(SearchView)search.getActionView();
        return super.onCreateOptionsMenu(menu);

    }
//    public void setiConnectActivityAndBaseSong(IConnectActivityAndBaseSong iConnectActivityAndBaseSong) {
//        this.iConnectActivityAndBaseSong = iConnectActivityAndBaseSong;
//    }
//    interface IConnectActivityAndBaseSong {
//        void connectActivityAndBaseSong();
//    }
    
}
