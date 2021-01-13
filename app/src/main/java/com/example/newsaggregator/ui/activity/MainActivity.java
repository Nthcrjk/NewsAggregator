package com.example.newsaggregator.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.example.newsaggregator.R;
import com.example.newsaggregator.ui.fragments.OnNavigationListener;
import com.example.newsaggregator.ui.fragments.flowFragments.NewsListFragment;

public class MainActivity extends AppCompatActivity implements OnNavigationListener {

    public final static String API_KEY = "4efaaafdb72d4925a544b61efe8bf931";

    private FragmentTransaction fTrance;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();
        onNavigate(new NewsListFragment(), false);
    }

    private void setToolBar(){
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.abc_vector_test);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle("");
    }

    @Override
    public void onNavigate(Fragment fragment, boolean isAddToBackStage) {
        fTrance = getSupportFragmentManager().beginTransaction();
        fTrance.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fTrance.replace(R.id.content, fragment);
        if (isAddToBackStage){
            fTrance.addToBackStack(null);
        }
        fTrance.commit();
    }
}