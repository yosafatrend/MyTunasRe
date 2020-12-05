package com.spect.mytunas.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spect.mytunas.R;
import com.spect.mytunas.Utils;
import com.spect.mytunas.activity.AnnounceActivity;
import com.spect.mytunas.activity.DashboardActivity;
import com.spect.mytunas.adapter.AnnounceAdapter;
import com.spect.mytunas.adapter.AnnounceHomeAdapter;
import com.spect.mytunas.adapter.JobAdapter;
import com.spect.mytunas.adapter.JobHomeAdapter;
import com.spect.mytunas.adapter.NewsAdapter;
import com.spect.mytunas.adapter.NewsHomeAdapter;
import com.spect.mytunas.api.ApiJobInterface;
import com.spect.mytunas.api.ApiNewsClient;
import com.spect.mytunas.api.ApiNewsInterface;
import com.spect.mytunas.models.Announce;
import com.spect.mytunas.models.Article;
import com.spect.mytunas.models.Job;
import com.spect.mytunas.models.News;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.spect.mytunas.fragment.NewsFragment.API_KEY;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView, rvAnnounceHome, rvJobHome;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private List<Job> jobs = new ArrayList<>();
    private NewsHomeAdapter adapter;
    private DatabaseReference database;
    private ArrayList<Announce> daftarReq;
    private AnnounceHomeAdapter announceHomeAdapter;
    private JobHomeAdapter jobHomeAdapter;
    private ProgressBar progressBar;
    private LinearLayout noAnnounceHome,noJobHome;
    private TextView tvBerita,tvPekerjaan,tvPengumuman;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ImageSlider imageSlider=v.findViewById(R.id.slider);

        database = FirebaseDatabase.getInstance().getReference();

        noJobHome = v.findViewById(R.id.noJobHome);
        recyclerView = v.findViewById(R.id.rvNewsHome);
        rvAnnounceHome = v.findViewById(R.id.rvAnnHome);
        noAnnounceHome = v.findViewById(R.id.noAnnounceHome);
        rvJobHome = v.findViewById(R.id.rvJobHome);
        progressBar = v.findViewById(R.id.progressBarHome);
        tvBerita = v.findViewById(R.id.homeberita);
        tvPekerjaan = v.findViewById(R.id.homepekerjaan);
        tvPengumuman = v.findViewById(R.id.homepengumuman);
        RecyclerView.LayoutManager mLayoutManagerJob = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvJobHome.setLayoutManager(mLayoutManagerJob);
        rvJobHome.setItemAnimator(new DefaultItemAnimator());

        tvPengumuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(),AnnounceActivity.class));

            }
        });
        tvPekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                JobFragment llf = new JobFragment();
                ft.replace(R.id.fragment_container, llf);
                ft.commit();
            }
        });
        tvBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                NewsFragment llf = new NewsFragment();
                ft.replace(R.id.fragment_container, llf);
                ft.commit();
            }
        });

        getListJobLocation("kerja", "pati");

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setStackFromEnd(true);
        rvAnnounceHome.setLayoutManager(mLayoutManager);
        rvAnnounceHome.setItemAnimator(new DefaultItemAnimator());

        database.child("Berita").orderByChild("kelas").equalTo( "all").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                daftarReq = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Announce requests = noteDataSnapshot.getValue(Announce.class);
                    requests.setKey(noteDataSnapshot.getKey());
                    if (requests == null){
                        rvAnnounceHome.setVisibility(View.GONE);
                        tvPengumuman.setVisibility(View.GONE);
                        noAnnounceHome.setVisibility(View.VISIBLE);
                    }else{
                        rvAnnounceHome.setVisibility(View.VISIBLE);
                        tvPengumuman.setVisibility(View.VISIBLE);
                        noAnnounceHome.setVisibility(View.GONE);
                    }
                    daftarReq.add(requests);
                    Collections.reverse(daftarReq);
                }
                announceHomeAdapter = new AnnounceHomeAdapter(daftarReq, getActivity());
                rvAnnounceHome.setAdapter(announceHomeAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        LoadJson("");

        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/Slider%2Fslider%201.jpg?alt=media&token=87cbc0d6-8e4e-46eb-8a37-8318ee2e1f62"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/Slider%2Fslider%202.jpg?alt=media&token=81d92cdf-84c2-4566-80d1-a31075e2c6b1"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/Slider%2Fslider%203.jpg?alt=media&token=ead6d61d-77cd-4a6d-a371-e4cc8bd18aa1"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/Slider%2Fslider%204.jpg?alt=media&token=a035d78a-a7c4-49da-9914-ea7f8af768eb"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/Slider%2Fslider%205.jpg?alt=media&token=0bcf7fdc-5625-44ec-b918-49e7f50765f1"));
        imageSlider.setImageList(slideModels,true);
        return v;
    }

    public void LoadJson(final String keyword) {
        ApiNewsInterface apiInterface = ApiNewsClient.getApiClient().create(ApiNewsInterface.class);

        String country = Utils.getCountry();

        Call<News> call;

        if (keyword.length() > 0) {
            call = apiInterface.getNewsSearch(keyword, "id", "publishedAt", API_KEY);
        } else {
            call = apiInterface.getNews("kemendikbud", "id", "publishedAt", API_KEY);
        }

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null) {
                    if (!articles.isEmpty()) {
                        articles.clear();
                    }
                    articles = response.body().getArticle();
                    adapter = new NewsHomeAdapter(articles, getActivity());
                    recyclerView.setAdapter(adapter);

                } else {
                    String errorCode;
                    switch (response.code()) {
                        case 404:
                            errorCode = "404 not found";
                            break;
                        case 500:
                            errorCode = "500 server broken";
                            break;
                        default:
                            errorCode = "unknown error";
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
            }
        });
    }

    public void getListJobLocation(String jobName, String location) {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-loker.herokuapp.com/api/")
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
                        noJobHome.setVisibility(View.VISIBLE);
//                    Toast.makeText(getActivity(), "Jaringan anda jelek, mohon dicoba kembali " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                jobs = response.body();
                jobHomeAdapter = new JobHomeAdapter(jobs, getActivity(), getActivity());
                rvJobHome.setAdapter(jobHomeAdapter);
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                noJobHome.setVisibility(View.VISIBLE);
//                Toast.makeText(getContext(), "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("tagjob", "Code " + t.getMessage());
            }
        });


    }

}
