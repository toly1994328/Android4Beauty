package com.toly1994.tolyservice.provider.swordProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/28/028:11:49<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class SwordDatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "weapon.db";//数据库名
    private static int DATABASE_VERSION = 1;//数据库版本

    private static SwordDatabaseHelper sInstance;


    //双检锁单例
    public static synchronized SwordDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SwordDatabaseHelper.class) {
                if (sInstance == null) {
                    sInstance = new SwordDatabaseHelper(context);
                }
            }
        }
        return sInstance;
    }


    public SwordDatabaseHelper(@Nullable Context context) {
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
        db.execSQL("CREATE TABLE sword (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name VARCHAR(32) NOT NULL," +
                "atk INTEGER  DEFAULT 1000," +
                "user VARCHAR(32) NOT NULL" +
                "); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
