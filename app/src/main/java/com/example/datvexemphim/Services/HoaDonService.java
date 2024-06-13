package com.example.datvexemphim.Services;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.API.HoaDonAPIService;
import com.example.datvexemphim.Activity.SettingFragment.History;
import com.example.datvexemphim.Model.HoaDon;

import java.io.Serializable;
import java.util.ArrayList;
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
                if (adapter != null)
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HoaDon>> call, Throwable t) {

            }
        });
    }

    public static void getAllHoaDonSetting(Context context, String username) {
        List<HoaDon> hdForUser = new ArrayList<>();
        List<HoaDon> dsHoaDon = new ArrayList<>();
        HoaDonAPIService.service.getAllHoaDon().enqueue(new Callback<List<HoaDon>>() {

            @Override
            public void onResponse(Call<List<HoaDon>> call, Response<List<HoaDon>> response) {
                response.body().forEach(hoaDon -> {
                    dsHoaDon.add(hoaDon);
                });
                for (HoaDon hd : dsHoaDon) {
                    if (hd.getId_kh().getId_acc().getTk().equals(username)) {
                        hdForUser.add(hd);
                    }
                }
                Intent intent = new Intent(context, History.class);

                intent.putExtra("hd", (Serializable) hdForUser);

                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<HoaDon>> call, Throwable t) {

            }
        });
    }
}
