package com.comp4905.jobarc.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.comp4905.jobarc.Adapters.HomeAdapter;
import com.comp4905.jobarc.Models.Job;
import com.comp4905.jobarc.Models.ModelHome;
import com.comp4905.jobarc.R;
import com.comp4905.jobarc.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private static final String TAG = "Search Fragment: ";
    private HomeAdapter adapterSearch;
    private LinearLayoutManager manager;
    private List<Job> jobList = new ArrayList<>();
    private Long id;
    private String username;
    private String accountType;

    EditText searchBar;
    Button searchButton;

    public SearchFragment(Long id, String username, String accountType) {
        // Required empty public constructor
        super();
        this.id = id;
        this.username = username;
        this.accountType = accountType;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchBar = view.findViewById(R.id.searchBar);
        searchButton = view.findViewById(R.id.searchButton);

        RecyclerView rv = view.findViewById(R.id.recyclerSearch);
        manager = new LinearLayoutManager(getContext());
        adapterSearch = new HomeAdapter(getContext(), jobList, id, username, accountType);
        rv.setAdapter(adapterSearch);
        rv.setLayoutManager(manager);

        view.findViewById(R.id.searchButton).setOnClickListener(v -> {
            if(searchBar.getText().toString().isEmpty()){
                searchBar.setError("Enter keyword to search Jobs");
                searchBar.requestFocus();
            } else {
                getSearchData(searchBar.getText().toString());
            }
        });

        return view;
    }

    private void getSearchData(String keyword){
        jobList.clear();
        adapterSearch.notifyDataSetChanged();

        Call<ModelHome> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getSearchJobs(keyword);

        call.enqueue(new Callback<ModelHome>() {
            @Override
            public void onResponse(Call<ModelHome> call, Response<ModelHome> response) {

                ModelHome mh = response.body();
                if(mh.getItems().isEmpty()){
                    Toast.makeText(getContext(), "no search results found", Toast.LENGTH_LONG).show();
                } else {
                    jobList.addAll(mh.getItems());
                    adapterSearch.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelHome> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }




}
