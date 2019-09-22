package com.example.watchnow_project.TransData;

import com.example.watchnow_project.Model.Entity.Video;

import java.util.ArrayList;

public interface IVideoFullScreen {
    void sendVideoFull(ArrayList<Video> videos, int position, int oldDuration);
}
