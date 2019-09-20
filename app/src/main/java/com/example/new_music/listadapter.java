package com.example.new_music;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listadapter extends Activity {


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_baihat);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<baiHat> list=new ArrayList<>();
        list.add(new baiHat("1","Nam thang ruc ro",R.drawable.ic_tuychon));
        list.add(new baiHat("1","Nam thang ruc ro",R.drawable.ic_tuychon));
        list.add(new baiHat("1","Nam thang ruc ro",R.drawable.ic_tuychon));
        baiHatAdapter adapter=new baiHatAdapter(list,this);
        recyclerView.setAdapter(adapter);
    }
}
