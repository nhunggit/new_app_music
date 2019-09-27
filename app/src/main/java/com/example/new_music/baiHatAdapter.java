package com.example.new_music;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class baiHatAdapter extends RecyclerView.Adapter<baiHatAdapter.WordViewHolder> implements fragmentListBaihat.OnListViewItemClickListener {
    ArrayList<baiHat> listBaihat;
    Context context;
    String name="";
    TextView song_playing;

    //int layout;

    public baiHatAdapter(ArrayList<baiHat> listBaihat, Context context) {
        this.listBaihat = listBaihat;
        //this.layout=layout;
        this.context = context;
    }



    @NonNull
    @Override
    public baiHatAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemview=inflater.inflate(R.layout.item_baihat,parent,false);
        return new WordViewHolder(itemview,this);
    }

    @Override
    public void onBindViewHolder(@NonNull baiHatAdapter.WordViewHolder holder, int position) {
        holder.tstt.setText(listBaihat.get(position).getStt()+"");
        holder.ttenbh.setText(listBaihat.get(position).getTenbh());
        holder.ituychon.setImageResource(listBaihat.get(position).getBia());

    }

    @Override
    public int getItemCount() {
        return listBaihat.size();
    }

    @Override
    public String onItemClick(int position) {
        String name=listBaihat.get(position).getTenbh();
        return name;
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tstt;
        TextView ttenbh;
        ImageView ituychon;

        final baiHatAdapter baihatadapter;
        public WordViewHolder(@NonNull View itemView, baiHatAdapter adapter) {
            super(itemView);
            this.baihatadapter=adapter;
            tstt=(TextView)itemView.findViewById(R.id.item_stt);
            ttenbh=(TextView)itemView.findViewById(R.id.item_name);
            ituychon=(ImageView)itemView.findViewById(R.id.imageView);
            ituychon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu tuychon=new PopupMenu(context,ituychon);
                    tuychon.getMenuInflater().inflate(R.menu.tuychon_menu,tuychon.getMenu());
                    tuychon.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.cute:
                                    break;
                                case  R.id.xinhdep:
                                    break;
                            }
                            return false;
                        }
                    });
                    tuychon.show();
                }
            });
            itemView.setOnClickListener(this);
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onClick(View view) {
            int mposition=getLayoutPosition();
            baiHat element=listBaihat.get(mposition);
            listBaihat.set(mposition,element);
            baihatadapter.notifyDataSetChanged();


        }
    }
}
