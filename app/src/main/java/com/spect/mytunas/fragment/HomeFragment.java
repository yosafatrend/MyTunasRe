package com.spect.mytunas.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spect.mytunas.R;
import com.spect.mytunas.Utils;
import com.spect.mytunas.activity.AnnounceActivity;
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

        recyclerView = v.findViewById(R.id.rvNewsHome);
        rvAnnounceHome = v.findViewById(R.id.rvAnnHome);
        rvJobHome = v.findViewById(R.id.rvJobHome);
        progressBar = v.findViewById(R.id.progressBarHome);

        RecyclerView.LayoutManager mLayoutManagerJob = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvJobHome.setLayoutManager(mLayoutManagerJob);
        rvJobHome.setItemAnimator(new DefaultItemAnimator());

        getListJobLocation("kerja", "pati");

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvAnnounceHome.setLayoutManager(mLayoutManager);
        rvAnnounceHome.setItemAnimator(new DefaultItemAnimator());

        database.child("Berita").orderByChild("kelas").equalTo( "all").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                daftarReq = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Announce requests = noteDataSnapshot.getValue(Announce.class);
                    requests.setKey(noteDataSnapshot.getKey());
                    daftarReq.add(requests);
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
        slideModels.add(new SlideModel(R.drawable.foto_depan));
        slideModels.add(new SlideModel("https://smkthpati.sch.id/images/berita/gb183.jpg"));
        slideModels.add(new SlideModel("https://smkthpati.sch.id/images/galeri/gb184.jpg"));
        slideModels.add(new SlideModel("https://smkthpati.sch.id/images/berita/gb178.jpg"));
        slideModels.add(new SlideModel("https://smkthpati.sch.id/images/galeri/gb185.jpg"));
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
                jobHomeAdapter = new JobHomeAdapter(jobs, getActivity(), getActivity());
                rvJobHome.setAdapter(jobHomeAdapter);
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
