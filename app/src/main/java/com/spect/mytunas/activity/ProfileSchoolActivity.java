package com.spect.mytunas.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

import com.spect.mytunas.R;

public class ProfileSchoolActivity extends AppCompatActivity  {

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
    }
    public void onBack(View view) {
        super.onBackPressed();
    }
}