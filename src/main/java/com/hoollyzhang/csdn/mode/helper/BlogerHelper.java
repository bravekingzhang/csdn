package com.hoollyzhang.csdn.mode.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by brzhang on 15/5/17.
 */
public class BlogerHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME    = "bloger.db";
    private static final int    DATABASE_VERSION = 1;

    public static final String TABLE_NAME_USER = "user";
    public static final String USER_ID = "_id";
    public static final String USER_NAME = "name";
    public static final String USER_BLOG_SRC = "blog_src";
    public static final String USER_PIC_SRC = "pic_src";

    public BlogerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public BlogerHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists ").append(TABLE_NAME_USER).append(" (")
                .append(USER_ID).
                append(" INTEGER PRIMARY KEY AUTOINCREMENT, ").
                append(USER_NAME).append(" char(40), ").
                append(USER_PIC_SRC).append(" varchar(50), ").
                append(USER_BLOG_SRC).append(" varchar(50) ").
                append(")");
        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
