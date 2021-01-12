package com.example.newsaggregator.model.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModel {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalResults")
    @Expose
    private int totalResults;

    @SerializedName("articles")
    @Expose
    private List<Articles> articles;
}
