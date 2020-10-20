package com.spect.mytunas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;
import com.spect.mytunas.R;

public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        PhotoView photoView = (PhotoView) findViewById(R.id.imageView6);
        photoView.setImageResource(R.drawable.denah);
    }
    public void onBack(View view) {
        super.onBackPressed();
    }
}