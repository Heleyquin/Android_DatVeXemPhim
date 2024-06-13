package com.example.datvexemphim.Services;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.API.AccountAPIService;
import com.example.datvexemphim.API.PhimAPIService;
import com.example.datvexemphim.Activity.ForAll.Home;
import com.example.datvexemphim.Activity.ForAll.MainActivity;
import com.example.datvexemphim.Activity.SettingFragment.Detail_Account;
import com.example.datvexemphim.Model.Account;
import com.example.datvexemphim.Model.Phim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountService {
    public static Account getAccount(String username){
        final Account[] account = {new Account()};
        AccountAPIService.service.getAccountByUsername(username).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                account[0] = response.body();
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
        return account[0];
    }
    public static void changePassword(String username, String password, Context context) {
        System.out.println(username);
        AccountAPIService.service.getAccountByUsername(username).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Account account = response.body();
                account.setMk(password);
                AccountAPIService.service.changeInfo(account).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
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

    public static void getAccountSetting(Context context, String username) {
            AccountAPIService.service.getAccountByUsername(username).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {


                    Intent intent = new Intent(context, Detail_Account.class);

                    intent.putExtra("tk", response.body());

                    context.startActivity(intent);
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {

                }
            });
        }
}
