package com.spect.mytunas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.spect.mytunas.R;

public class addAnnounce extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spKelas, spJurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announce);
        spKelas = findViewById(R.id.edtkelas);
        spJurusan = findViewById(R.id.edtjurusan);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.jurusan, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJurusan.setAdapter(arrayAdapter);
        spJurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jurusan = spJurusan.getSelectedItem().toString();
                if (jurusan.equals("Multimedia")) {
                    ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.mm, android.R.layout.simple_spinner_item);
                    setAdapterKelas(arrayAdapter);
                } else if (jurusan.equals("Teknik Komputer dan Jaringan")) {
                    ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.tkj, android.R.layout.simple_spinner_item);
                    setAdapterKelas(arrayAdapter);
                } else if (jurusan.equals("Broadcasting")) {
                    ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.bc, android.R.layout.simple_spinner_item);
                    setAdapterKelas(arrayAdapter);
                } else if (jurusan.equals("Teknik Pemesinan")) {
                    ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.tpm, android.R.layout.simple_spinner_item);
                    setAdapterKelas(arrayAdapter);
                } else if (jurusan.equals("Teknik Kendaraan Ringan")) {
                    ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.tkr, android.R.layout.simple_spinner_item);
                    setAdapterKelas(arrayAdapter);
                } else if (jurusan.equals("Teknik Pengelasan")) {
                    ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.las, android.R.layout.simple_spinner_item);
                    setAdapterKelas(arrayAdapter);
                } else if (jurusan.equals("Teknik Instalasi Tenaga Listrik")) {
                    ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.titl, android.R.layout.simple_spinner_item);
                    setAdapterKelas(arrayAdapter);
                } else if (jurusan.equals("Analisis Pengujian Laboratorium")) {
                    ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.apl, android.R.layout.simple_spinner_item);
                    setAdapterKelas(arrayAdapter);
                } else {
                    spKelas.setEnabled(false);

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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setAdapterKelas(ArrayAdapter<CharSequence> arrayAdapter) {
        spKelas.setEnabled(true);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKelas.setAdapter(arrayAdapter);
    }
    public void onBack(View view) {
        super.onBackPressed();
    }
}