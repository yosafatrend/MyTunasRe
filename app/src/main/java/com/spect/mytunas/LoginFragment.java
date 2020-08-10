package com.spect.mytunas;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    private EditText edtEmail, edtPass;
    private Button btnLogin;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        edtEmail = v.findViewById(R.id.edtEmailLog);
        edtPass = v.findViewById(R.id.edtPassLog);
        btnLogin = v.findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();
        progressBar = v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        return v;
    }

    private void loginUser(){
        final String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();

        if (pass.isEmpty()){
            edtPass.setError("Password perlu diisi");
            edtPass.requestFocus();
            return;
        }
        if (email.isEmpty()){
            edtEmail.setError("Email perlu diisi");
            edtEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Format email salah");
            edtEmail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Toast.makeText(getActivity(), "Login Berhasil", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "Akun belum terdaftar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
