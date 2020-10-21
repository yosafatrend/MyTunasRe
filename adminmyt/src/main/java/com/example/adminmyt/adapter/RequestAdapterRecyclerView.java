package com.example.adminmyt.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmyt.MainActivity;
import com.example.adminmyt.R;
import com.example.adminmyt.model.Requests;

import java.util.ArrayList;
import java.util.List;

public class RequestAdapterRecyclerView extends RecyclerView.Adapter<RequestAdapterRecyclerView.MyViewHolder> {
    private List<Requests> moviesList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView tv_berita, tv_upload;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.linear_layout);
            tv_berita = view.findViewById(R.id.tv_berita);
            tv_upload = view.findViewById(R.id.tv_upload);
        }
    }

    public RequestAdapterRecyclerView(List<Requests> moviesList, Activity activity) {
        this.moviesList = moviesList;
        this.mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.item_request, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Requests movie = moviesList.get(position);

        holder.tv_berita.setText(movie.getInformasi());
        holder.tv_upload.setText(movie.getPengirim());

        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goDetail = new Intent(mActivity, MainActivity.class);
                goDetail.putExtra("id", movie.getKey());
                goDetail.putExtra("pengirim", movie.getPengirim());
                goDetail.putExtra("infromasi", movie.getInformasi());
                goDetail.putExtra("topik", movie.getTopik());


                mActivity.startActivity(goDetail);


            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}