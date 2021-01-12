package com.example.newsaggregator.ui.fragments.flowFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.newsaggregator.R;
import com.example.newsaggregator.adapter.adapter.HeaderNewsAdapter;
import com.example.newsaggregator.model.model.Articles;
import com.example.newsaggregator.model.model.NewsModel;
import com.example.newsaggregator.presentation.presenter.NewsListPresenter;
import com.example.newsaggregator.presentation.view.NewsListView;
import com.example.newsaggregator.ui.activity.MainActivity;
import com.example.newsaggregator.ui.fragments.OnNavigationListener;
import com.example.newsaggregator.ui.fragments.fragments.DetailNewsFragment;

import java.util.List;

import moxy.MvpAppCompatFragment;
import moxy.MvpView;
import moxy.presenter.InjectPresenter;

public class MainFlowFragment extends MvpAppCompatFragment implements NewsListView {

    @InjectPresenter
    NewsListPresenter presenter;

    private OnNavigationListener onNavigationListener;

    private RecyclerView newsList;
    private HeaderNewsAdapter adapter;

    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        //ПЕРЕДЕЛАТЬ!
        this.context = context;

        onNavigationListener = (MainActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_flow, container, false);
        newsList = (RecyclerView) view.findViewById(R.id.rv_news_list);
        return view;
    }

    @Override
    public void setAdapter(List<Articles> states) {

        adapter = new HeaderNewsAdapter(context, states);
        newsList.setAdapter(adapter);
    }
}