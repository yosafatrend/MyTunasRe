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

public class AboutActivity extends AppCompatActivity {
    ConstraintLayout expandableView,expandableView2,expandableView3,expandableView5,Cardview_visi,Cardview_syarat,Cardview_kebijakan, Cardview_author;
    Button arrowBtn,arrowBtn2,arrowBtn3, arrowBtn5;
    CardView cardView,cardView2,cardView3,cardView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        expandableView = findViewById(R.id.expandableView);
        Cardview_visi = findViewById(R.id.cardview_visi);
        arrowBtn = findViewById(R.id.arrowBtn);
        cardView = findViewById(R.id.cradView);

        Cardview_visi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }
            }
        });
        expandableView2 = findViewById(R.id.expandableView2);
        Cardview_syarat = findViewById(R.id.cardview_syarat);
        arrowBtn2 = findViewById(R.id.arrowBtn2);
        cardView2 = findViewById(R.id.cradView2);

        Cardview_syarat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView2.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView2.setVisibility(View.VISIBLE);
                    arrowBtn2.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView2.setVisibility(View.GONE);
                    arrowBtn2.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }
            }
        });
        expandableView3 = findViewById(R.id.expandableView3);
        Cardview_kebijakan = findViewById(R.id.cardview_privasi);
        arrowBtn3 = findViewById(R.id.arrowBtn3);
        cardView3 = findViewById(R.id.cradView3);

        Cardview_kebijakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView3.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    expandableView3.setVisibility(View.VISIBLE);
                    arrowBtn3.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    expandableView3.setVisibility(View.GONE);
                    arrowBtn3.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }
            }
        });
        expandableView5 = findViewById(R.id.expandableView5);
        Cardview_author = findViewById(R.id.cardview_author);
        arrowBtn5 = findViewById(R.id.arrowBtn5);
        cardView5 = findViewById(R.id.cradView5);

        Cardview_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView5.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                    expandableView5.setVisibility(View.VISIBLE);
                    arrowBtn5.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                    expandableView5.setVisibility(View.GONE);
                    arrowBtn5.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }

            }
        });
    }
    public void onBack(View view) {
        super.onBackPressed();
    }
}