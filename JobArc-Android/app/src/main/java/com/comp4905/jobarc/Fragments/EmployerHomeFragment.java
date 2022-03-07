package com.comp4905.jobarc.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.comp4905.jobarc.Activity.JobseekerDashboardActivity;
import com.comp4905.jobarc.Adapters.HomeAdapter;
import com.comp4905.jobarc.Models.Job;
import com.comp4905.jobarc.Models.ModelHome;
import com.comp4905.jobarc.R;
import com.comp4905.jobarc.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployerHomeFragment extends Fragment {

    private static final String TAG = "Home Fragment: ";
    private HomeAdapter adapterHome;
    private LinearLayoutManager manager;
    private List<Job> jobList = new ArrayList<>();
    private Long id;

    public EmployerHomeFragment(Long id) {
        // Required empty public constructor
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rv = view.findViewById(R.id.recyclerView);
        adapterHome = new HomeAdapter(getContext(), jobList);
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapterHome);
        rv.setLayoutManager(manager);


        if(jobList.size() == 0){
            getDashboardData(id);
        }
        return view;
    }



    private void getDashboardData(long id){
        Call<ModelHome> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getDashboard(id);

        call.enqueue(new Callback<ModelHome>() {
            @Override
            public void onResponse(Call<ModelHome> call, Response<ModelHome> response) {
                if(response.errorBody() != null){
                    Log.w(TAG, "onResponse: " + response.errorBody());
                } else {
                    ModelHome mh = response.body();
                    jobList.addAll(mh.getItems());
                    adapterHome.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelHome> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
