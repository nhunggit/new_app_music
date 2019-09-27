package com.example.new_music;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class contentProvider extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri nhacUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver=getContentResolver();
        Cursor cusor=contentResolver.query(nhacUri,null,null,null,null);
        cusor.moveToFirst();
        int i=0;
        while(!cusor.isAfterLast()){
            String duongdanAudio=cusor.getString(cusor.getColumnIndex(MediaStore.Audio.Media.DATA));
            //int bia=Integer.parseInt(duongdanAudio);
            String ten=cusor.getString(cusor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            //baiHat baiHat=new baiHat(i,ten,bia);
            Log.d("giatri",ten+"___"+i+"__"+duongdanAudio);
            cusor.moveToNext();
            i++;
        }
    }
}
