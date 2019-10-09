package com.example.new_music;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private final IBinder mBinder=new LocalBinder();

    public class LocalBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


}
