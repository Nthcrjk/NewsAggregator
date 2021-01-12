package com.example.newsaggregator.model.service;

import com.example.newsaggregator.model.api.NewsApi;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    public static final String BASE_URL = "https://newsapi.org/v2/";

    private Retrofit retrofit;
    private NewsApi api;

    public Service(){
        retrofit = createRetrofit();
    }

    public NewsApi getApi() {
        api = retrofit.create(NewsApi.class);
        return api;
    }

    private Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

}
