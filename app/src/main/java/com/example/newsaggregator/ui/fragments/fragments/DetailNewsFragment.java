package com.example.newsaggregator.ui.fragments.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsaggregator.R;
import com.example.newsaggregator.helpers.TextChanger;
import com.example.newsaggregator.model.model.Articles;
import com.squareup.picasso.Picasso;


public class DetailNewsFragment extends Fragment {

    private Articles articles;
    private ImageView detailItemImage;
    private Context context;
    private TextView detailItemTitle, detailItemPublished, detailItemDescription,
            detailItemAuthor, detailItemContent;


    public DetailNewsFragment(Articles articles){
        this.articles = articles;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_news, container, false);

        detailItemImage = (ImageView) view.findViewById(R.id.detail_item_image);
        detailItemTitle = (TextView) view.findViewById(R.id.detail_item_title);
        detailItemPublished = (TextView) view.findViewById(R.id.detail_item_published);
        detailItemDescription = (TextView) view.findViewById(R.id.detail_item_description);
        detailItemAuthor = (TextView) view.findViewById(R.id.detail_item_author);
        detailItemContent = (TextView) view.findViewById(R.id.detail_item_content);

        Picasso.with(context).load(articles.getUrlToImage()).into(detailItemImage);
        detailItemTitle.setText(articles.getTitle());
        detailItemPublished.setText(articles.getPublishedAt());
        detailItemDescription.setText(articles.getDescription());
        detailItemAuthor.setText(articles.getAuthor());
        detailItemContent.setText(articles.getContent());


        String link = "<a href=\""+ articles.getUrl() +"\"> в источнике</a>";
        detailItemContent.append(Html.fromHtml(link, null, null));
        detailItemContent.setLinksClickable(true);
        detailItemContent.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}