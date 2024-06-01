package com.example.datvexemphim.Services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.datvexemphim.API.AccountAPIService;
import com.example.datvexemphim.API.KhachHangAPIService;
import com.example.datvexemphim.Activity.Home;
import com.example.datvexemphim.Activity.MainActivity;
import com.example.datvexemphim.Activity.SignUp;
import com.example.datvexemphim.Model.Account;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.example.datvexemphim.API.AuthenticationAPIService;
import com.example.datvexemphim.API.interceptor.TokenStorage;

import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationService {
    public static void login(Map<String, String> data, Context context) {
        AuthenticationAPIService.service.login(data).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                try {
                    // GET JSON RESPONSE.
                    JsonObject responseData = Objects.requireNonNull(response.body()).getAsJsonObject();
                    String accessToken = responseData.get("accessToken").getAsString();
                    TokenStorage.setAccessToken(accessToken);
                    Intent intent = new Intent(context, Home.class);
                    context.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(context, "Thông tin đăng nhập không chính xác!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                Toast.makeText(context, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void register(Map<String, Object> dataAccount, Context context, Map<String, Object> dataKhachHang) {
        AuthenticationAPIService.service.register(dataAccount).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    AccountAPIService.service.getAccountByUsername(dataAccount.get("tk").toString()).enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(@NonNull Call<Account> call, @NonNull Response<Account> response) {
                            dataKhachHang.put("account", response.body());
                            KhachHangAPIService.service.addKhachHang(dataKhachHang).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(context, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                        context.startActivity(new Intent(context, MainActivity.class));
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Toast.makeText(context, "Thêm khách hàng thất bại", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }

                        @Override
                        public void onFailure(@NonNull Call<Account> call, @NonNull Throwable t) {
                            Toast.makeText(context, "Lấy tài khoản thất bại", Toast.LENGTH_SHORT).show();

                        }
                    });
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
