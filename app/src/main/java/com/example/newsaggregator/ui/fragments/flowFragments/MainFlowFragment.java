package com.example.newsaggregator.ui.fragments.flowFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.newsaggregator.R;
import com.example.newsaggregator.ui.activity.MainActivity;
import com.example.newsaggregator.ui.fragments.OnNavigationListener;
import com.example.newsaggregator.ui.fragments.fragments.DetailNewsFragment;

public class MainFlowFragment extends Fragment {

    private OnNavigationListener onNavigationListener;

    @Override
    public void onAttach(@NonNull Context context) {
        onNavigationListener = (MainActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_flow, container, false);
        Button button = (Button) view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavigationListener.onNavigate(new DetailNewsFragment(), true);
            }
        });
        return view;
    }
}