package com.example.datvexemphim.Services;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.API.RapAPIService;
import com.example.datvexemphim.Activity.PhimFragment.Movie_Detail;
import com.example.datvexemphim.Model.Rap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RapService {
    public static void getAllRap(List<Rap> raps, RecyclerView.Adapter adapter) {
        RapAPIService.service.getAllRap().enqueue(new Callback<List<Rap>>() {

            @Override
            public void onResponse(Call<List<Rap>> call, Response<List<Rap>> response) {
                response.body().forEach(rap -> {
                    raps.add(rap);
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Rap>> call, Throwable t) {

            }
        });
    }
}
