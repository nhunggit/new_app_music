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
   // Uri nhacUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    @SuppressLint("WrongConstant")
    @Override
  //  baiHatAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.list_baihat, container, false);
        tenbh=(TextView)inflate.findViewById(R.id.song_playing);
        recyclerView=(RecyclerView)inflate.findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        @SuppressLint("WrongConstant") LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<baiHat> arrayList=new ArrayList<>();
        arrayList.add(new baiHat(1,"Nam thang ruc ro",R.drawable.ic_tuychon,R.raw.song_gio));

        baiHatAdapter adapter=new baiHatAdapter(arrayList,getContext());
        recyclerView.setAdapter(adapter);
        //tenbh.setText(adapter.onItemClick());

        return inflate;
    }
    public interface OnListViewItemClickListener {
        String onItemClick(int position);
    }
//    public interface OnListViewItemClickListener {
//
//    }

}
