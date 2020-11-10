package com.spect.mytunas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.*;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spect.mytunas.R;

public class DetailAnnouncementActivity extends AppCompatActivity {

    private TextView tvAuthor,tvBerita,tvTopik,tvTanggal;
    private ImageView imageView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_announcement);

        imageView = findViewById(R.id.imgDetailPengumuman);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvBerita = findViewById(R.id.tvBerita);
        tvTopik = findViewById(R.id.tvtopik);
        tvTanggal = findViewById(R.id.tvtanggal);
        tvAuthor.setText(getIntent().getStringExtra("pengirim"));
        tvBerita.setText(getIntent().getStringExtra("informasi"));
        tvTopik.setText(getIntent().getStringExtra("topik"));
        tvTanggal.setText(  "- " + getIntent().getStringExtra("tgl"));
       // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    imageView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
                    imageView.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    imageView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                   // fullScreen();
                }
            }
        });
        if (getIntent().getStringExtra("imageUri") == null) {
           imageView.setVisibility(View.GONE);
        }else {
            Glide.with(this).load(getIntent().getStringExtra("imageUri")).into(imageView);
        }
    }
    public void fullScreen() {

        // BEGIN_INCLUDE (get_current_ui_flags)
        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        // END_INCLUDE (get_current_ui_flags)
        // BEGIN_INCLUDE (toggle_ui_flags)
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i("TAG", "Turning immersive mode mode off. ");
        } else {
            Log.i("TAG", "Turning immersive mode mode on.");
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        // Immersive mode: Backward compatible to KitKat.
        // Note that this flag doesn't do anything by itself, it only augments the behavior
        // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
        // all three flags are being toggled together.
        // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
        // Sticky immersive mode differs in that it makes the navigation and status bars
        // semi-transparent, and the UI flag does not get cleared when the user interacts with
        // the screen.
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        //END_INCLUDE (set_ui_flags)
    }
    public void onBack(View view) {
        super.onBackPressed();
    }
}