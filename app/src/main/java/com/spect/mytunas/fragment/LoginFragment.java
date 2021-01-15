package com.spect.mytunas.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.spect.mytunas.R;
import com.spect.mytunas.activity.DashboardActivity;

public class LoginFragment extends Fragment {
    private TextInputEditText edtEmail, edtPass;
    private Button btnLogin;
    private TextView tvForgotPass, tvRegister;
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

        edtEmail = v.findViewById(R.id.edtNis);
        edtPass = v.findViewById(R.id.edtPassNis);
        btnLogin = v.findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();
        progressBar = v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        tvForgotPass = v.findViewById(R.id.textView);
        tvRegister = v.findViewById(R.id.textViewRegister);
        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogForgotPassword();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        final ViewPager pager = (ViewPager)getActivity().findViewById(R.id.viewPager);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });
        return v;


    }

    private void dialogForgotPassword() {
        final EditText sendEmail = new EditText(getContext());
        final AlertDialog.Builder passResetDialog = new AlertDialog.Builder(getContext());
        passResetDialog.setTitle("Ganti Password ?");
        passResetDialog.setMessage("Silahkan masukkan email untuk menerima reset link");
        passResetDialog.setView(sendEmail);

        passResetDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String emial =sendEmail.getText().toString();
                mAuth.sendPasswordResetEmail(emial).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Reset link di kirim ke email anda", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "gagal mengirim reset link", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        passResetDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        passResetDialog.create().show();
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
                FirebaseUser user = mAuth.getCurrentUser();

                if (task.isSuccessful()){
                    Toast.makeText(getActivity(), "Login Berhasil", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    getActivity().finish();

                }else{
                    Toast.makeText(getActivity(), "Akun belum terdaftar", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!= null){
            getActivity().finish();
            startActivity(new Intent(getActivity(),DashboardActivity.class));
        }
    }

}
