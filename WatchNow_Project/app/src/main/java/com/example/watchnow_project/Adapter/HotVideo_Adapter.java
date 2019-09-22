package com.example.watchnow_project.Adapter;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.watchnow_project.Model.Entity.Video;
import com.example.watchnow_project.R;
import com.example.watchnow_project.Event.ISetOnVideoItemClick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class HotVideo_Adapter extends RecyclerView.Adapter<HotVideo_Adapter.ViewHolder> {
    Context context;
    ArrayList<Video> videosList;
    ISetOnVideoItemClick iSetOnnVideoItemClick;

    public  void setOnVideoItemClick(ISetOnVideoItemClick iSetOnVideoItemClick){
        this.iSetOnnVideoItemClick = iSetOnVideoItemClick;
    }

    public HotVideo_Adapter(Context context, ArrayList<Video> videosList) {
        this.context = context;
        this.videosList = videosList;
    }

    @NonNull
    @Override
    public HotVideo_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_items, parent, false);
        ViewHolder viewhoder = new ViewHolder(view);

        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotVideo_Adapter.ViewHolder holder, int position) {
        Glide.with(context).load(videosList.get(position).getAvatar()).into(holder.img_Avatar);

//        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//        retriever.setDataSource(videosList.get(position).getFile_Mp4(),new HashMap<String,String>());
//        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//        SimpleDateFormat formatTime = new SimpleDateFormat("mm:ss");
//        formatTime.format(Float.parseFloat(time));
//        retriever.release();
//
//        holder.tv_Time.setText(formatTime.format(Float.parseFloat(time))+"");
        holder.tv_Time.setText("00:00");

        holder.tv_Title.setText(videosList.get(position).getTitle());
        holder.tv_TimeCreate.setText("Date create: " + videosList.get(position).getDate_Created());
        //Glide.with(context).load(videosList.get(position).getAvatar()).into(holder.img_Avatar_Mini);
        holder.img_Avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(InternetConnection.ConnectionForInternet(context)){
                        iSetOnnVideoItemClick.onItemSelect(videosList, position);
                    }else {
                        return;
                    }
                }
                catch (Exception ex){
                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                }

            }
        });
        holder.tv_Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(InternetConnection.ConnectionForInternet(context)){
                        iSetOnnVideoItemClick.onItemSelect(videosList, position);
                    }else {
                        return;
                    }
                }
                catch (Exception ex){
                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_Avatar;
        ImageView img_Avatar_Mini;
        TextView tv_Time;
        TextView tv_Title;
        TextView tv_TimeCreate;
        public ViewHolder(@NonNull View view) {
            super(view);

            img_Avatar = view.findViewById(R.id.img_Avatar);
            img_Avatar_Mini = view.findViewById(R.id.img_Avartar_Mini);
            tv_Title = view.findViewById(R.id.tv_Title);
            tv_Time = view.findViewById(R.id.tv_Time);
            tv_TimeCreate = view.findViewById(R.id.tv_TimeCreate);
        }
    }
}
