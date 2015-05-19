package com.hoollyzhang.csdn.work;

import android.text.TextUtils;
import android.util.Log;

import com.hoollyzhang.csdn.mode.Article;
import com.hoollyzhang.csdn.response.ArticalResponse;
import com.hoollyzhang.csdn.utils.Constans;

import org.apache.http.HttpStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by brzhang on 15/5/19.
 */
public class AtricalListDownLoadTask {
    private static final String TAG = "AtricalListDownLoadTask";

    public static ArticalResponse getAtricalList(String urlString) {
        if (TextUtils.isEmpty(urlString)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            if (conn.getResponseCode() == HttpStatus.SC_OK) {
                InputStream is = conn.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    sb.append(new String(buf, 0, len, "UTF-8"));
                }
                is.close();
            } else {
                Log.e(TAG, "访问网络失败！");
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        Document document = Jsoup.parse(sb.toString());
        Elements units = document.getElementsByClass("list_item");
        if (units != null && units.size() > 0) {
            ArrayList<Article> articles = new ArrayList<>();

            for (int i = 0; i < units.size(); i++) {
                Article article = new Article();
                Element element = units.get(i);
                Elements titles = element.getElementsByClass("link_title");
                Log.e(TAG,"size titles is "+titles.size());
                if (titles != null && titles.size() > 0) {
                    article.setTitle(titles.get(0).child(0).text());
                    article.setUrl(Constans.BASEURL + titles.get(0).child(0).attr("href"));
                }
                Elements contentDes = element.getElementsByClass("article_description");
                Log.e(TAG,"size contentDes is "+contentDes.size());
                if (contentDes != null && contentDes.size() > 0)
                    article.setContent(contentDes.get(0).text());
                articles.add(article);
            }
            ArticalResponse articalResponse = new ArticalResponse();
            articalResponse.setArticles(articles);
            return articalResponse;
        }
        return null;
    }
}
