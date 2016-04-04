package com.example.ruby.loltellme.utils.retrofit;

import com.example.ruby.loltellme.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import timber.log.Timber;

public class RequestManager {

    private static EverlanceServices defaultRequestManager;
    private static EverlanceServices loginRequestManager;

    private RequestManager() {
    }

    public static EverlanceServices getDefault() {
        loginRequestManager = null;
        if (defaultRequestManager == null) {
            defaultRequestManager = generateRetrofit().create(EverlanceServices.class);
        }
        return defaultRequestManager;
    }

    public static EverlanceServices getSigning() {
        defaultRequestManager = null;
        if (loginRequestManager == null) {
            loginRequestManager = generateRetrofit().create(EverlanceServices.class);
        }
        return loginRequestManager;
    }

    private static Retrofit generateRetrofit() {
        OkHttpClient client = getOkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                Request.Builder newRequest = chain.request().newBuilder();
                newRequest.addHeader("User-Agent", "Riot-Games-Developer-Portal");
                newRequest.addHeader("Accept-Language", "en-US");
                newRequest.addHeader("Accept-Charset", "ISO-8859-1,utf-8");
                newRequest.addHeader("Origin", "https://developer.riotgames.com");
                return chain.proceed(newRequest.build());
            }
        });
        Timber.d("BaseAPIURL %s", Constants.BASE_API_URL);
        Timber.d("client parameters %s", Constants.BASE_API_URL);

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(20, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(20, TimeUnit.SECONDS);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient.interceptors().add(interceptor);
        return okHttpClient;
    }
}
