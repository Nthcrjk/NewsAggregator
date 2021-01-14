package com.example.newsaggregator.ui.fragments.flowFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.newsaggregator.R;
import com.example.newsaggregator.adapter.adapter.HeaderNewsAdapter;
import com.example.newsaggregator.model.model.Articles;
import com.example.newsaggregator.presentation.presenter.NewsListPresenter;
import com.example.newsaggregator.presentation.view.NewsListView;
import com.example.newsaggregator.ui.fragments.OnNavigationListener;

import java.util.List;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class NewsListFragment extends MvpAppCompatFragment implements NewsListView {

    @InjectPresenter
    NewsListPresenter presenter;

    private OnNavigationListener onNavigationListener;

    private RecyclerView newsList;
    private HeaderNewsAdapter adapter;
    private LinearLayoutManager manager;
    private Context context;

    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
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
        manager = new LinearLayoutManager(context);
        adapter = new HeaderNewsAdapter(context, states);
        newsList.setLayoutManager(manager);
        newsList.setAdapter(adapter);

        newsList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = manager.getChildCount();
                    totalItemCount = manager.getItemCount();
                    pastVisiblesItems = manager.findFirstVisibleItemPosition();
                    if (loading){
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount){
                            loading = false;
                            getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loading = true;
                                }
                            }, 1000);
                            presenter.loadNextNewsPage();
                            Log.e("gaf", "Scrolled");
                        }
                    }
                }
            }
        });
    }

    @Override
    public void updateAdapterData(List<Articles> newData) {
        adapter.getData().addAll(newData);
        adapter.notifyDataSetChanged();
    }

}