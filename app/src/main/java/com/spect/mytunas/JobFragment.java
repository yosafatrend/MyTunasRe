package com.spect.mytunas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
public class JobFragment extends Fragment {
    public JobFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_job, container, false);
        final AutoCompleteTextView actv1 = (AutoCompleteTextView) v.findViewById(R.id.actv1);
        ImageView image = (ImageView) v.findViewById(R.id.image);
        actv1.setThreshold(2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,items);
        actv1.setAdapter(adapter);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv1.showDropDown();
            }
        });
        return v;
    }
    private static final String[] items = new String[]{"Pati, Jateng","Rembang, Jateng", "Kudus, Jateng"};
}
