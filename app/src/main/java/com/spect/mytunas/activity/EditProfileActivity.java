package com.spect.mytunas.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.spect.mytunas.R;
import com.spect.mytunas.models.User;

import java.io.IOException;
import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 101;
    TextView textView;

    ImageView imageVew;
    TextInputEditText edtNama, edtNis, edtEmail, edtJenkel, edtStatus, edtJurusan, edtTelepon, edtAlamat;

    Uri uriProfileImage;
    ProgressBar progressBar;

    String profileImageUrl;
    FirebaseAuth mAuth;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mAuth = FirebaseAuth.getInstance();

        textView = findViewById(R.id.textView6);
        imageVew = findViewById(R.id.imageView);
        edtNama = findViewById(R.id.edtNamaP);
        edtNis = findViewById(R.id.edtNisP);
        edtEmail = findViewById(R.id.edtEmailP);

        user = mAuth.getCurrentUser();
        if (user.isEmailVerified()) {
            Toast.makeText(this, "email is verified", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog dialog = new AlertDialog.Builder(EditProfileActivity.this)
                    .setTitle("Email Verification")
                    .setMessage("Email belum diverifikasi, silahkan verifikasi terlebih dahulu. Belum menerima email?")
                    .setNegativeButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("Kirim Ulang", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Email berhasil dikirim", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }).create();
            dialog.show();
        }


        progressBar = findViewById(R.id.progressBar);
        loadUserInformation();

        imageVew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();

            }
        });


        findViewById(R.id.buttonsave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation(new User(
                   edtEmail.getText().toString(), edtNama.getText().toString(), edtNis.getText().toString(), profileImageUrl
                ));


            }
        });

    }


    private void loadUserInformation() {

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Toast.makeText(this, user.getPhotoUrl().toString(), Toast.LENGTH_SHORT).show();
                Log.d("tag", "PhotoLink" +  user.getPhotoUrl().toString());
//                Glide.with(this)
//                        .load(user.getPhotoUrl().toString()).into(imageVew);


            }
            DatabaseReference siswa = FirebaseDatabase.getInstance().getReference("siswa");
            DatabaseReference childSiswa = siswa.child(mAuth.getCurrentUser().getUid());
            childSiswa.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Toast.makeText(getApplicationContext(), snapshot.child("email").getValue().toString(), Toast.LENGTH_SHORT).show();
                    try {
                        edtEmail.setText(snapshot.child("email").getValue().toString());
                        edtNama.setText(snapshot.child("nama_lengkap").getValue().toString());
                        edtNis.setText(snapshot.child("nis").getValue().toString());
                        Glide.with(getApplicationContext())
                                .load(snapshot.child("imgUri").getValue().toString()).into(imageVew);
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

    private void saveUserInformation(User users) {
        String displayame = edtNama.getText().toString();
        if (displayame.isEmpty()) {
            edtNama.setError("nama tidak boleh kosong");
            edtNama.requestFocus();
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap<String, Object> values = new HashMap<>();
            values.put("email", edtEmail.getText().toString());
            values.put("nama_lengkap", edtNama.getText().toString());
            values.put("nis", edtNis.getText().toString());
            values.put("imgUri", profileImageUrl);
            DatabaseReference siswa = FirebaseDatabase.getInstance().getReference("siswa");
            DatabaseReference childSiswa = siswa.child(mAuth.getCurrentUser().getUid());
            childSiswa.updateChildren(values).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfileImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                imageVew.setImageBitmap(bitmap);
                uploadImageToFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseStorage() {
        StorageReference profileImageRef =
                FirebaseStorage.getInstance().getReference("PhotoProfile/" + System.currentTimeMillis() + ".jpg");
        if (uriProfileImage != null) {
            progressBar.setVisibility(View.VISIBLE);
            profileImageRef.putFile(uriProfileImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    profileImageUrl = task.getResult().toString();
                                    Toast.makeText(EditProfileActivity.this, "uplad sukses " + profileImageUrl, Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(EditProfileActivity.this, "gagal upload", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void showImageChooser() {
        android.content.Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(android.content.Intent.createChooser(intent, "Pilih foto profile"), CHOOSE_IMAGE);
    }
}
