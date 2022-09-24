package com.example.dogsretrofit.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Dogs {
    @SerializedName("status") String status;
    @SerializedName("message") List<String> images;


    public String getStatus() {return status;}

    public List<String> getMessage() { return images; }

}
