package com.spect.mytunas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class SchoolFragment extends Fragment {
    private  CardView cardView1, cardView2, cardView3, cardView4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_school, container, false);
        cardView1 =(CardView)v.findViewById(R.id.cV_announce);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), announcement.class));
            }
        });
        cardView2 = (CardView)v.findViewById(R.id.cV_school);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), profile_school.class));
            }
        });
        cardView4 = (CardView)v.findViewById(R.id.cV_class);
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), ClassroomActivity.class));
            }
        });
        return v;
    }
}