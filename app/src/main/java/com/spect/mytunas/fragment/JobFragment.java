package com.spect.mytunas.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.Api;
import com.google.android.material.textfield.TextInputEditText;
import com.spect.mytunas.R;
import com.spect.mytunas.adapter.JobAdapter;
import com.spect.mytunas.api.ApiJobInterface;
import com.spect.mytunas.models.Article;
import com.spect.mytunas.models.Job;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Job> jobs = new ArrayList<>();
    private JobAdapter adapter;
    private ProgressBar progressBar;
    private Button btnJobSearch;
    private TextInputEditText edtJobName, edtJobLoc;

    public JobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_job, container, false);
        // Get a reference to the AutoCompleteTextView in the layout
//        AutoCompleteTextView textView = (AutoCompleteTextView) v.findViewById(R.id.actv);
//        // Get the string array
//        String[] countries = getResources().getStringArray(R.array.jenis_loker);
//        // Create the adapter and set it to the AutoCompleteTextView
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, countries);
//        textView.setAdapter(adapter);
//
//
//        // Get a reference to the AutoCompleteTextView in the layout
//        AutoCompleteTextView textView2 = (AutoCompleteTextView) v.findViewById(R.id.actv2);
//        // Get the string array
//        String[] countries2 = getResources().getStringArray(R.array.tempat_loker);
//        // Create the adapter and set it to the AutoCompleteTextView
//        ArrayAdapter<String> adapter2 =
//                new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, countries2);
//        textView2.setAdapter(adapter2);
        Log.d("tagjob", "Code masuk");
        recyclerView = v.findViewById(R.id.rvJob);
        progressBar = v.findViewById(R.id.progressBarJob);
        edtJobName = v.findViewById(R.id.edtJobName);
        edtJobLoc = v.findViewById(R.id.edtJobLoc);
        btnJobSearch = v.findViewById(R.id.btnSearchJob);
        progressBar.setVisibility(View.VISIBLE);
        edtJobName.setText("");
        edtJobLoc.setText("");
        btnJobSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if(edtJobName.getText().toString() != null && edtJobLoc.getText().toString() != null){
                    getListJobLocation(edtJobName.getText().toString(), edtJobLoc.getText().toString());
                }
                else if(edtJobName.getText().toString() != null && edtJobLoc.getText().toString() == null){
                    getListJobOnly(edtJobName.getText().toString());
                }
                else if(edtJobName.getText().toString() == null && edtJobLoc.getText().toString() != null){
                    getListJobLocation("kerja", edtJobLoc.getText().toString());
                }
                else if (edtJobName.getText().toString() == "" && edtJobLoc.getText().toString() == ""){
                    Toast.makeText(getActivity(), "Form pencarian perlu diisi salah satu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        getListJobLocation("kerja", "pati");
        super.onAttach(context);
    }

    public void getListJobOnly(String jobName) {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://job-api35.herokuapp.com/api/")
                .addConverterFactory((GsonConverterFactory.create()))
                .build();

        ApiJobInterface apiJobInterface = retrofit.create(ApiJobInterface.class);

        Call<List<Job>> call = apiJobInterface.getJob(jobName);
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                progressBar.setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    Log.d("tagjob", "Code " + response.code());
                    return;
                }
                jobs = response.body();
                adapter = new JobAdapter(jobs, getActivity(), getActivity());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("tagjob", "Code " + t.getMessage());
            }
        });
    }

    public void getListJobLocation(String jobName, String location) {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://job-api35.herokuapp.com/api/")
                .addConverterFactory((GsonConverterFactory.create()))
                .build();

        ApiJobInterface apiJobInterface = retrofit.create(ApiJobInterface.class);

        Call<List<Job>> call = apiJobInterface.getJobLoc(jobName, location);
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                progressBar.setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    Log.d("tagjob", "Code " + response.code());
                    Toast.makeText(getActivity(), "Jaringan anda jelek, mohon dicoba kembali " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                jobs = response.body();
                adapter = new JobAdapter(jobs, getActivity(), getActivity());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("tagjob", "Code " + t.getMessage());
            }
        });
    }
}
