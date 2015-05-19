package com.hoollyzhang.csdn.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoollyzhang.csdn.R;
import com.hoollyzhang.csdn.mode.Article;

import java.util.ArrayList;

/**
 * Created by brzhang on 15/5/18.
 */
public class ArticleAdataper extends BaseAdapter {

    private ArrayList<Article> listArticle;
    private Activity           mActivity;

    public ArticleAdataper(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        if (listArticle != null) {
            return listArticle.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (listArticle != null) {
            return listArticle.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mActivity.getLayoutInflater().inflate(R.layout.csdn_artical_list_item, null);
            viewHolder.content = (LinearLayout) convertView.findViewById(R.id.csdn_article_list_item_layout);
            viewHolder.title = (TextView) convertView.findViewById(R.id.csdn_article_list_item_title);
            viewHolder.contentDes = (TextView) convertView.findViewById(R.id.csdn_article_list_item_contentdes);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        addOnclickListener(viewHolder, position);
        fillData(viewHolder, position);
        return convertView;
    }

    private void fillData(ViewHolder viewHolder, int position) {
        Article article = listArticle.get(position);
        if (article == null) {
            return;
        }
        viewHolder.title.setText(article.getTitle());
        viewHolder.contentDes.setText(article.getContent());
    }

    private void addOnclickListener(ViewHolder viewHolder, int position) {
        final Article article = listArticle.get(position);
        if (article == null) {
            return;
        }
        viewHolder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(article.getUrl());
                mActivity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
    }


    public void setListArticle(ArrayList<Article> listArticle) {
        this.listArticle = listArticle;
    }

    public ArrayList<Article> getListArticle() {
        return listArticle;
    }

    class ViewHolder {
        LinearLayout content;
        TextView title;
        TextView contentDes;
    }
}
