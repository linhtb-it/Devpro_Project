package com.example.watchnow_project.Event;

import com.example.watchnow_project.Model.Entity.Video;

import java.util.ArrayList;

public interface ISetOnVideoItemClick {
    void onItemSelect(ArrayList<Video> videos, int position,int oldDuration);
}
