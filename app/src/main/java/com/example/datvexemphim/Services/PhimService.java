package com.example.datvexemphim.Services;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.API.PhimAPIService;
import com.example.datvexemphim.Model.Phim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhimService {
    public static void getAllPhim(List<Phim> phims, RecyclerView.Adapter adapter, List<Phim> dataFul) {
        PhimAPIService.service.getAllPhim().enqueue(new Callback<List<Phim>>() {

            @Override
            public void onResponse(Call<List<Phim>> call, Response<List<Phim>> response) {
                String prefixAnh = "https://media.themoviedb.org/t/p/w440_and_h660_face";
                response.body().forEach(phim -> {
                    phim.setAnh(prefixAnh+phim.getAnh());
                    phim.setNamPhatHanh(phim.getNamPhatHanh().substring(0,9));
                    phims.add(phim);
                });
                dataFul.addAll(phims);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Phim>> call, Throwable t) {

            }
        });
    }
}
