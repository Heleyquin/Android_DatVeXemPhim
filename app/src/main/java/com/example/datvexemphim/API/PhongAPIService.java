package com.example.datvexemphim.API;

import com.example.datvexemphim.Model.Phong;
import com.example.datvexemphim.Model.SuatChieu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhongAPIService extends APIService {
    PhongAPIService service = BUILDER.create(PhongAPIService.class);

    @GET("api/phong")
    Call<List<Phong>> getAllPhong();

}
