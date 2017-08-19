package br.com.dfn.exemplohttp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultNewsApi {

    public String status;

    @SerializedName("articles")
    public List<Article> articleList;
}
