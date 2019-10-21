package com.example.new_music;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyService myService;
    boolean mBound=false;
    Fragment fragmentlistBaihat;
    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder=(MyService.LocalBinder) service;
            myService=binder.getService();
            Log.d("BKAV DucLQ", " Bkav DucLQ bind service myService "+ myService);
            ((listSongFragmennt)fragmentlistBaihat).setMyService(myService);
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
        Intent intent=new Intent(this,MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        fragmentlistBaihat = new listSongFragmennt();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment1, fragmentlistBaihat).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tuychon_menu,menu);
        MenuItem search=menu.findItem(R.id.timkiem);
        SearchView searchView=(SearchView)search.getActionView();
        return super.onCreateOptionsMenu(menu);

    }



}

//        //contentProvider contentProvider=new contentProvider();
//        //contentProvider.onCreate();
////        Fragment fragmentbaihat=new fragmentBaihat();
////        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentbaihat,fragmentbaihat).commit();
////        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlistbaihat, fragmentlistBaihat).commit();
//    }
////
////    public void onClickbh(View view) {
////        Fragment fragmentBaihat=new fragmentBaihat();
////        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlist,fragmentBaihat).commit();
////    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.tuychon_menu,menu);
//        MenuItem search=menu.findItem(R.id.timkiem);
//        SearchView searchView= (SearchView) search.getActionView();
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        return super.onContextItemSelected(item);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void tenbaihat(View view) {
//        Fragment fragment=new fragmentBaihat();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlist,fragment).commit();

 //   }
//    public void provider(){
//        ContentResolver contentResolver=getContentResolver();
//        Cursor cusor=contentResolver.query(nhacUri,null,null,null,null);
//        cusor.moveToFirst();
//        int i=0;
//        while(!cusor.isAfterLast()){
//            String duongdanAudio=cusor.getString(cusor.getColumnIndex(MediaStore.Audio.Media.DATA));
//            //int bia=Integer.parseInt(duongdanAudio);
//            String ten=cusor.getString(cusor.getColumnIndex(MediaStore.Audio.Media.TITLE));
//            //baiHat baiHat=new baiHat(i,ten,bia);
//            Log.d("giatri",ten+"___"+i+"__"+duongdanAudio);
//            cusor.moveToNext();
//            i++;
//        }
//    }
//}
