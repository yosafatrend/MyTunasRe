package com.spect.mytunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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

import java.net.URLEncoder;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutprofile);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutProfileActivity.this, EditProfileActivity.class));
            }
        });

        ImageView buttonwhatsapp = findViewById(R.id.buttonwhatsapp);
        buttonwhatsapp.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                startSupportChat();
            }
        } );

        DatabaseReference siswa = FirebaseDatabase.getInstance().getReference("siswa");
        DatabaseReference childSiswa = siswa.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        childSiswa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Toast.makeText(getApplicationContext(), snapshot.child("email").getValue().toString(), Toast.LENGTH_SHORT).show();
                try {
                    TextView navUsername = findViewById(R.id.profile_name);
                    TextView navNis = findViewById(R.id.profile_nis);
                    CircleImageView imgUser = findViewById(R.id.profile_image);
                    TextView profileEmail = findViewById(R.id.profile_email);
                    navUsername.setText(snapshot.child("nama_lengkap").getValue().toString());
                    navNis.setText(snapshot.child("nis").getValue().toString());
                    profileEmail.setText(snapshot.child("email").getValue().toString());
                    Glide.with(getApplicationContext())
                            .load(snapshot.child("imgUri").getValue().toString()).into(imgUser);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void gotoIg(View view) {
        Uri uri = Uri.parse("http://instagram.com/_u/elvisardinno");


        Intent i = new Intent(Intent.ACTION_VIEW, uri);

        i.setPackage("com.instagram.android");

        try {
            startActivity(i);
        } catch (ActivityNotFoundException e) {

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/elvisardinno")));
        }
    }

    public void gotoFb(View view) {
        startActivity(getOpenFacebookIntent());
    }

    public Intent getOpenFacebookIntent() {
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            getPackageManager().getPackageInfo("com.facebook.lite", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100009462566593"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/elvis.ardinno06"));
        }
    }

    public void gotoTw(View view) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=")));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!")));
        }
    }

    private void startSupportChat() {

        try {
            String headerReceiver = "";
            String bodyMessageFormal = "";
            String whatsappContain = headerReceiver + bodyMessageFormal;
            String trimToNumner = "+6281392833720";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://wa.me/" + trimToNumner + "/?text=" + ""));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}