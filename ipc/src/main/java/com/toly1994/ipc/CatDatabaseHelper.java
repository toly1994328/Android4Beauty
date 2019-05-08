package com.toly1994.ipc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:14:49<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class CatDatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "cat.db";//数据库名
    private static int DATABASE_VERSION = 1;//数据库版本
    private volatile static CatDatabaseHelper sInstance;
    //双检锁单例
    public static synchronized CatDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (CatDatabaseHelper.class) {
                if (sInstance == null) {
                    sInstance = new CatDatabaseHelper(context);
                }
            }
        }
        return sInstance;
    }
    public CatDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createSwordTable(db);
    }
    /**
     * 创建sword表
     *
     * @param db SQLiteDatabase
     */
    private void createSwordTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cat (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name VARCHAR(32) NOT NULL," +
                "color VARCHAR(32) NOT NULL" +
                "); ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
