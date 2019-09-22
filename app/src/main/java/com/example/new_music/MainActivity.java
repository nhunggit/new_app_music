package com.example.new_music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragmentlistBaihat = new fragmentListBaihat();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlist, fragmentlistBaihat).commit();

    }

    public void onClickbh(View view) {
        Fragment fragmentBaihat=new fragmentBaihat();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlist,fragmentBaihat).commit();
    }
}
