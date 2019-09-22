package com.example.watchnow_project.TransData;

import com.example.watchnow_project.Model.Entity.Video;

import java.util.ArrayList;

public interface IVideoTrans {
    void sendVideo(ArrayList<Video> videos, int position, int oldDuration);
    void receiveVideo(boolean x);
    void sendString(String x);
}
