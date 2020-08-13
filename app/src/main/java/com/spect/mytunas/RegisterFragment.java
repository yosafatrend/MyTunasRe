package com.spect.mytunas;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    TextInputEditText edtNis, edtPass, edtNama, edtEmail;
    Button btnRegist;
    ProgressBar progressBar;
    private DatabaseReference Siswa;
    private FirebaseAuth mAuth;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        btnRegist = v.findViewById(R.id.btnRegist);
        edtNama = v.findViewById(R.id.edtNama);
        edtNis = v.findViewById(R.id.edtNis);
        edtPass = v.findViewById(R.id.edtPass);
        edtEmail = v.findViewById(R.id.edtEmail);
        progressBar = v.findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null){
            Intent intent = new Intent(getActivity(), DashboardActivity.class);
            Toast.makeText(getActivity(), "Anda sudah login", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            getActivity().finish();
        }

    }

    private void registerUser() {
        final String name = edtNama.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String nis = edtNis.getText().toString().trim();
        final String pass = edtPass.getText().toString().trim();

        if (name.isEmpty()){
            edtNama.setError("Nama perlu diisi");
            edtNama.requestFocus();
            return;
        } if (email.isEmpty()){
            edtEmail.setError("Email perlu diisi");
            edtEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Format email salah");
            edtEmail.requestFocus();
            return;
        }
        if (pass.length() < 6) {
            edtPass.setError("Password harus lebih dari 6 karakter");
            edtPass.requestFocus();
            return;
        }
        if (nis.isEmpty()){
            edtNis.setError("NIS perlu diisi");
            edtNis.requestFocus();
            return;
        }
        if (pass.isEmpty()){
            edtPass.setError("Password perlu diisi");
            edtPass.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Siswa siswa = new Siswa(nis, name, pass, email);

                    FirebaseDatabase.getInstance().getReference("siswa")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(siswa).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Registration success", Toast.LENGTH_SHORT).show();
                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });
                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(getActivity(), "Anda telah terdaftar", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                } else {
                    Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("tag", task.getException().getMessage());
                }
            }

        });
    }
}
