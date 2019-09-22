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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchnow_project.Adapter.Category_Adapter;
import com.example.watchnow_project.GetString.Links;
import com.example.watchnow_project.Main.MainActivity;
import com.example.watchnow_project.Model.Entity.Category;
import com.example.watchnow_project.Model.Entity.Video;
import com.example.watchnow_project.R;
import com.example.watchnow_project.TransData.ICategoryTrans;
import com.example.watchnow_project.Event.ISetOnCategoryItemClick;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    RecyclerView rvCategory;
    ArrayList<Category> categoryList;
    ArrayList<Video> videoList;
    ICategoryTrans listener;
    ProgressBar progressBar;
    public static CategoryFragment newInstance() {

        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);

        rvCategory = view.findViewById(R.id.rv_Category);
        progressBar = view.findViewById(R.id.progres_Category_Loadding);
        DoGetCategory doGetCategory = new DoGetCategory(Links.GET_CATEGORY);
        doGetCategory.execute();

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
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity){
            this.listener = (ICategoryTrans) context;
        }
        else {
            throw new RuntimeException(context.toString()+"interface chua hoat dong");
        }
    }

    public class DoGetCategory extends AsyncTask<Void, Void, String> {
        private String resultAPI;
        private String url;

        private static final String TAG = "DoGetVideo";
        public DoGetCategory(String url) {
            this.url = url;
        }

        @Override
        protected String doInBackground(Void... voids) {
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
        private void loadFragMent(ArrayList<Category> categories){
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1,RecyclerView.VERTICAL,false);
            Category_Adapter adapter = new Category_Adapter(getActivity(),categories);

            adapter.setOnCategoryItemClick(new ISetOnCategoryItemClick() {
                @Override
                public void onItemSelect(Category category, String linkCategory) {
                    listener.sendCategory(category,linkCategory);
                }
            });

            rvCategory.setLayoutManager(layoutManager);
            rvCategory.setAdapter(adapter);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            try {
                categoryList = new ArrayList<>();
                videoList = new ArrayList<>();
                JSONArray categoryJSonArray = new JSONArray(s);

                for (int i = 0; i < categoryJSonArray.length(); i++) {
                    JSONObject objectCategory = categoryJSonArray.getJSONObject(i);
                    Category category = new Category();
                    category.setId(objectCategory.getString("id"));
                    category.setPublisher_ID(objectCategory.getString("publisher_id"));
                    category.setContent_Type(objectCategory.getInt("content_type"));
                    category.setTab(objectCategory.getString("tab"));
                    category.setTitle(objectCategory.getString("title"));
                    category.setAvatar(objectCategory.getString("avatar"));
                    category.setStatus(objectCategory.getInt("status"));
                    category.setDeleted(objectCategory.getInt("deleted"));
                    category.setUser_Created(objectCategory.getString("user_created"));
                    category.setUser_Modified(objectCategory.getString("user_modified"));
                    category.setUser_Modified(objectCategory.getString("date_created"));
                    category.setUser_Modified(objectCategory.getString("date_modified"));
                    category.setParent_ID(objectCategory.getString("parent_id"));
                    category.setLft(objectCategory.getInt("lft"));
                    category.setRgt(objectCategory.getInt("rgt"));
                    category.setLevel(objectCategory.getInt("level"));
                    category.setShort_Code(objectCategory.getString("short_code"));
                    category.setCommand_Code(objectCategory.getString("command_code"));
                    category.setPrice(objectCategory.getDouble("price"));
                    category.setFinished_Message(objectCategory.getString("finished_message"));
                    category.setHelp_Message(objectCategory.getString("help_message"));
                    category.setIcash(objectCategory.getString("icash"));
                    category.setThumb(objectCategory.getString("thumb"));
                    categoryList.add(category);
                }

                loadFragMent(categoryList);
            } catch (Exception json_ex) {
                json_ex.printStackTrace();
            }
        }
    }

}
