package com.spect.mytunas.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.spect.mytunas.R;
import com.spect.mytunas.models.Requests;

import java.util.List;

public class RequestAdapterRecyclerView extends RecyclerView.Adapter<RequestAdapterRecyclerView.MyViewHolder> {
    private List<Requests> moviesList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView tvBerita, tvAuthor;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.lr_layout);
            tvBerita = view.findViewById(R.id.tvBerita);
            tvAuthor = view.findViewById(R.id.tvAuthor);
        }
    }

    public RequestAdapterRecyclerView(List<Requests> moviesList, Activity activity) {
        this.moviesList = moviesList;
        this.mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.item_announce, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Requests movie = moviesList.get(position);

        holder.tvBerita.setText(movie.getInformasi());
        holder.tvAuthor.setText(movie.getPengirim());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}