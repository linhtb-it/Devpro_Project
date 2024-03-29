package com.example.watchnow_project.View;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.watchnow_project.Model.Entity.Video;
import com.example.watchnow_project.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class VideoFullScreenActivity extends AppCompatActivity {
    SeekBar seekBar;
    VideoView vv_Video;
    ImageView imgBut_Play, imgBut_Pause, imgBut_Back, imgBut_Next, imgBut_full;
    TextView tv_Title_PlayVideo, tv_TimePlay, tv_TimeMax;
    ProgressBar progressBar;
    SimpleDateFormat formatTime = new SimpleDateFormat("mm:ss");
    ArrayList<Video> videos;
    float xTouch = 0;
    int i=0;
    int position, oldDuration;
    boolean statusControl, videoNext =  true;

    public void setVideos(ArrayList<Video> videos, int position,int oldDuration) {
        this.videos = videos;
        this.position = position;
        this.oldDuration = oldDuration;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player_full_screen);

        seekBar = findViewById(R.id.seek_Video_full);
        imgBut_Back = findViewById(R.id.back_ctr_full);
        imgBut_Next = findViewById(R.id.next_ctr_full);
        imgBut_Pause = findViewById(R.id.pause_ctr_full);
        imgBut_Play = findViewById(R.id.play_ctr_full);
        tv_TimeMax = findViewById(R.id.tv_TimeMax_full);
        tv_TimePlay = findViewById(R.id.tv_TimePlay_full);
        vv_Video = findViewById(R.id.vv_Video_full);
        imgBut_full = findViewById(R.id.img_FullScreen_full);
        progressBar = findViewById(R.id.progres_loadVideo_full);
        videos = new ArrayList<>();
        Intent getIntent = getIntent();
        Bundle bundle = getIntent.getExtras();
        this.position = bundle.getInt("position");
        this.oldDuration = bundle.getInt("oldDuration");
        int size = bundle.getInt("size");
        videos = new ArrayList<>();
        for(int i=0; i< size; i++){
            Video video = (Video)bundle.getSerializable("video"+i);
            videos.add(video);
        }
        hideControl();

        imgBut_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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

        vv_Video.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() ==MotionEvent.ACTION_MOVE){
                    if(motionEvent.getX()>=xTouch){
                        i++;
                    }
                    else {
                        i--;
                    }
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //showControl();
                    xTouch = motionEvent.getX();
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
                    },2500);
                }
            }
        });

        PlayVideo(videos, position, oldDuration);
    }
    private void hideControl(){
        imgBut_Next.setVisibility(View.GONE);
        imgBut_Back.setVisibility(View.GONE);
        imgBut_Pause.setVisibility(View.GONE);
        imgBut_Play.setVisibility(View.GONE);
        tv_TimePlay.setVisibility(View.GONE);
        tv_TimeMax.setVisibility(View.GONE);
        seekBar.setVisibility(View.GONE);
        imgBut_full.setVisibility(View.GONE);
        statusControl = false;
    }
    private void showControl(){
        imgBut_Next.setVisibility(View.VISIBLE);
        imgBut_Back.setVisibility(View.VISIBLE);
        seekBar.setVisibility(View.VISIBLE);
        tv_TimePlay.setVisibility(View.VISIBLE);
        tv_TimeMax.setVisibility(View.VISIBLE);
        imgBut_full.setVisibility(View.VISIBLE);

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
            PlayVideo(this.videos, position,0);
        }
        else {
            position = 0;
            PlayVideo(this.videos, position,0);
        }
    }
    // back control click event - run video before on List
    private void backControl(){
        progressBar.setVisibility(View.VISIBLE);
        if(position>0){
            position--;
            PlayVideo(this.videos, position,0);
        }
        else {
            position = videos.size()-1;
            PlayVideo(this.videos, position,0);
        }
    }
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
                setTimeVideo();
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
        retriever.release();
        return  Float.parseFloat(time);
    }
//    private void setVideoToList(ArrayList<Video> videos, int position){
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
//        VideoNear_Adapter adapter = new VideoNear_Adapter(getContext(),videos);
//
//        rv_Video_Near.setLayoutManager(layoutManager);
//        rv_Video_Near.setAdapter(adapter);
//
//        adapter.setOnVideoItemClick((videos1, position1) -> PlayVideo(videos1, position1));
//    }
    private void PlayVideo(ArrayList<Video> videos, int position, int oldDuration){
        //tv_Title_PlayVideo.setText(this.videos.get(position).getTitle());
//        try{
//            Glide.with(getContext()).load(videos.get(position).getAvatar()).into(img_Avatar_PlayVideo);
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
        Uri uri = Uri.parse(videos.get(position).getFile_Mp4());
        vv_Video.setVideoURI(uri);
//        setVideoToList(this.videos, position);
        vv_Video.start();
        float timeMax = getDurationVideo(videos.get(position));
        tv_TimeMax.setText(formatTime.format(timeMax));
        seekBar.setMax((int)timeMax);
        vv_Video.seekTo(oldDuration-1);
        videoUpdate();
    }
}
