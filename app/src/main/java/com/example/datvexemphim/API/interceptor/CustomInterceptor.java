package com.example.datvexemphim.API.interceptor;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.datvexemphim.Activity.ForAll.ChonGhe;
import com.example.datvexemphim.Services.UserSessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CustomInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        // GET ORIGINAL REQUEST.
        Request originalRequest = chain.request();
        Request.Builder modifiedRequest = originalRequest.newBuilder()
                .method(originalRequest.method(), originalRequest.body());
        // ADD HEADER TO REQUEST.
        UserSessionManager userSessionManager = new UserSessionManager(MyApp.getContext());
        String accessToken = userSessionManager.getToken();

        if (accessToken != null) {
            modifiedRequest = modifiedRequest.addHeader("Authorization", "Bearer " + accessToken);
        }
        // CONTINUE TO EXECUTE REQUEST.
        return chain.proceed(modifiedRequest.build());
    }
}
