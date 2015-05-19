package com.hoollyzhang.csdn.response;

import com.hoollyzhang.csdn.mode.Article;

import java.util.ArrayList;

/**
 * Created by brzhang on 15/5/18.
 */
public class ArticalResponse {
    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    ArrayList<Article> articles;
}
