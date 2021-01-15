package com.spect.mytunas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spect.mytunas.R;
import com.spect.mytunas.adapter.AnnounceAdapter;
import com.spect.mytunas.models.Announce;

import java.util.ArrayList;

public class AnnounceActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private DatabaseReference database;

    private ArrayList<Announce> daftarReq;
    private AnnounceAdapter requestAdapterRecyclerView;
    private LinearLayout noAnnounceClass, noAnnounceAll;

    private RecyclerView rc_list_request, rc_announce_class;
    private FloatingActionButton fab_add;
    private ProgressDialog loading;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman);
        toolbar = findViewById(R.id.toolbar_announce);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("Ann");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_pengumuman);

        database = FirebaseDatabase.getInstance().getReference();

        rc_list_request = findViewById(R.id.recyclerView3);
        rc_announce_class = findViewById(R.id.rvAnnClass);
        noAnnounceClass = findViewById(R.id.noAnnounceClass);
        noAnnounceAll = findViewById(R.id.noAnnounceAll);
        fab_add = findViewById(R.id.fab_add);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        rc_list_request.setLayoutManager(mLayoutManager);
        rc_list_request.setItemAnimator(new DefaultItemAnimator());
        rc_announce_class.setLayoutManager(mLayoutManager2);
        rc_announce_class.setItemAnimator(new DefaultItemAnimator());

        loading = ProgressDialog.show(AnnounceActivity.this,
                null,
                "Please wait...",
                true,
                false);
        database.child("Berita").orderByChild("kelas").equalTo( "all").addValueEventListener(new ValueEventListener() {
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
                    Announce requests = noteDataSnapshot.getValue(Announce.class);
                    requests.setKey(noteDataSnapshot.getKey());
                    if (requests == null){
                        rc_list_request.setVisibility(View.GONE);
                        noAnnounceAll.setVisibility(View.VISIBLE);
                    }else{
                        rc_list_request.setVisibility(View.VISIBLE);
                        noAnnounceAll.setVisibility(View.GONE);
                    }


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
                requestAdapterRecyclerView = new AnnounceAdapter(daftarReq, AnnounceActivity.this);
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

        database.child("Berita").orderByChild("kelas").equalTo( "XII TKJ 4").addValueEventListener(new ValueEventListener() {
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
                    Announce requests = noteDataSnapshot.getValue(Announce.class);
                    if (requests == null){
                        rc_announce_class.setVisibility(View.GONE);
                        noAnnounceClass.setVisibility(View.VISIBLE);
                    }else{
                        rc_announce_class.setVisibility(View.VISIBLE);
                        noAnnounceClass.setVisibility(View.GONE);
                    }
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
                requestAdapterRecyclerView = new AnnounceAdapter(daftarReq, AnnounceActivity.this);
                rc_announce_class.setAdapter(requestAdapterRecyclerView);
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
                // sendNotification();
                startActivity(new Intent(AnnounceActivity.this, addAnnounce.class));
            }
        });
    }

    public void onBack(View view) {
        super.onBackPressed();
    }
        }