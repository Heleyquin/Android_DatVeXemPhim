package com.example.datvexemphim.API;

import com.example.datvexemphim.Model.Account;
import com.example.datvexemphim.Model.KhachHang;
import com.google.gson.JsonElement;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface KhachHangAPIService extends APIService {
    KhachHangAPIService service = BUILDER.create(KhachHangAPIService.class);

    @POST("api/auth/khachHang")
    Call<Void> addKhachHang(@Body Map<String, String> khachHang);

    @PUT("api/auth/khachHang")
    Call<Void> updateKhachHang(@Body Map<String, Object> khachHang);

    @GET("api/auth/khachHang/username/{username}")
    Call<JsonElement> getKhachHangByUsername(@Path("username") String username);

}
