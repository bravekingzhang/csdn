package com.hoollyzhang.csdn.logic;

import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import com.hoollyzhang.csdn.listener.ArticalListener;
import com.hoollyzhang.csdn.response.ArticalResponse;
import com.hoollyzhang.csdn.work.AtricalListDownLoadTask;

/**
 * Created by brzhang on 15/5/18.
 */
public class ArticalLogic {
    private static final String TAG = "ArticalLogic";
    private Context         context;
    private ArticalListener listener;

    public ArticalLogic(Context context, ArticalListener listener) {
        this.context = context;
        this.listener = listener;
    }


    public void getAtricalList(String blogUrl, final int pageIndex) {
        AsyncTask articalTask = new AsyncTask<Object, Integer, ArticalResponse>() {
            @Override
            protected ArticalResponse doInBackground(Object... params) {
                int count = params.length;
                if (count == 0) {
                    return null;
                }
                Log.e(TAG,"start downloading from internet "+params[0].toString()+"/article/list/"+pageIndex);
                ArticalResponse response =  AtricalListDownLoadTask.getAtricalList(params[0].toString()+"/article/list/"+pageIndex);
                return response;
            }

            protected void onProgressUpdate(Integer... progress) {
                listener.onProgress(progress[0]);
            }

            protected void onPostExecute(ArticalResponse result) {
                if (listener != null) {
                    listener.onAsyncCallBack(result);
                }
            }
        };
        articalTask.execute(blogUrl);
    }
}
