package com.toly1994.ipc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:14:47<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class CatContentProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int CAT_QUERY = 0;
    private static final int CAT_INSERT = 1;
    private static final int CAT_UPDATE = 2;
    private static final int CAT_DELETE = 3;

    private static final String TABLE_NAME = "cat";

    static {
        //给当前sUriMatcher添加匹配规则
        sUriMatcher.addURI("toly1994.com.cat", "query", CAT_QUERY);
        sUriMatcher.addURI("toly1994.com.cat", "insert", CAT_INSERT);
        sUriMatcher.addURI("toly1994.com.cat", "update", CAT_UPDATE);
        sUriMatcher.addURI("toly1994.com.cat", "delete", CAT_DELETE);
    }

    private SQLiteOpenHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = CatDatabaseHelper.getInstance(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //进行uri匹配
        int result = sUriMatcher.match(uri);
        if (result == CAT_QUERY) {
            SQLiteDatabase db = mOpenHelper.getReadableDatabase();
            return db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

        } else {
            throw new IllegalStateException(" query Uri 错误");
        }
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //进行uri匹配
        int result = sUriMatcher.match(uri);
        if (result == CAT_INSERT) {
            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            Long insert = db.insert(TABLE_NAME, null, values);
            //uri：数据发送变化，通过uri判断调用哪个内容观察者
            //第二个参数：内容观察者对象  如果传null 则注册了整个uri的内容观察者皆可以收到通知
            getContext().getContentResolver().notifyChange(uri, null);
            db.close();
            return Uri.parse(String.valueOf(insert));
        } else {
            throw new IllegalStateException("insert Uri 错误");
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //进行uri匹配
        int result = sUriMatcher.match(uri);
        if (result == CAT_DELETE) {
            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            int delete = db.delete(TABLE_NAME, selection, selectionArgs);
            db.close();
            return delete;
        } else {
            throw new IllegalStateException("delete Uri  错误");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        //进行uri匹配
        int result = sUriMatcher.match(uri);
        if (result == CAT_UPDATE) {
            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            int update = db.update(TABLE_NAME, values, selection, selectionArgs);
            db.close();
            return update;
        } else {
            throw new IllegalStateException("update Uri 错误");
        }
    }
}
