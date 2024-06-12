package com.example.datvexemphim.API;

import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.SuatChieu;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GheAPIService extends APIService {
    GheAPIService service = BUILDER.create(GheAPIService.class);

    @GET("api/ghe")
    Call<List<Ghe>> getAllGhe();

}
