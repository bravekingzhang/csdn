package com.hoollyzhang.csdn.manger;

import android.content.Context;

import com.hoollyzhang.csdn.listener.BlogerListener;
import com.hoollyzhang.csdn.logic.BlogerLogic;
import com.hoollyzhang.csdn.mode.Bloger;

/**
 * Created by brzhang on 15/5/19.
 */
public class BlogerManger {

    public static void getBlogerList(Context context,BlogerListener listener) {
        new BlogerLogic(context, listener).getBlogerList();
    }

    public static void insertBloger(Context context,BlogerListener listener, Bloger bloger) {
        new BlogerLogic(context, listener).insertBloger(bloger);
    }
}
