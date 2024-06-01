package com.example.datvexemphim.API;

import com.example.datvexemphim.Model.Account;
import com.google.gson.JsonElement;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountAPIService extends APIService {
    AccountAPIService service = BUILDER.create(AccountAPIService.class);

    @GET("api/auth/account/username/{username}")
    Call<Account> getAccountByUsername(@Path("username") String username);

}
