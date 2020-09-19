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
        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) v.findViewById(R.id.actv);
        // Get the string array
        String[] countries = getResources().getStringArray(R.array.jenis_loker);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);


        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView2 = (AutoCompleteTextView) v.findViewById(R.id.actv2);
        // Get the string array
        String[] countries2 = getResources().getStringArray(R.array.tempat_loker);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, countries2);
        textView2.setAdapter(adapter2);

        return v;
    }
}
