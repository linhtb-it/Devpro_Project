package com.example.watchnow_project.View;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.view.DragEvent;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.watchnow_project.Adapter.VideoNear_Adapter;
import com.example.watchnow_project.Model.Entity.Video;
import com.example.watchnow_project.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class VideoPlayerFragment extends Fragment {

    VideoView vv_Video;
    ImageView img_Avatar_PlayVideo;
    ImageView imgBut_Play, imgBut_Pause, imgBut_Back, imgBut_Next;
    TextView tv_Title_PlayVideo, tv_TimePlay, tv_TimeMax;
    SeekBar seekBar;
    boolean statusControl, videoNext =  true;
    ProgressBar progressBar;
    RecyclerView rv_Video_Near;
    ImageView imgFullScreen;

    ArrayList<Video> videos;

    SimpleDateFormat formatTime = new SimpleDateFormat("mm:ss");
    int i = 0;
    float xTouch, yTouch;

    int position;
    public void getVideo(ArrayList videos, int position){
        this.videos = videos;
        this.position = position;
    }
    public static VideoPlayerFragment newInstance() {
        
        Bundle args = new Bundle();

        VideoPlayerFragment fragment = new VideoPlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_player_fragment,container,false);
        vv_Video = view.findViewById(R.id.vv_Video);
        img_Avatar_PlayVideo = view.findViewById(R.id.img_Avatar_PlayVideo);
        tv_Title_PlayVideo = view.findViewById(R.id.tv_Title_Video_Play);
        rv_Video_Near = view.findViewById(R.id.rv_Video_Near);
        imgBut_Play = view.findViewById(R.id.play_ctr);
        imgBut_Pause = view.findViewById(R.id.pause_ctr);
        imgBut_Back = view.findViewById(R.id.back_ctr);
        imgBut_Next = view.findViewById(R.id.next_ctr);
        seekBar = view.findViewById(R.id.seek_Video);
        tv_TimePlay = view.findViewById(R.id.tv_TimePlay);
        tv_TimeMax = view.findViewById(R.id.tv_TimeMax);
        imgFullScreen = view.findViewById(R.id.img_FullScreen);
        progressBar = view.findViewById(R.id.progres_loadVideo);
        progressBar.setVisibility(View.VISIBLE);
        hideControl();

        imgBut_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playControl_Click();
            }
        });
        imgBut_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextControl();
            }
        });
        imgBut_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backControl();
            }
        });
        imgBut_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseControl();
            }
        });
        vv_Video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        vv_Video.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//
                if (motionEvent.getAction() ==MotionEvent.ACTION_MOVE){
                    if(motionEvent.getX()>=xTouch){
                        i++;
                    }
                    else {
                        i--;
                    }
                    tv_Title_PlayVideo.setText("Start Hover"+ i);
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //showControl();
                    xTouch = motionEvent.getX();
                    tv_Title_PlayVideo.setText("Finish"+ motionEvent.getX());
                    i=0 ;
                }if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (i>5){
                        if(vv_Video.getDuration() -vv_Video.getCurrentPosition()>10000){
                            vv_Video.seekTo(vv_Video.getCurrentPosition() + 10000);
                        }else {
                            vv_Video.seekTo( vv_Video.getDuration() - 1000);
                        }

                    }else if(i<-5){
                        if(vv_Video.getCurrentPosition() <= 10000){
                            vv_Video.seekTo(100);
                        }else {
                            vv_Video.seekTo(vv_Video.getCurrentPosition() - 10000);
                        }

                    }

                    tv_Title_PlayVideo.setText("Stop touch"+ vv_Video.getWidth()+"--"+vv_Video.getHeight());
                }
                return false;
            }
        });
        vv_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statusControl){
                    hideControl();
                }else {
                    showControl();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hideControl();
                        }
                    },1500);
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setTv_TimePlay(formatTime.format(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setTv_TimePlay(formatTime.format(seekBar.getProgress()));
                vv_Video.seekTo(seekBar.getProgress());
            }
        });
        //progressBar = view.findViewById(R.id.progres_videoPlayer_Loadding);

        //progressBar.setVisibility(view.GONE);

        PlayVideo(videos, position);
        return view;

    }
    private void hideControl(){
        imgBut_Next.setVisibility(View.GONE);
        imgBut_Back.setVisibility(View.GONE);
        imgBut_Pause.setVisibility(View.GONE);
        imgBut_Play.setVisibility(View.GONE);
        tv_TimePlay.setVisibility(View.GONE);
        tv_TimeMax.setVisibility(View.GONE);
        seekBar.setVisibility(View.GONE);
        imgFullScreen.setVisibility(View.GONE);
        statusControl = false;
    }
    private void showControl(){
        imgBut_Next.setVisibility(View.VISIBLE);
        imgBut_Back.setVisibility(View.VISIBLE);
        seekBar.setVisibility(View.VISIBLE);
        tv_TimePlay.setVisibility(View.VISIBLE);
        tv_TimeMax.setVisibility(View.VISIBLE);
        imgFullScreen.setVisibility(View.VISIBLE);

        if(vv_Video.isPlaying()){
            imgBut_Pause.setVisibility(View.VISIBLE);
            imgBut_Play.setVisibility(View.GONE);
        }else {
            imgBut_Play.setVisibility(View.VISIBLE);
            imgBut_Pause.setVisibility(View.GONE);
        }

        statusControl = true;
    }

    // play control click event - play video
    private void playControl_Click(){
        try{
            vv_Video.resume();
        }catch (Exception ex){
            vv_Video.start();
        }
        statusControl = true;
        showControl();
    }

    //pause controll click event - pause video
    private void pauseControl(){
        vv_Video.pause();
        statusControl = false;
        showControl();
    }

    //next control click event - run video next on List
    private void nextControl(){
        vv_Video.suspend();
        progressBar.setVisibility(View.VISIBLE);
        if(position<videos.size()){
            position++;
            PlayVideo(this.videos, position);
        }
        else {
            position = 0;
            PlayVideo(this.videos, position);
        }
    }
    // back control click event - run video before on List
    private void backControl(){
        progressBar.setVisibility(View.VISIBLE);
        if(position>0){
            position--;
            PlayVideo(this.videos, position);
        }
        else {
            position = videos.size()-1;
            PlayVideo(this.videos, position);
        }
    }

    // set time to textView Right of seekBar - length of video
    private void setTv_TimeMax(){
        seekBar.setMax(vv_Video.getDuration());
    }

    // set time to textView Left of seekBar - run time of video
    private void setTv_TimePlay(String time){
        tv_TimePlay.setText(time);
    }

    private void setTimeVideo(){
        seekBar.setProgress(vv_Video.getCurrentPosition());
        setTv_TimePlay(formatTime.format(vv_Video.getCurrentPosition()));
    }
    private void videoUpdate(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //tv_TimeMax.setText(formatTime.format(vv_Video.getDuration()));
                //seekBar.setMax(vv_Video.getDuration());
                setTimeVideo();
                //progressBar.setVisibility(View.GONE);
                handler.postDelayed(this,500);
            }
        }, 100);
        vv_Video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(videoNext){
                    progressBar.setVisibility(View.VISIBLE);
                    nextControl();
                }else
                    showControl();
            }
        });
    }
    private float getDurationVideo(Video video){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(video.getFile_Mp4(),new HashMap<String, String>());
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        //SimpleDateFormat formatTime = new SimpleDateFormat("mm:ss");
        //formatTime.format(Float.parseFloat(time));
        retriever.release();
        return  Float.parseFloat(time);
    }
    private void setVideoToList(ArrayList<Video> videos, int position){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        VideoNear_Adapter adapter = new VideoNear_Adapter(getContext(),videos);

        rv_Video_Near.setLayoutManager(layoutManager);
        rv_Video_Near.setAdapter(adapter);

        adapter.setOnVideoItemClick((videos1, position1) -> PlayVideo(videos1, position1));
    }
    private void PlayVideo(ArrayList<Video> videos, int position){
        tv_Title_PlayVideo.setText(this.videos.get(position).getTitle());
        try{
            Glide.with(getContext()).load(videos.get(position).getAvatar()).into(img_Avatar_PlayVideo);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        Uri uri = Uri.parse(videos.get(position).getFile_Mp4());
        vv_Video.setVideoURI(uri);
        setVideoToList(this.videos, position);
        vv_Video.start();
        float timeMax = getDurationVideo(videos.get(position));
        tv_TimeMax.setText(formatTime.format(timeMax));
        seekBar.setMax((int)timeMax);
        videoUpdate();

    }
}
