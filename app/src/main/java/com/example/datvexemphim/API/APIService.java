package com.example.datvexemphim.API;

import com.example.datvexemphim.API.interceptor.OkHttpProvider;
import com.google.gson.GsonBuilder;
import com.example.datvexemphim.API.interceptor.CustomInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface APIService {
    Retrofit BUILDER = new Retrofit.Builder()
//            .baseUrl("http://10.0.2.2:8080/") //nếu chạy máy ảo
            .baseUrl("http://192.168.1.7:8080/") //địa chỉ ip của máy tính
            .client(new OkHttpClient.Builder().addInterceptor(new CustomInterceptor()).build())
            .client(OkHttpProvider.getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd").create()))
            .build();
}
