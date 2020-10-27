package com.spect.mytunas.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.google.android.material.snackbar.Snackbar;
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
import com.spect.mytunas.models.Siswa;
import com.spect.mytunas.models.User;

import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final int CHOOSE_IMAGE = 101;
    TextView textView, tvJurusanError, tvJenkelError, tvStatusError;
    Spinner spJurusan, spJenkel, spStatus, spKelas;
    CircleImageView imageVew;
    TextInputEditText edtNama, edtNis, edtEmail, edtWa, edtAlamat, edtFb, edtIg, edtTwt;
    Uri uriProfileImage;
    ProgressBar progressBar;
    String profileImageUrl;
    Boolean isKelas;
    FirebaseAuth mAuth;
    FirebaseUser user;
    ConstraintLayout editProfileActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mAuth = FirebaseAuth.getInstance();

        textView = findViewById(R.id.textView6);
        editProfileActivity = findViewById(R.id.EditProfileActivity);
        tvJenkelError = findViewById(R.id.tvJenkelError);
        tvJurusanError = findViewById(R.id.tvJurusanError);
        tvStatusError = findViewById(R.id.tvStatusError);
        imageVew = findViewById(R.id.imgChoose);
        edtNama = findViewById(R.id.edtNamaP);
        edtNis = findViewById(R.id.edtNisP);
        edtEmail = findViewById(R.id.edtEmailP);
        edtWa = findViewById(R.id.edtTeleponP);
        edtAlamat = findViewById(R.id.edtAlamatP);
        edtFb = findViewById(R.id.edtURLFbP);
        edtIg = findViewById(R.id.edtIgUserP);
        edtTwt = findViewById(R.id.edtTwUserP);
        spJenkel = findViewById(R.id.spJenkelP);
        spJurusan = findViewById(R.id.spJurusanP);
        spStatus = findViewById(R.id.spStatusP);
        spKelas = findViewById(R.id.spKelas);
        isKelas = false;
        spKelas.setEnabled(false);

        ArrayAdapter<CharSequence> arrayAdapterGender = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        arrayAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJenkel.setAdapter(arrayAdapterGender);
        spJenkel.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapterJurusan = ArrayAdapter.createFromResource(this, R.array.jurusan, android.R.layout.simple_spinner_item);
        arrayAdapterJurusan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJurusan.setAdapter(arrayAdapterJurusan);
        spJurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DatabaseReference siswa = FirebaseDatabase.getInstance().getReference("siswa");
                DatabaseReference childSiswa = siswa.child(mAuth.getCurrentUser().getUid());
                childSiswa.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            if (!snapshot.child("kelas").getValue().toString().equals("")){
                                setKelasContent(snapshot.child("kelas").getValue().toString());
                            }
                        }catch (Exception e){
                         setKelasContent("x");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> arrayAdapterStatus = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        arrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(arrayAdapterStatus);
        spStatus.setOnItemSelectedListener(this);

        user = mAuth.getCurrentUser();
        if (user.isEmailVerified()) {
            Toast.makeText(this, "Email is verified", Toast.LENGTH_SHORT).show();
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
                String nama = edtNama.getText().toString();
                String email = edtEmail.getText().toString();
                String nis = edtNis.getText().toString();
                String status = spStatus.getSelectedItem().toString();
                String alamat = edtAlamat.getText().toString();
                String gender = spJenkel.getSelectedItem().toString();
                String jurusan = spJurusan.getSelectedItem().toString();

                saveUserInformation(new Siswa(
                   edtEmail.getText().toString(), edtNama.getText().toString(), edtNis.getText().toString(), profileImageUrl
                ));
            }
        });

    }

    private void setKelasContent(String kelas){
       // Snackbar.make(editProfileActivity, kelas, Snackbar.LENGTH_SHORT).show();
        String jurusan = spJurusan.getSelectedItem().toString();
        if (jurusan.equals("Multimedia")){
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.mm, android.R.layout.simple_spinner_item);
            setAdapterKelas(arrayAdapter, kelas);
        }else if(jurusan.equals("Teknik Komputer dan Jaringan")){
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.tkj, android.R.layout.simple_spinner_item);
            setAdapterKelas(arrayAdapter, kelas);
        }else if(jurusan.equals("Broadcasting")){
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.bc, android.R.layout.simple_spinner_item);
            setAdapterKelas(arrayAdapter, kelas);
        }else if(jurusan.equals("Teknik Pemesinan")){
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.tpm, android.R.layout.simple_spinner_item);
            setAdapterKelas(arrayAdapter, kelas);
        }else if(jurusan.equals("Teknik Kendaraan Ringan")){
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.tkr, android.R.layout.simple_spinner_item);
            setAdapterKelas(arrayAdapter, kelas);
        }else if(jurusan.equals("Teknik Pengelasan")){
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.las, android.R.layout.simple_spinner_item);
            setAdapterKelas(arrayAdapter, kelas);
        }else if(jurusan.equals("Teknik Instalasi Tenaga Listrik")){
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.titl, android.R.layout.simple_spinner_item);
            setAdapterKelas(arrayAdapter, kelas);
        }else if(jurusan.equals("Analisis Pengujian Laboratorium")){
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.apl, android.R.layout.simple_spinner_item);
            setAdapterKelas(arrayAdapter, kelas);
        }else{
            spKelas.setEnabled(false);
            isKelas = false;
        }
        spKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setAdapterKelas(ArrayAdapter<CharSequence> arrayAdapter, String kelas){
        spKelas.setEnabled(true);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKelas.setAdapter(arrayAdapter);
        if (!kelas.equals("")){
            spKelas.setSelection(arrayAdapter.getPosition(kelas));
        }else{
          //  Snackbar.make(editProfileActivity, "BA", Snackbar.LENGTH_SHORT).show();
        }
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
                        edtAlamat.setText(snapshot.child("alamat").getValue().toString());
                        edtWa.setText(snapshot.child("wa").getValue().toString());
                        edtFb.setText(snapshot.child("urlFb").getValue().toString());
                        edtIg.setText(snapshot.child("urlIg").getValue().toString());
                        edtTwt.setText(snapshot.child("urlTwt").getValue().toString());
                        if (snapshot.child("gender").getValue().toString().equals("Laki-laki")){
                            spJenkel.setSelection(1);
                        }else if (snapshot.child("gender").getValue().toString().equals("Perempuan")){
                            spJenkel.setSelection(2);
                        }
                        if (snapshot.child("status").getValue().toString().equals("Siswa")){
                            spStatus.setSelection(1);
                        }else if (snapshot.child("status").getValue().toString().equals("Alumni")){
                            spStatus.setSelection(2);
                        }
                        if (snapshot.child("jurusan").getValue().toString().equals("Teknik Komputer dan Jaringan")){
                            spJurusan.setSelection(1);
                        } else if (snapshot.child("jurusan").getValue().toString().equals("Multimedia")){
                            spJurusan.setSelection(2);
                        } else if (snapshot.child("jurusan").getValue().toString().equals("Broadcasting")){
                            spJurusan.setSelection(3);
                        } else if (snapshot.child("jurusan").getValue().toString().equals("Teknik Pemesinan")){
                            spJurusan.setSelection(4);
                        } else if (snapshot.child("jurusan").getValue().toString().equals("Teknik Kendaraan Ringan")){
                            spJurusan.setSelection(5);
                        }else if (snapshot.child("jurusan").getValue().toString().equals("Teknik Pengelasan")){
                            spJurusan.setSelection(6);
                        }else if (snapshot.child("jurusan").getValue().toString().equals("Teknik Instalasi Tenaga Listrik")){
                            spJurusan.setSelection(7);
                        }else if (snapshot.child("jurusan").getValue().toString().equals("Analisis Pengujian Laboratorium")){
                            spJurusan.setSelection(8);
                        }
                        setKelasContent(snapshot.child("kelas").getValue().toString());
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

    private void saveUserInformation(Siswa siswaa) {
        String nama = edtNama.getText().toString();
        String email = edtEmail.getText().toString();
        String nis = edtNis.getText().toString();
        String status = spStatus.getSelectedItem().toString();
        String alamat = edtAlamat.getText().toString();
        String wa = edtWa.getText().toString();
        String kelas = null;
        String gender = spJenkel.getSelectedItem().toString();
        String jurusan = spJurusan.getSelectedItem().toString();
        String fb = edtFb.getText().toString();
        String twt = edtTwt.getText().toString();
        String ig = edtIg.getText().toString();
        if (nama.isEmpty()) {
            edtNama.setError("Nama harus diisi");
            edtNama.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            edtEmail.setError("Email harus diisi");
            edtEmail.requestFocus();
            return;
        }
        if (nis.isEmpty()) {
            edtNis.setError("Nis harus diisi");
            edtNis.requestFocus();
            return;
        }if (status.equals("- Pilih Status -")) {
            tvStatusError.setError("Status harus diisi");
            tvStatusError.requestFocus();
            Snackbar.make(editProfileActivity, "Mohon untuk mengisi status", Snackbar.LENGTH_SHORT).show();
            return;
        }if (alamat.isEmpty()) {
            edtAlamat.setError("Alamat harus diisi");
            edtAlamat.requestFocus();
            return;
        }if (gender.equals("- Pilih Gender -")) {
            tvJenkelError.setError("Gender harus diisi");
            tvJenkelError.requestFocus();
            Snackbar.make(editProfileActivity, "Mohon untuk mengisi gender", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (jurusan.equals("- Pilih Jurusan -")) {
            tvJurusanError.setError("Jurusan harus diisi");
            tvJurusanError.requestFocus();
            Snackbar.make(editProfileActivity, "Mohon untuk mengisi jurusan", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (isKelas = true){
            kelas = spKelas.getSelectedItem().toString();
            if (kelas.equals("- Pilih Kelas -") || kelas.isEmpty()) {
                tvJurusanError.setError("Kelas harus diisi");
                tvJurusanError.requestFocus();
                Snackbar.make(editProfileActivity, "Mohon untuk mengisi kelas", Snackbar.LENGTH_SHORT).show();
                return;
            }
        }
        if (!fb.contains("facebook") && !fb.isEmpty()){
            edtFb.setError("Mohon masukkan url dengan benar");
            edtFb.requestFocus();
        }
        if (!ig.contains("instagram") && !fb.isEmpty()){
            edtIg.setError("Mohon masukkan url dengan benar");
            edtIg.requestFocus();
        }
        if (!twt.contains("twitter") && !fb.isEmpty()){
            edtTwt.setError("Mohon masukkan url dengan benar");
            edtTwt.requestFocus();
        }

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap<String, Object> values = new HashMap<>();
            values.put("email", edtEmail.getText().toString());
            values.put("nama_lengkap", edtNama.getText().toString());
            values.put("nis", edtNis.getText().toString());
            values.put("status", status);
            values.put("alamat", alamat);
            values.put("gender", gender);
            values.put("jurusan", jurusan);
            values.put("kelas", kelas);
            values.put("wa", wa);
            values.put("urlFb", fb);
            values.put("urlIg", ig);
            values.put("urlTwt", twt);
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
                                    Toast.makeText(EditProfileActivity.this, "upload sukses " + profileImageUrl, Toast.LENGTH_SHORT).show();

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
