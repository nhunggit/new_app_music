package com.example.new_music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragmentlistBaihat = new fragmentListBaihat();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlist,fragmentlistBaihat).commit();
//        FragmentManager fragmentManager=getFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//
//        fragmentTransaction.add(R.id.fragmentlist,fragmentlistBaihat);
//        fragmentTransaction.commit();

//        FragmentTransaction fragmenttransaction=fragmentManager.beginTransaction();
//        fragmentListBaihat fragmentlistBaihat=new fragmentListBaihat();
//
//        fragmenttransaction.add(R.id.fragmentlist,fragmentlistBaihat);
    }
}
