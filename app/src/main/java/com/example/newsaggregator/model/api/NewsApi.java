package com.example.newsaggregator.model.api;

import com.example.newsaggregator.model.model.NewsModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("everything?q=bitcoin&pageSize=10")
    Observable<NewsModel> getNews(@Query("page") int page, @Query("apiKey") String apiKey);
}
