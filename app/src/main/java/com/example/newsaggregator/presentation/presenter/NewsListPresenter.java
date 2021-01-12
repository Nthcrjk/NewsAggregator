package com.example.newsaggregator.presentation.presenter;

import android.util.Log;

import com.example.newsaggregator.model.api.NewsApi;
import com.example.newsaggregator.model.model.Articles;
import com.example.newsaggregator.model.model.NewsModel;
import com.example.newsaggregator.model.service.Service;
import com.example.newsaggregator.presentation.view.NewsListView;
import com.example.newsaggregator.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class NewsListPresenter extends MvpPresenter<NewsListView> {

    private ArrayList<Articles> loadList;
    private NewsApi api;

    private final String LOG_TAG = NewsListPresenter.class.getName();

    public NewsListPresenter(){
        loadList = new ArrayList<>();
        api = new Service().getApi();

        Observable<NewsModel> observable = api.getNews(MainActivity.API_KEY);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewsModel newsModel) {
                        loadList.addAll(newsModel.getArticles());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(LOG_TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getViewState().setAdapter(loadList);
                    }
                });
    }
}
