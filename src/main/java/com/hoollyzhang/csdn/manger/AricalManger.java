package com.hoollyzhang.csdn.manger;

import android.content.Context;

import com.hoollyzhang.csdn.listener.ArticalListener;
import com.hoollyzhang.csdn.logic.ArticalLogic;

/**
 * Created by brzhang on 15/5/18.
 */
public class AricalManger {
    /**
     * 从网络上拉取文章列表
     * @param blogUrl
     * @param pageIndex
     */
    public static void requestAtricalList(Context context,ArticalListener listener,String blogUrl, int pageIndex) {
        new ArticalLogic(context,listener).getAtricalList(blogUrl, pageIndex);
    }
}
