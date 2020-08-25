package com.spect.mytunas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ImageSlider imageSlider=v.findViewById(R.id.slider);

        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://smkthpati.sch.id/images/berita/gb183.jpg"));
        slideModels.add(new SlideModel("https://smkthpati.sch.id/images/galeri/gb184.jpg"));
        slideModels.add(new SlideModel("https://smkthpati.sch.id/images/berita/gb178.jpg"));
        slideModels.add(new SlideModel("https://lh3.googleusercontent.com/proxy/pDCifrkN6kbuoMQaW8rSIquKtLvRImOy3kLw_rz8VnAVIuQoKIBgsJDfEe1gNuXIrok_9CHl01lGpPPt_zhxydcwPXQM8XkpDcxjLgny3dzYcavIvSwDVt7b"));
        imageSlider.setImageList(slideModels,true);
        return v;
    }
}
