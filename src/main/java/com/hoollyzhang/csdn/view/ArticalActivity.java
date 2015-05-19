package com.hoollyzhang.csdn.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.hoollyzhang.csdn.R;
import com.hoollyzhang.csdn.adapter.ArticleAdataper;
import com.hoollyzhang.csdn.listener.ArticalListener;
import com.hoollyzhang.csdn.manger.AricalManger;
import com.hoollyzhang.csdn.mode.Article;
import com.hoollyzhang.csdn.response.ArticalResponse;
import com.hoollyzhang.csdn.utils.UrlUtis;
import com.hoollyzhang.hlib.view.XListView;

import java.util.ArrayList;

/**
 * Created by brzhang on 15/5/18.
 */
public class ArticalActivity extends Activity implements ArticalListener,XListView.IXListViewListener {
    public static final String BLOG_SRC = "blog_src";


    private XListView       listview;
    private ArticleAdataper articalAdataper;
    private int pageIndex = 1;
    private String blogUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csdn_articalactivity);
        listview = (XListView) findViewById(R.id.csdn_artical_activity_artical_list);
        listview.setPullLoadEnable(true);
        listview.setPullRefreshEnable(true);
        listview.setXListViewListener(this);
        blogUrl = UrlUtis.generateCsdnBlogerUrl(getIntent().getStringExtra(BLOG_SRC));
        initData();
    }

    private void initData() {
        articalAdataper = new ArticleAdataper(this);
        listview.setAdapter(articalAdataper);
        AricalManger.requestAtricalList(this,this,blogUrl, pageIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAsyncCallBack(ArticalResponse ret) {
        listview.stopLoadMore();
        listview.stopRefresh();
        if (ret!=null && ret.getArticles()!=null){
            if (pageIndex==1){
                articalAdataper.setListArticle(ret.getArticles());
                articalAdataper.notifyDataSetInvalidated();
            }else{
                ArrayList<Article> articleArrayList = articalAdataper.getListArticle();
                for (Article article:ret.getArticles()){
                    articleArrayList.add(article);
                    articalAdataper.setListArticle(articleArrayList);
                    articalAdataper.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onProgress(Integer progres) {

    }

    @Override
    public void onRefresh() {
        pageIndex=1;
        AricalManger.requestAtricalList(this,this,blogUrl, pageIndex);
    }

    @Override
    public void onLoadMore() {
        AricalManger.requestAtricalList(this,this,blogUrl, ++pageIndex);
    }
}
