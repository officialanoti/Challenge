package com.example.android.challenge;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.challenge.Adapter.FinderAdapter;
import com.example.android.challenge.Models.Developer;
import com.example.android.challenge.Models.DeveloperResponse;
import com.example.android.challenge.rest.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Magaji on 8/30/2017.
 */

public class FinderActivity extends AppCompatActivity {

    private RecyclerView rRecyclerView;
    private FinderAdapter mAdapter = new FinderAdapter(new ArrayList<Developer>());

    public static final String BASE_URL = "https://api.github.com/";
    private ApiClient apiClient ;

    private TextView msgView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();




        msgView = (TextView) findViewById(R.id.display_message);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);



        apiClient = new ApiClient();

        rRecyclerView = (RecyclerView) findViewById(R.id.finder_list);
        rRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        rRecyclerView.setHasFixedSize(true);
        rRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        rRecyclerView.setAdapter(mAdapter);


        isLoading();
        if(activeNetwork !=null && activeNetwork.isConnectedOrConnecting()){
            loadDevelopers();
        }else {
            response_message("No Internet Connection");
        }




    }
    private void loadDevelopers () {
        isLoading();
        Call<DeveloperResponse> call = apiClient.getApiService().getDevelopers();
        call.enqueue(new Callback<DeveloperResponse>() {
            @Override
            public void onResponse(Call<DeveloperResponse> call, Response<DeveloperResponse> response) {
                List<Developer> developers = response.body().getItems();
                if(developers.isEmpty()) {
                    response_message("No Developers Found");
                } else {
                    default_mode();
                    mAdapter = new FinderAdapter(developers);
                    rRecyclerView.setAdapter(mAdapter);
                }

            }

            @Override
            public void onFailure(Call<DeveloperResponse> call, Throwable t) {
                response_message("Could Not Get List");
                Log.e(FinderActivity.class.getName(), "Error Getting Results");
            }
        });
    }
    public void isLoading () {
        progressBar.setVisibility(View.VISIBLE);
        msgView.setVisibility(View.INVISIBLE);
        rRecyclerView.setVisibility(View.INVISIBLE);
    }

    public void response_message (String message) {
        progressBar.setVisibility(View.INVISIBLE);
        msgView.setVisibility(View.VISIBLE);
        msgView.setText(message);
        rRecyclerView.setVisibility(View.VISIBLE);

    }
    public void default_mode () {
        progressBar.setVisibility(View.INVISIBLE);
        msgView.setVisibility(View.INVISIBLE);
        rRecyclerView.setVisibility(View.VISIBLE);
    }
}
