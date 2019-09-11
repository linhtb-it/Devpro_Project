package com.example.watchnow_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.watchnow_project.Model.Entity.Video;
import com.example.watchnow_project.R;
import com.example.watchnow_project.Event.ISetOnVideoItemClick;

import java.util.ArrayList;

public class VideoNear_Adapter extends RecyclerView.Adapter<VideoNear_Adapter.ViewHolder> {
    Context context;
    ArrayList<Video> videosList;
    ISetOnVideoItemClick iSetOnnVideoItemClick;

    public  void setOnVideoItemClick(ISetOnVideoItemClick iSetOnnVideoItemClick){
        this.iSetOnnVideoItemClick = iSetOnnVideoItemClick;
    }

    public VideoNear_Adapter(Context context, ArrayList<Video> videosList) {
        this.context = context;
        this.videosList = videosList;
    }

    @NonNull
    @Override
    public VideoNear_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_near_items, parent, false);
        ViewHolder viewhoder = new ViewHolder(view);

        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(videosList.get(position).getAvatar()).into(holder.img_Avatar_Video_Near);
        holder.tv_Title_Video_Near.setText(videosList.get(position).getTitle());
        holder.tv_TimeCreate_Video_near.setText(videosList.get(position).getDate_Created());

        holder.img_Avatar_Video_Near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSetOnnVideoItemClick.onItemSelect(videosList, position);
            }
        });
        holder.tv_Title_Video_Near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSetOnnVideoItemClick.onItemSelect(videosList, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_Avatar_Video_Near;
        TextView tv_Title_Video_Near;
        TextView tv_TimeCreate_Video_near;
        public ViewHolder(@NonNull View view) {
            super(view);

            img_Avatar_Video_Near = view.findViewById(R.id.img_Avatar_Video_Near);
            tv_Title_Video_Near = view.findViewById(R.id.tv_Title_Video_Near);
            tv_TimeCreate_Video_near = view.findViewById(R.id.tv_TimeCreate_Video_near);
        }
    }
}
