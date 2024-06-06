package com.example.datvexemphim.API;

import com.example.datvexemphim.Model.Phim;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PhimAPIService extends APIService {
    PhimAPIService service = BUILDER.create(PhimAPIService.class);

    @GET("api/phim")
    Call<List<Phim>> getAllPhim();

}
