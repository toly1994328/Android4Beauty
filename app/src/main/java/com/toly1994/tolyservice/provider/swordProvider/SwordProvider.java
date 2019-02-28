package com.toly1994.tolyservice.provider.swordProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/28/028:11:42<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：万界神兵录
 */
public class SwordProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int SWORD_QUERY = 0;

    private static final int SWORD_INSERT = 1;
    private static final int SWORD_UPDATE = 2;
    private static final int SWORD_DELETE = 3;

    private static final String TABLE_NAME = "sword";

    static {
        //给当前sUriMatcher添加匹配规则
        sUriMatcher.addURI("toly1994.com.sword", "query", SWORD_QUERY);
        sUriMatcher.addURI("toly1994.com.sword", "insert", SWORD_INSERT);
        sUriMatcher.addURI("toly1994.com.sword", "update", SWORD_UPDATE);
        sUriMatcher.addURI("toly1994.com.sword", "delete", SWORD_DELETE);
    }

    private SQLiteOpenHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = SwordDatabaseHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //进行uri匹配
        int result = sUriMatcher.match(uri);
        if (result == SWORD_QUERY) {
            SQLiteDatabase db = mOpenHelper.getReadableDatabase();
            return db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

        } else {
            throw new IllegalStateException(" query Uri 错误");
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //进行uri匹配
        int result = sUriMatcher.match(uri);
        if (result == SWORD_INSERT) {
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
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        //进行uri匹配
        int result = sUriMatcher.match(uri);
        if (result == SWORD_DELETE) {
            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            int delete = db.delete(TABLE_NAME, selection, selectionArgs);
            db.close();
            return delete;
        } else {
            throw new IllegalStateException("delete Uri  错误");
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        //进行uri匹配
        int result = sUriMatcher.match(uri);
        if (result == SWORD_UPDATE) {
            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            int update = db.update(TABLE_NAME, values, selection, selectionArgs);
            db.close();
            return update;
        } else {
            throw new IllegalStateException("update Uri 错误");
        }
    }
}
