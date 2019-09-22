package com.example.watchnow_project.View;

import android.net.ConnectivityManager;
import android.os.Bundle;

import com.example.watchnow_project.Adapter.InternetConnection;
import com.example.watchnow_project.GetString.Links;
import com.example.watchnow_project.Model.Entity.Category;
import com.example.watchnow_project.Model.Entity.Video;
import com.example.watchnow_project.R;
import com.example.watchnow_project.TransData.ICategoryTrans;
import com.example.watchnow_project.TransData.IVideoTrans;
import com.example.watchnow_project.View.CategoryFragment;
import com.example.watchnow_project.View.HotVideoFragment;
import com.example.watchnow_project.View.NoInternetFragment;
import com.example.watchnow_project.View.VideoPlayerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IVideoTrans, ICategoryTrans {

    private static final String TAG = "MainActivity";
    Fragment hotVideoFragMent;
    Fragment categoryFragMent;
    Fragment noInternetFragment;
    FragmentManager fragmentManager;
    VideoPlayerFragment videoPlayer;
    ProgressBar progressBar;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        fragmentManager = getSupportFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        progressBar = findViewById(R.id.progres);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(InternetConnection.ConnectionForInternet(this)){
            hotVideoFragMent = HotVideoFragment.newInstance(Links.GET_HOT_VIDEO);
            getFragment(hotVideoFragMent);
        }else {
            noInternetFragment = NoInternetFragment.newInstance();
            getFragment(noInternetFragment);
        }
    }

//    public  Boolean isConnected(){
//       ConnectivityManager cm = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
//        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
//    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_hotVideo) {
            HotVideoFragment hotVideoFragment;
            hotVideoFragment = HotVideoFragment.newInstance(Links.GET_HOT_VIDEO);
            getFragment(hotVideoFragment);
        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_hotVideo:

                    getFragment(HotVideoFragment.newInstance(Links.GET_HOT_VIDEO));
                    toolbar.setTitle(R.string.title_hotVideo);
                    return true;
                case R.id.navigation_category:
                    getFragment(CategoryFragment.newInstance());
                    toolbar.setTitle(R.string.title_dashboard);
                    return true;
                case R.id.navigation_favorite:
                    //getFragment(Favorite_Fragment.newInstance());
                    return true;
            }
            return false;
        }
    };
    public void getFragment(Fragment fragment){
        try {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container,fragment)
//                    .commit();
            progressBar.setVisibility(View.GONE);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getBaseContext(),"Error",Toast.LENGTH_LONG).show();
            Log.d(TAG, "getFragment: "+e.getMessage());
        }
    }

    @Override
    public void sendVideo(ArrayList<Video> videos,int position, int oldDuration) {
        videoPlayer = VideoPlayerFragment.newInstance();
        if(hotVideoFragMent !=null || hotVideoFragMent.isInLayout()){
            videoPlayer.getVideo(videos,position,oldDuration);
        }
        getFragment(videoPlayer);

    }

    @Override
    public void receiveVideo(boolean x) {

    }

    @Override
    public void sendString(String x) {

    }

    @Override
    public void sendCategory(Category category, String linkCategory) {
        this.toolbar.setTitle(category.getTitle());
        hotVideoFragMent = HotVideoFragment.newInstance(linkCategory);
        getFragment(hotVideoFragMent);
    }
}
