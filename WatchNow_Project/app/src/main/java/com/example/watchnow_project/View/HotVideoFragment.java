package com.example.watchnow_project.View;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchnow_project.Adapter.HotVideo_Adapter;
import com.example.watchnow_project.Model.Entity.Video;
import com.example.watchnow_project.R;
import com.example.watchnow_project.Event.ISetOnVideoItemClick;
import com.example.watchnow_project.TransData.IVideoTrans;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class HotVideoFragment extends Fragment{
    ArrayList<Video> videoList;
    IVideoTrans listener;
    RecyclerView rv_Video;
    String linkLoadVideo;
    ProgressBar progressBar;
    public void setListener(IVideoTrans listener){
        this.listener = listener;
    }
    public void setLinkLoadVideo(String linkLoadVideo) {
        this.linkLoadVideo = linkLoadVideo;
    }

    public HotVideoFragment(String linkLoadVideo) {
        this.linkLoadVideo = linkLoadVideo;
    }

    public static HotVideoFragment newInstance(String linkLoadVideo) {
        Bundle args = new Bundle();
        HotVideoFragment fragment = new HotVideoFragment(linkLoadVideo);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.hot_video_fragment,container, false);
        videoList = new ArrayList<>();
        rv_Video = view.findViewById(R.id.rv_Video);
        progressBar = view.findViewById(R.id.progres_video_Loadding);
        rv_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        DoGetVideo doGetVideo = new DoGetVideo(this.linkLoadVideo);
        doGetVideo.execute();
//        setRecyclerview();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity){
            this.listener = (IVideoTrans) context;
        }
        else {
            throw new RuntimeException(context.toString()+"interface chua hoat dong");
        }
    }


    public void setRecyclerview(ArrayList<Video> Vdeos){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        HotVideo_Adapter adapter = new HotVideo_Adapter(getActivity(),videoList);

        adapter.setOnVideoItemClick(new ISetOnVideoItemClick() {
            @Override
            public void onItemSelect(ArrayList<Video> videos, int position) {
                listener.sendVideo(videos,position);
            }
        });
        rv_Video.setLayoutManager(layoutManager);
        rv_Video.setAdapter(adapter);
    }


    public class DoGetVideo extends AsyncTask<Void, Void, String> {
        private String resultAPI;
        private String url;
        private static final String TAG = "DoGetVideo";

        public DoGetVideo(String url) {
            this.url = url;
        }

        @Override
        protected String doInBackground(Void... voids) {
            Log.e("demo","doInBackground ");

            try {
                URL url = new URL(this.url);
                URLConnection connection = url.openConnection();
                InputStream is = connection.getInputStream();
                int byteCharacter;
                resultAPI = "";
                while ((byteCharacter = is.read()) != -1) {
                    resultAPI += (char) byteCharacter;
                }
                Log.d(TAG, "doInBackground: " + resultAPI);
                return resultAPI;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            Log.e("demo","onPreeeExcute ");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("demo","onPostExecute ");

            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            try {
                videoList = new ArrayList<>();
                JSONArray hotvideoJSonArray = new JSONArray(s);

                for (int i = 0; i < hotvideoJSonArray.length(); i++) {
                    JSONObject objectHotVideo = hotvideoJSonArray.getJSONObject(i);

                    Video video = new Video();
                    video.setId(objectHotVideo.getString("id"));
                    video.setProvider_ID(objectHotVideo.getString("provider_id"));
                    video.setCategory_ID(objectHotVideo.getString("category_id"));
                    video.setTitle(objectHotVideo.getString("title"));
                    video.setAvatar(objectHotVideo.getString("avatar"));
                    video.setPrice(objectHotVideo.getDouble("price"));
                    video.setStatus(objectHotVideo.getInt("status"));
                    video.setDeleted(objectHotVideo.getInt("deleted"));
                    video.setCopyRight(objectHotVideo.getString("copyright"));
                    video.setArtist_Name(objectHotVideo.getString("artist_name"));
                    video.setAlbum_Name(objectHotVideo.getString("album_name"));
                    video.setFile_Mp4(objectHotVideo.getString("file_mp4"));
                    video.setFile_Mp4_Size(objectHotVideo.getDouble("file_mp4_size"));
                    video.setFile_3gp_Size(objectHotVideo.getDouble("file_3gp_size"));
                    video.setTotal_Downloaded(objectHotVideo.getString("total_downloaded"));
                    video.setDescription(objectHotVideo.getString("description"));
                    video.setDuration(objectHotVideo.getString("duration"));
                    video.setDate_Created(objectHotVideo.getString("date_created"));
                    video.setDate_Modified(objectHotVideo.getString("date_modified"));
                    video.setDate_Published(objectHotVideo.getString("date_published"));
                    video.setUser_Created(objectHotVideo.getString("user_created"));
                    video.setUser_Modified(objectHotVideo.getString("user_modified"));
                    video.setConvert_Status(objectHotVideo.getString("convert_status"));
                    video.setConvert_Time(objectHotVideo.getString("convert_time"));
                    video.setYoutube_Url(objectHotVideo.getString("youtube_url"));
                    video.setTags(objectHotVideo.getString("tags"));
                    video.setDownload_Status(objectHotVideo.getInt("download_status"));
                    video.setFb_Download(objectHotVideo.getString("fb_download"));
                    video.setIcash(objectHotVideo.getString("icash"));
                    video.setFb_Url(objectHotVideo.getString("fb_url"));
                    video.setAws_Status(objectHotVideo.getString("aws_status"));
                    video.setIcash_2(objectHotVideo.getString("icash_2"));
                    video.setPrice_2(objectHotVideo.getDouble("price_2"));
                    video.setPublisher_Category_ID(objectHotVideo.getString("publisher_category_id"));
                    video.setView_Clip_Gold(objectHotVideo.getString("view_clip_gold"));
                    video.setView_Clip_Icash(objectHotVideo.getString("download_clip_icash"));
                    video.setDownload_Clip_Gold(objectHotVideo.getInt("download_clip_gold"));
                    video.setDownload_Clip_Icash(objectHotVideo.getInt("download_clip_icash"));

                    videoList.add(video);
                }
                //videoList = videos;
                setRecyclerview(videoList);
            } catch (Exception json_ex) {
                json_ex.printStackTrace();
            } finally {

            }
        }
    }

}
