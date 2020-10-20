package com.spect.mytunas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.*;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spect.mytunas.R;

public class DetailAnnouncementActivity extends AppCompatActivity {

    private TextView tvAuthor,tvBerita;
    private ImageView imageView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_announcement);

        imageView = findViewById(R.id.imgDetailPengumuman);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvBerita = findViewById(R.id.tvBerita);
        tvAuthor.setText(getIntent().getStringExtra("pengirim"));
        tvBerita.setText(getIntent().getStringExtra("informasi"));
        if (getIntent().getStringExtra("imageUri") == null) {
           imageView.setVisibility(View.GONE);
        }else {
            Glide.with(this).load(getIntent().getStringExtra("imageUri")).into(imageView);
        }
    }
}