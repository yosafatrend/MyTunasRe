package com.spect.mytunas.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spect.mytunas.R;
import com.spect.mytunas.activity.DetailAnnouncementActivity;
import com.spect.mytunas.models.Announce;

import java.util.List;

public class AnnounceHomeAdapter extends RecyclerView.Adapter<AnnounceHomeAdapter.MyViewHolder> {
    private List<Announce> moviesList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView tvTitle, tvDesc;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.item_info);
            tvTitle = view.findViewById(R.id.topic_title);
            tvDesc = view.findViewById(R.id.desc_ann);
        }
    }

    public AnnounceHomeAdapter(List<Announce> moviesList, Activity activity) {
        this.moviesList = moviesList;
        this.mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.item_info_home, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Announce announce = moviesList.get(position);

        holder.tvTitle.setText(announce.getTopik());
        holder.tvDesc.setText(announce.getInformasi());
        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pengirim = announce.getPengirim();
                String informasi = announce.getInformasi();
                String imageuri = announce.getImgUri();
                String topik = announce.getTopik();
                String date = announce.getTanggal();
                Intent intent = new Intent(mActivity, DetailAnnouncementActivity.class);
                intent.putExtra("pengirim", pengirim);
                intent.putExtra("informasi", informasi);
                intent.putExtra("imageUri", imageuri);
                intent.putExtra("topik", topik);
                intent.putExtra("tgl", date);
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (moviesList.size() < 3){
            return moviesList.size();
        }else{
            return 3;
        }
    }


}