package com.toly1994.anotherapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/27/027:19:52<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：另一个app测试SwordProvider
 */
public class SwordProviderActivity extends AppCompatActivity {
    private static final String TAG = "SwordProviderActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentResolver resolver = getContentResolver();


//        insert(resolver); //插入测试
//        query(resolver);//查询测试
        update(resolver);//更新测试
        query(resolver);
        delete(resolver);//删除测试
        query(resolver);
    }

    /**
     * 删除测试
     *
     * @param resolver
     */
    private void delete(ContentResolver resolver) {

        Uri uri = Uri.parse("content://toly1994.com.sword/delete");
        resolver.delete(uri, "name=?", new String[]{"屠龙刀"});
    }

    /**
     * 插入测试
     *
     * @param resolver
     */
    private void insert(ContentResolver resolver) {
        Uri uri = Uri.parse("content://toly1994.com.sword/insert");
        ContentValues values = new ContentValues();
        values.put("name", "屠龙刀");
        values.put("atk", "3000");
        values.put("user", "张无忌");
        resolver.insert(uri, values);

    }

    /**
     * 更新测试
     *
     * @param resolver
     */
    private void update(ContentResolver resolver) {
        Uri uri = Uri.parse("content://toly1994.com.sword/update");
        ContentValues values = new ContentValues();
        values.put("name", "屠龙刀");
        values.put("atk", "3500");
        values.put("user", "张无忌");
        resolver.update(uri, values, "name=?", new String[]{"屠龙刀"});
    }

    /**
     * 查询测试
     *
     * @param resolver
     */
    private void query(ContentResolver resolver) {
        Uri uri = Uri.parse("content://toly1994.com.sword/query");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {//遍历游标，获取数据，储存在bean中
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int atk = cursor.getInt(cursor.getColumnIndex("atk"));
            String user = cursor.getString(cursor.getColumnIndex("user"));
            Log.e(TAG, "query: " + id + "---" + name + "---" + atk + "---" + user);
            //query: 2---屠龙刀---3000---张无忌
        }
    }

}
