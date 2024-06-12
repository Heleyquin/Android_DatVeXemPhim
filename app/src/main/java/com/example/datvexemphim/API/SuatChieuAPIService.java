package com.example.datvexemphim.API;

import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.SuatChieu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SuatChieuAPIService extends APIService {
    SuatChieuAPIService service = BUILDER.create(SuatChieuAPIService.class);

    @GET("api/suatChieu")
    Call<List<SuatChieu>> getAllSuatChieu();
    @GET("api/suatChieu/phim/{movieID}")
    Call<List<SuatChieu>> getSuatChieuByPhim(@Path("movieID") int movieID);
}
