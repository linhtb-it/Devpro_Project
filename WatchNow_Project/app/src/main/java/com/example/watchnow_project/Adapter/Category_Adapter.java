package com.example.watchnow_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.watchnow_project.GetString.Links;
import com.example.watchnow_project.Model.Entity.Category;
import com.example.watchnow_project.R;
import com.example.watchnow_project.Event.ISetOnCategoryItemClick;

import java.util.ArrayList;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.ViewHolder> {
    Context context;
    ArrayList<Category> categoryList;
    ISetOnCategoryItemClick i__onVideoItemClick;

    public void setOnCategoryItemClick(ISetOnCategoryItemClick i__onCategoryItemClick){
        this.i__onVideoItemClick = i__onCategoryItemClick;
    }

    public Category_Adapter(Context context, ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }


    @NonNull
    @Override
    public Category_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Category_Adapter.ViewHolder holder, int position) {
        Glide.with(context).load(categoryList.get(position).getThumb()).into(holder.img_Avatar_Category);
        holder.tv_Category_Name.setText(categoryList.get(position).getTitle());
        holder.img_Avatar_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(InternetConnection.ConnectionForInternet(context)){
                        i__onVideoItemClick.onItemSelect(categoryList.get(position), Links.GET_ITEM_CATEGORY);
                    }else {
                        //Toast.makeText(context,R.string.error,Toast.LENGTH_SHORT).show();
                        return;
                    }

                }catch (Exception ex){
                    Toast.makeText(context,R.string.error,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_Avatar_Category;
        TextView tv_Category_Name;
        public ViewHolder(@NonNull View view) {
            super(view);
            img_Avatar_Category= view.findViewById(R.id.img_Avatar_Category);
            tv_Category_Name = view.findViewById(R.id.tv_Category_Name);
        }
    }
}