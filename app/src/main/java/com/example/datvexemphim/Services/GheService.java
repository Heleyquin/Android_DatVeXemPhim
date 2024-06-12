package com.example.datvexemphim.Services;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.API.GheAPIService;
import com.example.datvexemphim.API.SuatChieuAPIService;
import com.example.datvexemphim.Adapter.PhimFragment.MoviesAdapter;
import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.SuatChieu;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GheService {
    public static void getAllGhe(List<Ghe> ghes, RecyclerView.Adapter adapter) {
        GheAPIService.service.getAllGhe().enqueue(new Callback<List<Ghe>>() {

            @Override
            public void onResponse(Call<List<Ghe>> call, Response<List<Ghe>> response) {
                response.body().forEach(ghe -> {
                    ghes.add(ghe);
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Ghe>> call, Throwable t) {

            }
        });
    }
}
