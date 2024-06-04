package com.example.datvexemphim.API;

import com.google.gson.JsonElement;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface KhachHangAPIService extends APIService {
    KhachHangAPIService service = BUILDER.create(KhachHangAPIService.class);

    @POST("api/auth/khachHang")
    Call<Void> addKhachHang(@Body Map<String, String> khachHang);

}
