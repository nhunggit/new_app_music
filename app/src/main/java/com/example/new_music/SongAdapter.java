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

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private ArrayList<Song> mListSong=new ArrayList<>();
    private ArrayList<Song > mSong;
    private LayoutInflater mInflater;
    private Context context;
    private OnClickItemView mClickItemmView;
    private String mTypeSong;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_baihat,parent,false);
        return  new ViewHolder(view);
    }

    @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position){
            if (mSong != null) {
                final Song current = mSong.get(position);
                holder.mStt.setText(current.getId() + "");
                holder.mNameSong.setText(current.getTitle());
                SimpleDateFormat formatTime = new SimpleDateFormat("mm:ss");
                holder.mHour.setText(formatTime.format(current.getDuration()));

                final Song finalCurrent = current;
                holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickItemmView.clickItem(finalCurrent);
                    }
                });
            }
        }
//    public void updateList(ArrayList<Song> songs) {
//        mSong = songs;
//        mListSong = new ArrayList<>(mSong);
//        notifyDataSetChanged();
//    }
    public void setSong(ArrayList<Song> songs) {
        mSong = songs;
        Log.d("size2", songs.size() + "//");
        notifyDataSetChanged();
    }
//    public void setmTypeSong(String mTypeSong) {
//        this.mTypeSong = mTypeSong;
//    }
    @Override
    public int getItemCount() {
            if(mSong!=null)
                return mSong.size();
            else
                return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNameSong, mHour;
        ImageButton mMore;
        TextView mStt;
        ConstraintLayout mConstraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mConstraintLayout=itemView.findViewById(R.id.constraintLayoutItem);
            mNameSong=itemView.findViewById(R.id.namesong);
            mHour=itemView.findViewById(R.id.hours);
            mStt=itemView.findViewById(R.id.stt);
            mMore=itemView.findViewById(R.id.more);
        }
    }
    interface OnClickItemView{
        void clickItem(Song song);
    }
}
