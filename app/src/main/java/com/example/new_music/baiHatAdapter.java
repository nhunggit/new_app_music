package com.example.new_music;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class baiHatAdapter extends RecyclerView.Adapter<baiHatAdapter.WordViewHolder> {
    ArrayList<baiHat> listBaihat;
    Context context;
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
        holder.tstt.setText(listBaihat.get(position).getStt());
        holder.ttenbh.setText(listBaihat.get(position).getTenbh());
        holder.ituychon.setImageResource(listBaihat.get(position).getBia());
    }

    @Override
    public int getItemCount() {
        return listBaihat.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tstt;
        TextView ttenbh;
        ImageView ituychon;
        final baiHatAdapter baihatadapter;
        public WordViewHolder(@NonNull View itemView, baiHatAdapter adapter) {
            super(itemView);
            this.baihatadapter=adapter;
            tstt=(TextView)itemView.findViewById(R.id.stt);
            ttenbh=(TextView)itemView.findViewById(R.id.tenbh3);
            ituychon=(ImageView)itemView.findViewById(R.id.tuychon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mposition=getLayoutPosition();
            baiHat element=listBaihat.get(mposition);
            listBaihat.set(mposition,element);
            baihatadapter.notifyDataSetChanged();
        }
    }
}
