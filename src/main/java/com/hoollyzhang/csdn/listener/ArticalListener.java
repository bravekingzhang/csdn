package com.hoollyzhang.csdn.listener;

import com.hoollyzhang.csdn.response.ArticalResponse;

/**
 * Created by brzhang on 15/5/19.
 */
public interface ArticalListener {
    void onAsyncCallBack(ArticalResponse ret);

    void onProgress(Integer progres);
}
