package com.example.new_music;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.WordViewHolder> {

    ArrayList<Song > mSong;
     Context mcontext;
     OnClickItemView onClickItemView;
     MyService myService;


    public void setmSong(ArrayList<Song> mSong) {
        this.mSong = mSong;
    }

    public SongAdapter(ArrayList<Song> mSong, Context mcontext) {
        this.mSong = mSong;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.item_baihat,parent,false);
        return new WordViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, final int position) {
        holder.mstt.setText(mSong.get(position).getId()+"");
        holder.mnameSong.setText(mSong.get(position).getTitle());
        holder.mHours.setText(mSong.get(position).getDuration()+"");
        holder.mMore.setImageResource(R.drawable.ic_more_vert_black_24dp);

    }

    public void setOnClickItemView(OnClickItemView onClickItemView){
        this.onClickItemView = onClickItemView;
    }

    @Override
    public int getItemCount() {
        return mSong.size();
    }


    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mstt;
        TextView mnameSong;
        TextView mHours;
        ImageButton mMore;
        ConstraintLayout constraintLayout;
        final SongAdapter mAdapter;
        public WordViewHolder(@NonNull View itemView,SongAdapter adapter) {
            super(itemView);
            this.mAdapter=adapter;
             mstt=(TextView)itemView.findViewById(R.id.stt);
            mnameSong=(TextView)itemView.findViewById(R.id.namesong);
             mHours=(TextView)itemView.findViewById(R.id.hours);
             mMore=(ImageButton)itemView.findViewById(R.id.more);
             constraintLayout=(ConstraintLayout)itemView.findViewById(R.id.constraintLayoutItem);
             itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onClickItemView.ClickItem(getAdapterPosition());
        }

    }
    interface OnClickItemView{
        void ClickItem(int position);
    }
}
