package com.example.myapplication;

import com.tencent.wcdb.Cursor;

public class DbQueryUtil {
    private volatile static DbQueryUtil _instance;

    private DbQueryUtil() {
    }

    public static DbQueryUtil get_Instance() {
   /*     if (_instance == null) {
            synchronized (DbQueryUtil.class) {
                if (_instance == null) {
                    _instance = new DbQueryUtil();
                }
            }
        }*/
        return _instance = new DbQueryUtil();
    }

    private Cursor mQuery;

    public void initCursor(Cursor query) {
        this.mQuery = query;
    }

    public int queryInt(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getInt(columnIndex);
    }

    private void QuesryException() {
        if (mQuery == null) throw new NullPointerException("mQuery 不能为空，请执行intCursor()");
    }

    public String queryString(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getString(columnIndex);
    }

    public byte[] queryBLOB(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getBlob(columnIndex);
    }

    public double querydouble(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getDouble(columnIndex);
    }

    public Float queryFloat(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getFloat(columnIndex);
    }

    public Long queryLong(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getLong(columnIndex);
    }

    public Short queryShort(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getShort(columnIndex);
    }

}