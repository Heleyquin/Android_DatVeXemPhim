package com.example.datvexemphim.Services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.datvexemphim.API.KhachHangAPIService;
import com.example.datvexemphim.API.PhimAPIService;
import com.example.datvexemphim.Activity.MainActivity;
import com.example.datvexemphim.Adapter.MoviesAdapter;
import com.example.datvexemphim.Model.Phim;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhimService {
    public static void getAllPhim(List<Phim> phims, MoviesAdapter moviesAdapter) {
        PhimAPIService.service.getAllPhim().enqueue(new Callback<List<Phim>>() {

            @Override
            public void onResponse(Call<List<Phim>> call, Response<List<Phim>> response) {
                String prefixAnh = "https://media.themoviedb.org/t/p/w440_and_h660_face";
                response.body().forEach(phim -> {
                    phim.setAnh(prefixAnh+phim.getAnh());
                    phims.add(phim);
                });
                moviesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Phim>> call, Throwable t) {

            }
        });
    }

    public static void register(Map<String, String> data, Context context) {
        KhachHangAPIService.service.addKhachHang(data).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, MainActivity.class));
                }
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(context, "Thêm tài khoản thất bại", Toast.LENGTH_SHORT).show();
                System.out.println(t);
            }
        });
    }
}
