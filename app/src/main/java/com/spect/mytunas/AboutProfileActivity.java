package com.spect.mytunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),  ""+ e, Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}