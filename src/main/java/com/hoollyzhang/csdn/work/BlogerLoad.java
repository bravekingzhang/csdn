package com.hoollyzhang.csdn.work;

import android.content.Context;

import com.hoollyzhang.csdn.mode.Bloger;
import com.hoollyzhang.csdn.mode.manger.DbBlogerManger;
import com.hoollyzhang.csdn.response.BlogerResponse;

import java.util.ArrayList;

/**
 * Created by brzhang on 15/5/19.
 */
public class BlogerLoad {

    public static BlogerResponse getBlogerlList(Context context) {
        BlogerResponse response = new BlogerResponse();
        DbBlogerManger blogerManger = new DbBlogerManger(context);
        ArrayList<Bloger> blogLists = (ArrayList<Bloger>) blogerManger.listBloger();
        response.setBlogers(blogLists);
        return response;
    }

    public static int insertBloger(Context context, Bloger bloger) {
        DbBlogerManger blogerManger = new DbBlogerManger(context);
        blogerManger.insertBloger(bloger);
        return 0;
    }
}
