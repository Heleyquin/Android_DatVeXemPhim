package com.example.datvexemphim.Services;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.datvexemphim.API.AccountAPIService;
import com.example.datvexemphim.API.KhachHangAPIService;
import com.example.datvexemphim.Activity.SettingFragment.User;
import com.example.datvexemphim.Model.Account;
import com.example.datvexemphim.Model.KhachHang;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhachHangService {
    public static KhachHang getKhachHang(String username){
        final KhachHang[] khachHangs = {new KhachHang()};
        KhachHangAPIService.service.getKhachHangByUsername(username).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonObject responseData = Objects.requireNonNull(response.body()).getAsJsonObject();
                boolean gioiTinh = true;
                if(responseData.get("gioiTinh").getAsString().equals("nu"))
                    gioiTinh = false;
                KhachHang khachHang = new KhachHang(
                        responseData.get("id_kh").getAsInt(),
                        responseData.get("ten").getAsString(),
                        responseData.get("cccd").getAsString(),
                        responseData.get("diaChi").getAsString(),
                        gioiTinh);
                khachHangs[0] = khachHang;
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
        return khachHangs[0];
    }
    public static void updateKhachHang(Map<String, Object> data, Context context, String username){
        KhachHangAPIService.service.getKhachHangByUsername(username).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonObject responseData = Objects.requireNonNull(response.body()).getAsJsonObject();
                data.put("id_kh", responseData.get("id_kh").getAsInt());
                AccountAPIService.service.getAccountByUsername(username).enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        Account tk = response.body();
                        data.put("account", tk);
                        KhachHangAPIService.service.updateKhachHang(data).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });

    }

    public static void getKhachHangSetting(Context context, String username, View v) {
        KhachHangAPIService.service.getKhachHangByUsername(username).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonObject responseData = response.body().getAsJsonObject();
                boolean gioiTinh = true;
                if(responseData.get("gioiTinh").getAsString().equals("nu"))
                    gioiTinh = false;
                KhachHang khachHang = new KhachHang(
                        responseData.get("id_kh").getAsInt(),
                        responseData.get("ten").getAsString(),
                        responseData.get("cccd").getAsString(),
                        responseData.get("diaChi").getAsString(),
                        gioiTinh);

                Intent intent = new Intent(context, User.class);
                intent.putExtra("user", khachHang);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }
}
