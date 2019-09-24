package com.example.new_music;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fragmentListBaihat extends Fragment {
    @Nullable
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    View inflate;
    TextView tenbh;
    Uri nhacUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    @SuppressLint("WrongConstant")
    @Override
  //  baiHatAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.list_baihat, container, false);
        recyclerView=(RecyclerView)inflate.findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<baiHat> list=new ArrayList<>();
        list.add(new baiHat("1","Nam thang ruc ro",R.drawable.ic_tuychon));
        list.add(new baiHat("1","Nam thang ruc ro",R.drawable.ic_tuychon));
        list.add(new baiHat("1","Nam thang ruc ro",R.drawable.ic_tuychon));
        list.add(new baiHat("1","Nam thang ruc ro",R.drawable.ic_tuychon));
        baiHatAdapter adapter=new baiHatAdapter(list,getContext());
        recyclerView.setAdapter(adapter);


        return inflate;
    }

  //  @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ContentResolver contentResolver=getCo
//    }
}
