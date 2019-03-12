package com.example.myapplication;

import android.content.Context;

import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;

public class OpeN extends SQLiteOpenHelper {
    public OpeN(Context context) {
        super(context, "user", null, 1);
    }

    public static final String TABLE_NAME = "calls";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + "" +
                "(id integer,name text,age text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
