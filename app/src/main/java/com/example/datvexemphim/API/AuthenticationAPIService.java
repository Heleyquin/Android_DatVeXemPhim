package com.example.datvexemphim.API;

import com.google.gson.JsonElement;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticationAPIService extends APIService {
    AuthenticationAPIService service = BUILDER.create(AuthenticationAPIService.class);

    @POST("api/auth/account/login")
    Call<JsonElement> login(@Body Map<String, String> account);

    @POST("api/auth/account/register")
    Call<Void> register(@Body Map<String, String> data);
}
