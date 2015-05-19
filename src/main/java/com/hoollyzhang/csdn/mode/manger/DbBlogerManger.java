package com.hoollyzhang.csdn.mode.manger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hoollyzhang.csdn.mode.Bloger;
import com.hoollyzhang.csdn.mode.helper.BlogerHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by brzhang on 15/5/17.
 */
public class DbBlogerManger {
    private static final String TAG="BlogerManger";
    private BlogerHelper   helper;
    private SQLiteDatabase db;

    public DbBlogerManger(Context context) {
        helper = new BlogerHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    public void insertBloger(Bloger bloger){
        db.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(BlogerHelper.USER_NAME,bloger.getBlogerName());
            contentValues.put(BlogerHelper.USER_BLOG_SRC,bloger.getBlogerSrc());
            contentValues.put(BlogerHelper.USER_PIC_SRC,bloger.getBlogerPic());
            db.insertWithOnConflict(BlogerHelper.TABLE_NAME_USER, "", contentValues, 1);
            db.setTransactionSuccessful();
        }catch ( Exception e ){
            Log.e(TAG,e.getMessage());
        }finally {
            db.endTransaction();
        }
    }

    public List<Bloger> listBloger(){
        ArrayList<Bloger> blogerArrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ").append(BlogerHelper.TABLE_NAME_USER);
        Cursor c = db.rawQuery(sb.toString(),null);
        while (c.moveToNext()) {
            Bloger bloger = new Bloger();
            bloger.setBlogerName(c.getString(c.getColumnIndex(BlogerHelper.USER_NAME)));
            bloger.setBlogerPic(c.getString(c.getColumnIndex(BlogerHelper.USER_PIC_SRC)));
            bloger.setBlogerSrc(c.getString(c.getColumnIndex(BlogerHelper.USER_BLOG_SRC)));
            blogerArrayList.add(bloger);
        }
        c.close();
        return blogerArrayList;
    }

    public void closedb() {
        if (db != null)
            db.close();
    }
}
