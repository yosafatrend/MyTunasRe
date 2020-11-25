package com.spect.mytunas.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.spect.mytunas.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileSchoolActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    ConstraintLayout expandableView2,expandableView3,expandableView4,Kotak,Kotak3,Kotak2, Cardview_sejarah, Cardview_jurusan, Cardview_visi;
    Button arrowBtn, arrowBtn2, arrowBtn3;
    CardView cardView,cardView2,cardView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_school);
        expandableView2 = findViewById(R.id.expandableView);
        Cardview_sejarah = findViewById(R.id.cardview_sejarah);
        Kotak = findViewById(R.id.kotak);
        arrowBtn = findViewById(R.id.arrowBtn);
        cardView = findViewById(R.id.cradView);

        Cardview_sejarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView2.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView2.setVisibility(View.VISIBLE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView2.setVisibility(View.GONE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }
            }
        });
        expandableView3 = findViewById(R.id.expandableView3);
        Kotak2 = findViewById(R.id.kotak2);
        Cardview_jurusan = findViewById(R.id.cardview_jurusan);
        arrowBtn2 = findViewById(R.id.arrowBtn2);
        cardView2 = findViewById(R.id.cradView2);

        Cardview_jurusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView3.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView3.setVisibility(View.VISIBLE);
                    arrowBtn2.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView3.setVisibility(View.GONE);
                    arrowBtn2.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }
            }
        });
        expandableView4 = findViewById(R.id.expandableView4);
        Kotak3 = findViewById(R.id.kotak3);
        Cardview_visi = findViewById(R.id.cardview_visi);
        arrowBtn3 = findViewById(R.id.arrowBtn3);
        cardView3 = findViewById(R.id.cradView3);

        Cardview_visi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView4.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    expandableView4.setVisibility(View.VISIBLE);
                    arrowBtn3.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    expandableView4.setVisibility(View.GONE);
                    arrowBtn3.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }
            }
        });
        ImageSlider imageSlider=findViewById(R.id.slider_tkj);{
        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTKJ%2Fslider%201.jpg?alt=media&token=7015e602-6e3a-4a12-9258-d3d43a89f934"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTKJ%2Fslider%202.jpg?alt=media&token=82c9de30-7897-42b0-8ae7-991e7811b7e0"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTKJ%2Fslider%203.jpg?alt=media&token=0dcdc6f1-5e51-48fa-aaf5-d07173a3c1ef"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTKJ%2Fslider%204.jpg?alt=media&token=a912efc9-c1c1-49f5-ac36-36064a80bec7"));
        imageSlider.setImageList(slideModels,true);}

        ImageSlider imageSlider2=findViewById(R.id.slider_mm);{
            List<SlideModel> slideModels=new ArrayList<>();
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FMM%2Fslider%201.jpg?alt=media&token=665f33b2-702e-425d-9acd-8de0a7bd2fb7"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FMM%2Fslider%202.jpg?alt=media&token=fffd8ab1-f2e3-43bb-847d-09fa124c3e15"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FMM%2Fslider%203.jpg?alt=media&token=4cf58dd5-5f4b-40e4-8363-aa206a2e0667"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FMM%2Fslider%204.jpg?alt=media&token=0c1fc93d-63d7-4436-af17-eb53d0621e32"));
            imageSlider2.setImageList(slideModels,true);}

        ImageSlider imageSlider3=findViewById(R.id.slider_bc);{
            List<SlideModel> slideModels=new ArrayList<>();
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FBroadcast%2Fslider%201.jpg?alt=media&token=15c660a2-b6ce-4009-8ca6-7f9e6e40a5e0"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FBroadcast%2Fslider%202.jpg?alt=media&token=ca63af07-aec3-418c-830a-aa503168c573"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FBroadcast%2Fslider%203.jpg?alt=media&token=3eb2cb00-9c70-4d54-81ed-b2b0d97973d7"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FBroadcast%2Fslider%204.jpg?alt=media&token=f178a6aa-e29a-453c-8d61-6d50086403be"));
            imageSlider3.setImageList(slideModels,true);}

        ImageSlider imageSlider4=findViewById(R.id.slider_listrik);{
            List<SlideModel> slideModels=new ArrayList<>();
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTITL%2Fslider%201.jpg?alt=media&token=c6795678-1a39-440f-939a-d79aa91a6a57"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTITL%2Fslider%202.jpg?alt=media&token=2decd6f0-67f1-4d30-9ba0-409c8a28af74"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTITL%2Fslider%203.jpg?alt=media&token=71d148f7-5868-4f85-971d-09e87805dc1b"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTITL%2Fslider%204.jpg?alt=media&token=23122aad-73b5-4a09-a6c1-8c3310b8fc13"));
            imageSlider4.setImageList(slideModels,true);}

        ImageSlider imageSlider5=findViewById(R.id.slider_apl);{
            List<SlideModel> slideModels=new ArrayList<>();
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FAPL%2Fslider%201.jpg?alt=media&token=2c9f5f05-6d31-41ab-a699-096e4dc220f0"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FAPL%2Fslider%202.jpg?alt=media&token=558fbf70-4cb4-4b35-ae78-67856dd435b8"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FAPL%2Fslider%203.jpg?alt=media&token=b5f572a0-c27a-4e09-8c6d-c8d22f20ae71"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FAPL%2Fslider%204.jpg?alt=media&token=2acef5ee-9502-4d89-a1f5-eb6509e8c97b"));
            imageSlider5.setImageList(slideModels,true);}

        ImageSlider imageSlider6=findViewById(R.id.slider_las);{
            List<SlideModel> slideModels=new ArrayList<>();
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FLas%2Fslider%201.jpg?alt=media&token=7d27b66e-38a3-4697-abd2-f4c126cb7afa"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FLas%2Fslider%202.jpg?alt=media&token=4a15e133-9d36-48cf-b241-4d14f0aa787a"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FLas%2Fslider%203.jpg?alt=media&token=de31975f-ab7a-43d9-99c3-75828c778e34"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FLas%2Fslider%204.jpg?alt=media&token=8ed22c75-343c-4cd1-a338-7aff44b8104c"));
            imageSlider6.setImageList(slideModels,true);}

        ImageSlider imageSlider7=findViewById(R.id.slider_tkr);{
            List<SlideModel> slideModels=new ArrayList<>();
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTKR%2Fslider%201.jpg?alt=media&token=08369a31-38c9-4562-8130-87802a7d5088"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTKR%2Fslider%202.jpg?alt=media&token=2e39de00-f2d9-4eca-966b-510238173f19"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTKR%2Fslider%203.jpg?alt=media&token=a67ee06a-0bfc-4b34-ad6b-0db2c462f95c"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FTKR%2Fslider%204.jpg?alt=media&token=1078a167-11c0-436b-91fb-0278f839f2e0"));
            imageSlider7.setImageList(slideModels,true);}

        ImageSlider imageSlider8=findViewById(R.id.slider_mesin);{
            List<SlideModel> slideModels=new ArrayList<>();
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FMesin%2Fslider%201.jpg?alt=media&token=cecdd5c5-4049-4951-8e44-8a3ad7001444"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FMesin%2Fslider%202.jpg?alt=media&token=1cbfea75-2db8-4adc-99a5-81c7fc6d2b34"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FMesin%2Fslider%203.jpg?alt=media&token=816b202d-c3d5-4dd3-af11-f65fbbf8067d"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/mytunas-a90f2.appspot.com/o/MajorsSlider%2FMesin%2Fslider%204.jpg?alt=media&token=20cc6a12-33b5-4b64-84d8-864a660742f0"));
            imageSlider8.setImageList(slideModels,true);}

    }
    public void onBack(View view) {
        super.onBackPressed();
    }
}