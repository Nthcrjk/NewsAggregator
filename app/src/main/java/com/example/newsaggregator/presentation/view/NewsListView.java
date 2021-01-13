package com.example.newsaggregator.presentation.view;

import com.example.newsaggregator.model.model.Articles;
import com.example.newsaggregator.model.model.NewsModel;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface NewsListView extends MvpView {
    void setAdapter(List<Articles> states);

    void updateAdapterData(List<Articles> newData);

}
