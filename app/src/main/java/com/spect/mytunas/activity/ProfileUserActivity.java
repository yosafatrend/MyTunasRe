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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spect.mytunas.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileUserActivity extends AppCompatActivity {
    private TextView profileName, profileEmail, profileNis, profileGender, profileClass, profileStatus, profileMajor, profileNumber, profileAddress;
    private ImageView btnWa, btnIg, btnFb, btnTwt;
    private CircleImageView imgUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileUserActivity.this, EditProfileActivity.class));
            }
        });

        profileNis = findViewById(R.id.profile_nis);
        profileGender = findViewById(R.id.profile_gender);
        profileClass = findViewById(R.id.profile_class);
        profileStatus = findViewById(R.id.profile_status);
        profileMajor = findViewById(R.id.profile_major);
        profileNumber = findViewById(R.id.profile_number);
        profileAddress = findViewById(R.id.profile_address);
        profileName = findViewById(R.id.profile_name);
        imgUser = findViewById(R.id.profile_image);
        profileEmail = findViewById(R.id.profile_email);

        btnWa = findViewById(R.id.buttonwhatsapp);
        btnFb = findViewById(R.id.profile_fb);
        btnIg = findViewById(R.id.profile_ig);
        btnTwt = findViewById(R.id.profile_twitter);

        DatabaseReference siswa = FirebaseDatabase.getInstance().getReference("siswa");
        DatabaseReference childSiswa = siswa.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        childSiswa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Toast.makeText(getApplicationContext(), snapshot.child("email").getValue().toString(), Toast.LENGTH_SHORT).show();
                try {

                    profileName.setText(snapshot.child("nama_lengkap").getValue().toString());
                    profileNis.setText(snapshot.child("nis").getValue().toString());
                    profileEmail.setText(snapshot.child("email").getValue().toString());
                    profileAddress.setText(snapshot.child("alamat").getValue().toString());
                    profileNumber.setText(snapshot.child("wa").getValue().toString());
                    profileClass.setText(snapshot.child("kelas").getValue().toString());
                    profileGender.setText(snapshot.child("gender").getValue().toString());
                    profileMajor.setText(snapshot.child("jurusan").getValue().toString());
                    profileStatus.setText(snapshot.child("status").getValue().toString());

                    final String waNumb = snapshot.child("wa").getValue().toString();
                    final String fbLink = snapshot.child("urlFb").getValue().toString();
                    final String igLink = snapshot.child("urlIg").getValue().toString();
                    final String twtLink = snapshot.child("urlTwt").getValue().toString();

                    if (!waNumb.equals("")){
                        btnWa.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick (View view){
                                startSupportChat(waNumb);
                            }
                        } );
                    }else{
                        btnWa.setVisibility(View.GONE);
                    }

                    if (!fbLink.equals("")){
                        btnFb.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick (View view){
                                gotoFb(fbLink);
                            }
                        } );
                    }else{
                        btnFb.setVisibility(View.GONE);
                    }

                    if (!igLink.equals("")){
                        btnIg.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick (View view){
                                gotoIg(igLink);
                            }
                        } );
                    }else{
                        btnIg.setVisibility(View.GONE);
                    }

                    if (!twtLink.equals("")){
                        btnTwt.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick (View view){
                                gotoTw(twtLink);
                            }
                        } );
                    }else{
                        btnTwt.setVisibility(View.GONE);
                    }

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
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse(fbLink));
        }
    }

    public void gotoTw(String twtLink) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name="+twtLink)));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"+twtLink)));
        }
    }

    private void startSupportChat(String number) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://wa.me/+62" + number + "/?text=" + "Hello.... "));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}