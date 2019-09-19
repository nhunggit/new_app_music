package com.example.new_music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentListBaihat fragmentlistBaihat=new fragmentListBaihat();
       // getSupportFragmentManager().beginTransaction().add(R.id.fragmentlist,fragmentlistBaihat);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragmentlist,fragmentlistBaihat);
        fragmentTransaction.commit();

//        FragmentTransaction fragmenttransaction=fragmentManager.beginTransaction();
//        fragmentListBaihat fragmentlistBaihat=new fragmentListBaihat();
//
//        fragmenttransaction.add(R.id.fragmentlist,fragmentlistBaihat);
    }
}
