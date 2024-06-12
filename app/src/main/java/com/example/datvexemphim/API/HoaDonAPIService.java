package com.example.datvexemphim.API;

import com.example.datvexemphim.Model.HoaDon;
import com.example.datvexemphim.Model.SuatChieu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HoaDonAPIService extends APIService {
    HoaDonAPIService service = BUILDER.create(HoaDonAPIService.class);

    @GET("api/hoaDon")
    Call<List<HoaDon>> getAllHoaDon();

}
