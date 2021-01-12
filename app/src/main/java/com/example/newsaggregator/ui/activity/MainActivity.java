package com.example.newsaggregator.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.newsaggregator.R;
import com.example.newsaggregator.model.model.NewsModel;
import com.example.newsaggregator.model.service.Service;
import com.example.newsaggregator.ui.fragments.OnNavigationListener;
import com.example.newsaggregator.ui.fragments.flowFragments.MainFlowFragment;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements OnNavigationListener {

    //public final static String API_KEY = "4efaaafdb72d4925a544b61efe8bf931";

    private FragmentTransaction fTrance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onNavigate(new MainFlowFragment(), false);
    }

    @Override
    public void onNavigate(Fragment fragment, boolean isAddToBackStage) {
        fTrance = getSupportFragmentManager().beginTransaction();
        fTrance.replace(R.id.content, fragment);
        if (isAddToBackStage){
            fTrance.addToBackStack(null);
        }
        fTrance.commit();
    }
}