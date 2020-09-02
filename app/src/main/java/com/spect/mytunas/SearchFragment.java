package com.spect.mytunas;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private Context mContext;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflaterr) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(mContext.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_user).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.search_user);
        searchMenuItem.expandActionView();
        getActivity().getSupportFragmentManager().popBackStack();
        searchView.onActionViewExpanded();
        searchView.clearFocus();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setQueryHint("Cari siswa..");

        searchMenuItem.getIcon().setVisible(false, false);
        super.onCreateOptionsMenu(menu, inflaterr);
    }

}
