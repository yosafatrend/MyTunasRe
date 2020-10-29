package com.spect.mytunas.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spect.mytunas.R;
import com.spect.mytunas.activity.DetailSiswaActivity;
import com.spect.mytunas.models.Siswa;

import java.util.List;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.MyViewHolder> {
    private List<Siswa> userList;
    private Activity mActivity;

    public UserSearchAdapter(List<Siswa> userList, Activity mActivity) {
        this.userList = userList;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public UserSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserSearchAdapter.MyViewHolder holder, int position) {
        final Siswa siswa = userList.get(position);

        Toast.makeText(mActivity, siswa.getUrlFb(), Toast.LENGTH_SHORT).show();
        holder.tvName.setText(siswa.getNama_lengkap());
        holder.tvNis.setText(siswa.getNis());
        Glide.with(mActivity)
                .load(siswa.getImgUri()).into(holder.imgAvatar);
        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, DetailSiswaActivity.class);
                i.putExtra("nama", siswa.getNama_lengkap());
                i.putExtra("nis", siswa.getNis());
                i.putExtra("email", siswa.getEmail());
                i.putExtra("gender", siswa.getGender());
                i.putExtra("jurusan", siswa.getJurusan());
                i.putExtra("kelas", siswa.getKelas());
                i.putExtra("alamat", siswa.getAlamat());
                i.putExtra("status", siswa.getStatus());
                i.putExtra("wa", siswa.getWa());
                i.putExtra("fb", siswa.getUrlFb());
                i.putExtra("ig", siswa.getUrlIg());
                i.putExtra("twt", siswa.getUrlTwt());
                i.putExtra("img", siswa.getImgUri());

                mActivity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout listItem;
        public TextView tvName, tvNis;
        public ImageView imgAvatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.listItem);
            tvName = itemView.findViewById(R.id.item_name);
            tvNis = itemView.findViewById(R.id.item_nis);
            imgAvatar = itemView.findViewById(R.id.item_avatar);
        }
    }
}
