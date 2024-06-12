package com.example.datvexemphim.Services;

import android.content.Intent;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.API.VeAPIService;
import com.example.datvexemphim.Model.Ve;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VeService {
    public static void getAllVe(List<Ve> ves, RecyclerView.Adapter adapter) {
        VeAPIService.service.getAllVe().enqueue(new Callback<List<Ve>>() {

            @Override
            public void onResponse(Call<List<Ve>> call, Response<List<Ve>> response) {
                response.body().forEach(ve -> {
                    ves.add(ve);
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Ve>> call, Throwable t) {

            }
        });
    }
    public static void addVe(Map<String, Object> data) {
        VeAPIService.service.addVe(data).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}
