package com.ramadan.safari.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ramadan.safari.R;
import com.ramadan.safari.activities.selected_sharm_landmark;
import com.ramadan.safari.model.Landmark_Blog;

import java.util.ArrayList;

public class sharm_landmark_rcv_adp extends RecyclerView.Adapter<sharm_landmark_rcv_adp.LandmarkViewHolder> {
    private Context mContext;
    private ArrayList<Landmark_Blog> landmark;


    public sharm_landmark_rcv_adp(Context mContext, ArrayList landmark) {
        this.mContext = mContext;
        this.landmark = landmark;
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.load)
                .error(R.drawable.ic_error_outline_white_48dp);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        AdapterView.OnItemClickListener mListener = (AdapterView.OnItemClickListener) listener;
    }

    @NonNull
    @Override
    public LandmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.landmark_blog_raw, parent, false);
        return new LandmarkViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LandmarkViewHolder holder, int position) {
        Landmark_Blog Landmark_blog = landmark.get(position);

        String imageUrl = Landmark_blog.getLandmark_img_url();
        String location = Landmark_blog.getLandmark_location();
        String rate = Landmark_blog.getLandmark_rate();
        String landmark_name = Landmark_blog.getLandmark_name();

        holder.landmark_name.setText(landmark_name);
        holder.landmark_rate.setText(rate);
        holder.landmark_location.setText(location);
        Glide.with(mContext).asBitmap().load(imageUrl).into(holder.landmark_img);
        holder.landmark_img.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.zoom_in));

    }

    @Override
    public int getItemCount() {
        return landmark.size();
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    class LandmarkViewHolder extends RecyclerView.ViewHolder {
        TextView landmark_name, landmark_rate, landmark_location, landmark_cost;
        ImageView landmark_img;


        LandmarkViewHolder(View itemView) {
            super(itemView);
            landmark_name = itemView.findViewById(R.id.landmark_name);
            landmark_rate = itemView.findViewById(R.id.landmark_rate);
            landmark_location = itemView.findViewById(R.id.landmark_location);
            landmark_cost = itemView.findViewById(R.id.one_ticket);
            landmark_img = itemView.findViewById(R.id.landmark_img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, selected_sharm_landmark.class);
                    Bundle bundle = new Bundle();
                    //bundle.putSerializable("Data", mlandmark);
                    bundle.putString("landmark_name", landmark_name.getText().toString());
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                    Animatoo.animateFade(mContext);
                }
            });
        }
    }
}



