package com.example.dogsretrofit;

import com.example.dogsretrofit.model.Dogs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIService {

    @GET
    Call<Dogs> getDogsByBreeds(@Url String url);

}
