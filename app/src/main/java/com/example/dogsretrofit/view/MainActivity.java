package com.example.dogsretrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import com.example.dogsretrofit.APIService;
import com.example.dogsretrofit.DogAdapter;
import com.example.dogsretrofit.databinding.ActivityMainBinding;
import com.example.dogsretrofit.model.Dogs;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ActivityMainBinding binding;
    private DogAdapter adapter;
    private List<String> dogImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dogImages = new ArrayList<>();
        adapter = new DogAdapter(dogImages);

        binding.searchBar.setOnQueryTextListener(this);
        
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new DogAdapter(dogImages);
        binding.rvPerros.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPerros.setAdapter(adapter);
    }


    private Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breed/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    private void searchByName(String query){

        APIService apiService = getRetrofit().create(APIService.class);

        Call<Dogs> call = apiService.getDogsByBreeds(query+"/images");

        call.enqueue(new Callback<Dogs>() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse( Call<Dogs> call, Response<Dogs> response   ) {
                if (!response.isSuccessful()){
                    Log.d("Error","Error: "+ response.code());
                    return;
                }

                assert response.body() != null;
                List<String> images = response.body().getMessage();
                dogImages.clear();
                dogImages.addAll(images);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Dogs> call, Throwable t) {
                Log.d("Error","Fallo: "+ t.getMessage());
            }
        });

        hideKeyBoard();
    }


    private void hideKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.viewRoot.getWindowToken(),0);
    }
    

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!(query == null || query.isEmpty())){
            searchByName(query.toLowerCase());
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
}