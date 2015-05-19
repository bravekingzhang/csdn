package com.hoollyzhang.csdn.logic;

import android.content.Context;
import android.os.AsyncTask;

import com.hoollyzhang.csdn.listener.BlogerListener;
import com.hoollyzhang.csdn.mode.Bloger;
import com.hoollyzhang.csdn.response.BlogerResponse;
import com.hoollyzhang.csdn.work.BlogerLoad;

/**
 * Created by brzhang on 15/5/19.
 */
public class BlogerLogic {
    private Context        context;
    private BlogerListener listener;

    public BlogerLogic(Context context, BlogerListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void getBlogerList() {
        AsyncTask blogerTask = new AsyncTask<Object, Integer, BlogerResponse>() {
            @Override
            protected BlogerResponse doInBackground(Object... params) {
                BlogerResponse response = BlogerLoad.getBlogerlList(context);
                return response;
            }

            protected void onProgressUpdate(Integer... progress) {
                listener.onProgress(progress[0]);
            }

            protected void onPostExecute(BlogerResponse result) {
                if (listener != null) {
                    listener.onAsyncCallBack(result);
                }
            }
        };
        blogerTask.execute();
    }

    public void insertBloger(final Bloger bloger) {
        AsyncTask blogerTask = new AsyncTask<Object, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Object... params) {
                int response = BlogerLoad.insertBloger(context, bloger);
                return response;
            }

            protected void onProgressUpdate(Integer... progress) {
                listener.onProgress(progress[0]);
            }

            protected void onPostExecute(Integer result) {
                if (listener != null) {
                    listener.onInsert();
                }
            }
        };
        blogerTask.execute();
    }
}
