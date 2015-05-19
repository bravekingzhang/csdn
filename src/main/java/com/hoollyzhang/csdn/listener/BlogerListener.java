package com.hoollyzhang.csdn.listener;

import com.hoollyzhang.csdn.response.BlogerResponse;

/**
 * Created by brzhang on 15/5/19.
 */
public interface BlogerListener {

    void onProgress(Integer progres);

    void onAsyncCallBack(BlogerResponse result);

    void onInsert();
}
