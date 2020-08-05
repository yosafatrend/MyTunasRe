package com.spect.mytunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
   private DatabaseReference Siswa;
   private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       FirebaseDatabase database = FirebaseDatabase.getInstance();
       final DatabaseReference reference = database.getReference();
        Siswa = FirebaseDatabase.getInstance().getReference().child("siswa");
        Siswa.child("1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String nis = snapshot.child("nis").getValue().toString();
                        Toast.makeText(MainActivity.this, "Berhasil " + nis, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        init();
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        }, 2000);
    }

    private void init(){
        this.progressBar = findViewById(R.id.progressBar);

    }
}

        //tes saya

        //tes te piaaa
        //tesdua
        //tes apin
        //tessssenggar

