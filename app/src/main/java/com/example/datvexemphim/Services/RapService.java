package com.example.datvexemphim.Services;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.API.RapAPIService;
import com.example.datvexemphim.Model.Rap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RapService {
    public static void getAllRap(List<Rap> raps, RecyclerView.Adapter adapter, List<Rap> dsRapFIl) {
        RapAPIService.service.getAllRap().enqueue(new Callback<List<Rap>>() {

            @Override
            public void onResponse(Call<List<Rap>> call, Response<List<Rap>> response) {
                response.body().forEach(rap -> {
                    raps.add(rap);
                });
                dsRapFIl.addAll(raps);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Rap>> call, Throwable t) {

            }
        });
    }
}
