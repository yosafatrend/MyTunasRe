package com.example.adminmyt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;

import com.example.adminmyt.model.Requests;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;


import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.example.adminmyt.adapter.RequestAdapterRecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeActivity extends AppCompatActivity {

    private DatabaseReference database;

    private ArrayList<Requests> daftarReq;
    private RequestAdapterRecyclerView requestAdapterRecyclerView;

    private RecyclerView rc_list_request;
    private ProgressDialog loading;
    private FloatingActionButton fab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = FirebaseDatabase.getInstance().getReference();

        rc_list_request = findViewById(R.id.rcList);
        fab_add = findViewById(R.id.fab_add);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rc_list_request.setLayoutManager(mLayoutManager);
        rc_list_request.setItemAnimator(new DefaultItemAnimator());

        loading = ProgressDialog.show(HomeActivity.this,
                null,
                "Please wait...",
                true,
                false);

        database.child("Berita").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftarReq = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object Wisata
                     * Dan juga menyimpan primary key pada object Wisata
                     * untuk keperluan Edit dan Delete data
                     */
                    Requests requests = noteDataSnapshot.getValue(Requests.class);
                    requests.setKey(noteDataSnapshot.getKey());


                    /**
                     * Menambahkan object Wisata yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftarReq.add(requests);
                }

                /**
                 * Inisialisasi adapter dan data hotel dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                requestAdapterRecyclerView = new RequestAdapterRecyclerView(daftarReq, HomeActivity.this);
                rc_list_request.setAdapter(requestAdapterRecyclerView);
                loading.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /**
                 * Kode ini akan dipanggil ketika ada error dan
                 * pengambilan data gagal dan memprint error nya
                 * ke LogCat
                 */
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                loading.dismiss();
            }
        });

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class)
                        .putExtra("id", "")
                        .putExtra("pengirim", "")
                        .putExtra("infromasi", ""));
            }
        });
    }
}