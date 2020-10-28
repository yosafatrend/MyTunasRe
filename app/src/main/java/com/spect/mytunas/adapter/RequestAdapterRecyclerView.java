package com.spect.mytunas.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spect.mytunas.R;
import com.spect.mytunas.activity.DetailAnnouncementActivity;
import com.spect.mytunas.models.Requests;

import java.util.List;

public class RequestAdapterRecyclerView extends RecyclerView.Adapter<RequestAdapterRecyclerView.MyViewHolder> {
    private List<Requests> moviesList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView tvBerita, tvAuthor, tvTanggal,tvTopik;
        ImageView imgAnnounce;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.lr_layout);
            tvBerita = view.findViewById(R.id.tvBerita);
            tvAuthor = view.findViewById(R.id.tvAuthor);
            imgAnnounce = view.findViewById(R.id.img_announce);
            tvTanggal = view.findViewById(R.id.tvtanggal_item);
            tvTopik = view.findViewById(R.id.tvtopik_item);
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
        final Requests announce = moviesList.get(position);

        holder.tvBerita.setText(announce.getInformasi());
        holder.tvAuthor.setText(announce.getPengirim());
        holder.tvTanggal.setText("- " + announce.getTanggal());
        holder.tvTopik.setText(announce.getTopik());
        //        if (movie.getImgUri() == null){
//            holder.imgAnnounce.setVisibility(View.GONE);
//        }else{
        Glide.with(mActivity).load(announce.getImgUri()).centerCrop().into(holder.imgAnnounce);
        //}
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
        return moviesList.size();
    }


}