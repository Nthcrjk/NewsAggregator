package com.example.newsaggregator.model.api;

import com.example.newsaggregator.model.model.NewsModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines?country=ru")
    Observable<NewsModel> getNews(@Query("apiKey") String apiKey);
}
