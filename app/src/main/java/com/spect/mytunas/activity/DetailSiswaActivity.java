package com.spect.mytunas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.spect.mytunas.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailSiswaActivity extends AppCompatActivity {
    private TextView profileName, profileEmail, profileNis, profileGender, profileClass, profileStatus, profileMajor, profileNumber, profileAddress;
    private ImageView btnWa, btnIg, btnFb, btnTwt;
    private CircleImageView imgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_siswa);

        profileNis = findViewById(R.id.profile_nis);
        profileGender = findViewById(R.id.profile_gender);
        profileClass = findViewById(R.id.profile_class);
        profileStatus = findViewById(R.id.profile_status);
        profileMajor = findViewById(R.id.profile_major);
        profileNumber = findViewById(R.id.profile_number);
        profileAddress = findViewById(R.id.profile_address);
        profileName = findViewById(R.id.profile_name);
        imgUser = findViewById(R.id.profile_imaged);
        profileEmail = findViewById(R.id.profile_email);

        btnWa = findViewById(R.id.buttonwhatsappd);
        btnFb = findViewById(R.id.profile_fbd);
        btnIg = findViewById(R.id.profile_igd);
        btnTwt = findViewById(R.id.profile_twitterd);

        profileName.setText(getIntent().getStringExtra("nama"));
        profileNis.setText(getIntent().getStringExtra("nis"));
        profileEmail.setText(getIntent().getStringExtra("email"));
        profileAddress.setText(getIntent().getStringExtra("alamat"));
        profileNumber.setText(getIntent().getStringExtra("wa"));
        profileClass.setText(getIntent().getStringExtra("kelas"));
        profileGender.setText(getIntent().getStringExtra("gender"));
        profileMajor.setText(getIntent().getStringExtra("jurusan"));
        profileStatus.setText(getIntent().getStringExtra("status"));
        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("img")).into(imgUser);

        final String waNumb = getIntent().getStringExtra("wa");
        final String fbLink = getIntent().getStringExtra("fb");
        final String igLink = getIntent().getStringExtra("ig");
        final String twtLink = getIntent().getStringExtra("twt");

        try {
            if (!waNumb.equals("")) {
                btnWa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startSupportChat(waNumb);
                    }
                });
            } else {
                btnWa.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            btnWa.setVisibility(View.GONE);
        }

        try {
            if (!fbLink.equals("")) {
                btnFb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoFb(fbLink);
                    }
                });
            } else {
                btnFb.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            btnFb.setVisibility(View.GONE);
        }

        try {
            if (!igLink.equals("")) {
                btnIg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoIg(igLink);
                    }
                });
            } else {
                btnIg.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            btnIg.setVisibility(View.GONE);
        }

        try {
            if (!twtLink.equals("")) {
                btnTwt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoTw(twtLink);
                    }
                });
            } else {
                btnTwt.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            btnTwt.setVisibility(View.GONE);
        }

        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("img")).into(imgUser);
    }

    public void gotoIg(String igLink) {
        Uri uri = Uri.parse(igLink);


        Intent i = new Intent(Intent.ACTION_VIEW, uri);

        i.setPackage("com.instagram.android");

        try {
            startActivity(i);
        } catch (ActivityNotFoundException e) {

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/" +igLink + "/")));
        }
    }

    public void gotoFb(String fbLink) {
        startActivity(getOpenFacebookIntent(fbLink));
    }

    public Intent getOpenFacebookIntent(String fbLink) {
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            getPackageManager().getPackageInfo("com.facebook.lite", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse(fbLink));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse(fbLink));
        }
    }

    public void gotoTw(String twtLink) {
        try {

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name="+twtLink)));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + twtLink)));
        }
    }

    private void startSupportChat(String number) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setData(Uri.parse("https://wa.me/+62" + number + "/?text=" + "Hai " + getIntent().getStringExtra("nama")));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}