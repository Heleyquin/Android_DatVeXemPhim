package com.example.datvexemphim.Services;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.API.HoaDonAPIService;
import com.example.datvexemphim.Model.HoaDon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoaDonService {
    public static void getAllHoaDon(List<HoaDon> hoaDons, RecyclerView.Adapter adapter) {
        HoaDonAPIService.service.getAllHoaDon().enqueue(new Callback<List<HoaDon>>() {

            @Override
            public void onResponse(Call<List<HoaDon>> call, Response<List<HoaDon>> response) {
                response.body().forEach(hoaDon -> {
                    hoaDons.add(hoaDon);
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HoaDon>> call, Throwable t) {

            }
        });
    }
}
