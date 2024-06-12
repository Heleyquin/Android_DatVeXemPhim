package com.example.datvexemphim.Services;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.API.SuatChieuAPIService;
import com.example.datvexemphim.Adapter.PhimFragment.MoviesAdapter;
import com.example.datvexemphim.Adapter.PhimFragment.RapAdapter;
import com.example.datvexemphim.Model.SuatChieu;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuatChieuService {
    public static void getAllSuatChieu(List<SuatChieu> suatChieus, RecyclerView.Adapter adapter) {
        SuatChieuAPIService.service.getAllSuatChieu().enqueue(new Callback<List<SuatChieu>>() {

            @Override
            public void onResponse(Call<List<SuatChieu>> call, Response<List<SuatChieu>> response) {
                response.body().forEach(suatChieu -> {
                    suatChieus.add(suatChieu);
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SuatChieu>> call, Throwable t) {

            }
        });
    }

    public static void getSuatChieuByPhim(List<SuatChieu> suatChieus,int idPhim, RecyclerView.Adapter rapAdapter) {
        SuatChieuAPIService.service.getSuatChieuByPhim(idPhim).enqueue(new Callback<List<SuatChieu>>() {

            @Override
            public void onResponse(Call<List<SuatChieu>> call, Response<List<SuatChieu>> response) {
                response.body().forEach(suatChieu -> {
                    suatChieus.add(suatChieu);
                });
                rapAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SuatChieu>> call, Throwable t) {

            }
        });
    }
}
