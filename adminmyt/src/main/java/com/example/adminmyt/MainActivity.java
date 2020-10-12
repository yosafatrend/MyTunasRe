package com.example.adminmyt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.adminmyt.model.Requests;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference database;
    private EditText edtInformasi,edtPengirim;
    private ProgressDialog progressBar;
    private ImageView profilePics;
    private String sInfromasi,sPengirim,sPid, profileImageUrl;
    private Button btnUpload, btnCancel;
    private  StorageReference storageReference;
    private FirebaseStorage firebaseStorage;
    public Uri imageUri;
    private RequestQueue mRequestQue;
    private String URL = "https://fcm.googleapis.com/fcm/send";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance().getReference();

        mRequestQue = Volley.newRequestQueue(this);

        edtInformasi = findViewById(R.id.edtInformasi);
        edtPengirim = findViewById(R.id.edtPengirim);
        profilePics = findViewById(R.id.profilePic);
        btnUpload = findViewById(R.id.btnUpload);
        btnCancel = findViewById(R.id.btn_cancel);
        sPid = getIntent().getStringExtra("id");
        sInfromasi = getIntent().getStringExtra("infromasi");
        sPengirim = getIntent().getStringExtra("pengirim");

        DatabaseReference berita = FirebaseDatabase.getInstance().getReference("Berita");
        DatabaseReference childBerita = berita.child(sPid);
        childBerita.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    Toast.makeText(MainActivity.this, snapshot.child("imgUri").getValue().toString(), Toast.LENGTH_SHORT).show();
                    Glide.with(getApplicationContext())
                            .load(snapshot.child("imgUri").getValue().toString()).into(profilePics);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error : " + e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        edtInformasi.setText(sInfromasi);
        edtPengirim.setText(sPengirim);

        edtPengirim.setText(sPengirim);
        edtInformasi.setText(sInfromasi);

        if (sPid.equals("")){
            btnUpload.setText("Save");
            btnCancel.setText("Cancel");
        } else {
            btnUpload.setText("Edit");
            btnCancel.setText("Delete");
        }

        profilePics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choosePicture();

            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sinformasi = edtInformasi.getText().toString();
                String Spengirim = edtPengirim.getText().toString();


                if (btnUpload.getText().equals("Save")){
                    // perintah save

                    if (Sinformasi.equals("")) {
                        edtInformasi.setError("Silahkan masukkan nama");
                        edtInformasi.requestFocus();
                    } else if (Spengirim.equals("")) {
                        edtPengirim.setError("Silahkan masukkan email");
                        edtPengirim.requestFocus();
                    } else {
                        progressBar = ProgressDialog.show(MainActivity.this,
                                null,
                                "Please wait...",
                                true,
                                false);

                        submitUser(new Requests(
                                Sinformasi.toLowerCase(),
                                Spengirim.toLowerCase(),
                                profileImageUrl));

                    }
                } else {
                    // perintah edit
                    if (Sinformasi.equals("")) {
                        edtInformasi.setError("Silahkan masukkan nama");
                        edtInformasi.requestFocus();
                    } else if (Spengirim.equals("")) {
                        edtPengirim.setError("Silahkan masukkan email");
                        edtPengirim.requestFocus();
                    } else {
                        progressBar = ProgressDialog.show(MainActivity.this,
                                null,
                                "Please wait...",
                                true,
                                false);

                        editUser(new Requests( Sinformasi.toLowerCase(),Spengirim.toLowerCase(), profileImageUrl),sPid);

                    }
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnCancel.getText().equals("Cancel")) {
                    //tutup page
                    finish();
                } else {
                    // delete
                }

            }
        });
    }

    private void choosePicture() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(android.content.Intent.createChooser(intent, "Pilih foto profile"), 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
                profilePics.setImageURI(imageUri);
                uploadImageToFirebaseStorage();

            }
        }


    private void uploadImageToFirebaseStorage() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Upload Image");
        progressDialog.show();


//        final StorageReference profileImageRef =
//                FirebaseStorage.getInstance().getReference("NewsPict/" + System.currentTimeMillis() + ".jpg");
        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                profileImageUrl = task.getResult().toString();
                                Toast.makeText(MainActivity.this, "uplad sukses " + profileImageUrl, Toast.LENGTH_SHORT).show();

                            }
                        });
                       // Toast.makeText(MainActivity.this, "uplad sukses" + img, Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Upload gagal", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercent = ( 100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                progressDialog.setMessage("Progress : " + (int) progressPercent+ "%");
            }
        });
    }


    private void submitUser(Requests requests) {
        String info = edtInformasi.getText().toString();
        sendNotification(info);
        database.child("Berita")
                .push()
                .setValue(requests)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        progressBar.dismiss();
                        edtPengirim.setText("");
                        edtInformasi.setText("");

                        Toast.makeText(MainActivity.this,
                                "Data Berhasil ditambahkan",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));

                    }

                });
    }

    private void editUser(Requests requests, String id) {
        database.child("Berita")
                .child(id)
                .setValue(requests)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        progressBar.dismiss();

                        edtInformasi.setText("");
                        edtPengirim.setText("");

                        Toast.makeText(MainActivity.this,
                                "Data Berhasil diedit",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));

                    }

                });
    }
    private void sendNotification(String info) {

        JSONObject json = new JSONObject();
        try {
            json.put("to","/topics/"+"news");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title","Pengumuman baru");
            notificationObj.put("body",info);

            JSONObject extraData = new JSONObject();
            extraData.put("brandId","puma");
            extraData.put("category","Shoes");


            json.put("notification",notificationObj);
            json.put("data",extraData);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("MUR", "onResponse: ");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("MUR", "onError: "+error.networkResponse);
                }
            }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAA4LA8Of0:APA91bGx6AvObuyiS1Nzx3UsrfteKiYb2x7Te6ZXtKbbdH3e7prUp6wCVvfWFbIDltQE7-_9j3jqnUzZURJHxE-A3OrPk418MAiHgwUYOUMvvu-jpyPboH-bfxgbdg4CdgZrtO__EAZ8");
                    return header;
                }
            };
            mRequestQue.add(request);
        }
        catch (JSONException e)

        {
            e.printStackTrace();
        }
    }
}