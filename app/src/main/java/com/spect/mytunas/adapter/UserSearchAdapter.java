package com.spect.mytunas.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spect.mytunas.R;
import com.spect.mytunas.models.Requests;
import com.spect.mytunas.models.Siswa;
import com.spect.mytunas.models.User;

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

        holder.tvName.setText(siswa.getNama_lengkap());
        holder.tvNis.setText(siswa.getNis());
        Glide.with(mActivity)
                .load(siswa.getImgUri()).into(holder.imgAvatar);
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
