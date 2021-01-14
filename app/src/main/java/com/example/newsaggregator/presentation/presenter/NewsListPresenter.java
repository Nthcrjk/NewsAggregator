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


    private List<Articles> lastLoaded;
    private NewsApi api;

    private boolean isNotSetAdapter = true;
    private boolean firstLoad = true;
    private int countOfPages = 0;
    private int pagesOpened = 0;

    private final int POSTS_IN_PAGE = 10;
    private final String LOG_TAG = NewsListPresenter.class.getName();

    public NewsListPresenter(){
        Log.e("gaf", "Constructor");
        api = new Service().getApi();
        lastLoaded = new ArrayList<>();

        loadNextNewsPage();
    }

    public void loadNextNewsPage(){
            Log.e("gaf", "открыто: " + Integer.toString(pagesOpened));
            pagesOpened++;
            Observable<NewsModel> observable = api.getNews(pagesOpened, MainActivity.API_KEY);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NewsModel>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull NewsModel newsModel) {
                            lastLoaded = newsModel.getArticles();

                            int totalRes = newsModel.getTotalResults();
                            countOfPages = totalRes / POSTS_IN_PAGE;
                            if ((totalRes / POSTS_IN_PAGE) % POSTS_IN_PAGE != 0)
                                countOfPages++;
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(LOG_TAG, e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            if (isNotSetAdapter) {
                                getViewState().setAdapter(lastLoaded);
                                isNotSetAdapter = false;
                                firstLoad = false;
                            } else {
                                getViewState().updateAdapterData(lastLoaded);
                            }
                        }
                    });

    }
}
