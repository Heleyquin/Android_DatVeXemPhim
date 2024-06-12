package com.example.datvexemphim.API;

import com.example.datvexemphim.Model.Phong;
import com.example.datvexemphim.Model.Rap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RapAPIService extends APIService {
    RapAPIService service = BUILDER.create(RapAPIService.class);

    @GET("api/rap")
    Call<List<Rap>> getAllRap();

}
