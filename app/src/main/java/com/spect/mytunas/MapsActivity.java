package com.spect.mytunas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        PhotoView photoView = (PhotoView) findViewById(R.id.imageView6);
        photoView.setImageResource(R.drawable.denah);
    }
}