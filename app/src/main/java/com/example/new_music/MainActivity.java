package com.example.new_music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Uri nhacUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Fragment fragmentlistBaihat = new fragmentListBaihat();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlist, fragmentlistBaihat).commit();
        ContentResolver contentResolver=getContentResolver();
        Cursor cusor=contentResolver.query(nhacUri,null,null,null,null);
        cusor.moveToFirst();
        while(!cusor.isAfterLast()){
            String duongdanAudio=cusor.getString(cusor.getColumnIndex(MediaStore.Audio.Media.DATA));
            String ten=cusor.getString(cusor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            Log.d("giatri", duongdanAudio +"___"+ten);
            cusor.moveToNext();
        }
    }

    public void onClickbh(View view) {
        Fragment fragmentBaihat=new fragmentBaihat();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlist,fragmentBaihat).commit();
    }
}
