package com.example.datvexemphim.Services;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.API.PhongAPIService;
import com.example.datvexemphim.API.SuatChieuAPIService;
import com.example.datvexemphim.Adapter.PhimFragment.MoviesAdapter;
import com.example.datvexemphim.Model.Phong;
import com.example.datvexemphim.Model.SuatChieu;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongService {
    public static void getAllPhong(List<Phong> phongs, RecyclerView.Adapter adapter) {
        PhongAPIService.service.getAllPhong().enqueue(new Callback<List<Phong>>() {

            @Override
            public void onResponse(Call<List<Phong>> call, Response<List<Phong>> response) {
                response.body().forEach(phong -> {
                    phongs.add(phong);
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Phong>> call, Throwable t) {

            }
        });
    }
}
