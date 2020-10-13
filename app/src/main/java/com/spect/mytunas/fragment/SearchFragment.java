package com.spect.mytunas.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spect.mytunas.R;
import com.spect.mytunas.adapter.RequestAdapterRecyclerView;
import com.spect.mytunas.adapter.UserSearchAdapter;
import com.spect.mytunas.models.Requests;
import com.spect.mytunas.models.Siswa;
import com.spect.mytunas.models.User;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private Context mContext;
    private ArrayList<Siswa> listUser;
    private UserSearchAdapter userSearchAdapter;
    public DatabaseReference mDatabaseRef;
    private RecyclerView rvUser;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        rvUser = v.findViewById(R.id.rvUserSearch);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvUser.setLayoutManager(mLayoutManager);
        searchUser("");
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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchUser(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);
        super.onCreateOptionsMenu(menu, inflaterr);
    }

    public void searchUser(String keyword){
        if (keyword.length() > 0) {
            mDatabaseRef = FirebaseDatabase.getInstance().getReference();
            mDatabaseRef.child("siswa").orderByChild("nama_lengkap").startAt(keyword)
                    .endAt(keyword+"\uf8ff").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    listUser = new ArrayList<>();
                    for (DataSnapshot noteDataSnapshot : snapshot.getChildren()) {

                        Siswa users = noteDataSnapshot.getValue(Siswa.class);
                        listUser.add(users);
                    }
                    userSearchAdapter = new UserSearchAdapter(listUser, getActivity());
                    rvUser.setAdapter(userSearchAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            mDatabaseRef = FirebaseDatabase.getInstance().getReference();
            mDatabaseRef.child("siswa").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    listUser = new ArrayList<>();
                    for (DataSnapshot noteDataSnapshot : snapshot.getChildren()) {
                        Siswa users = noteDataSnapshot.getValue(Siswa.class);
                        listUser.add(users);
                    }
                    userSearchAdapter = new UserSearchAdapter(listUser, getActivity());
                    rvUser.setAdapter(userSearchAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}
