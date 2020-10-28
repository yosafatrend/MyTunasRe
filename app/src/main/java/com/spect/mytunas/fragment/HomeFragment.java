package com.spect.mytunas.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.spect.mytunas.R;
import com.spect.mytunas.Utils;
import com.spect.mytunas.adapter.NewsAdapter;
import com.spect.mytunas.adapter.NewsHomeAdapter;
import com.spect.mytunas.api.ApiNewsClient;
import com.spect.mytunas.api.ApiNewsInterface;
import com.spect.mytunas.models.Article;
import com.spect.mytunas.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.spect.mytunas.fragment.NewsFragment.API_KEY;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private NewsHomeAdapter adapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ImageSlider imageSlider=v.findViewById(R.id.slider);

        recyclerView = v.findViewById(R.id.rvNewsHome);
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
//            call = apiInterface.getNews("id", "health", API_KEY);
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
}
