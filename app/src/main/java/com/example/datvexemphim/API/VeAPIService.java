package com.example.datvexemphim.API;

import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.Model.Ve;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VeAPIService extends APIService {
    VeAPIService service = BUILDER.create(VeAPIService.class);

    @GET("api/ve")
    Call<List<Ve>> getAllVe();
    @POST("api/ve")
    Call<Void> addVe(@Body Map<String, Object> data);

}
