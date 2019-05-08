package com.toly1994.ipc;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:12:30<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert(getContentResolver());

    }
    /**
     * 插入测试
     *
     * @param resolver
     */
    private void insert(ContentResolver resolver) {
        Uri uri = Uri.parse("content://toly1994.com.cat/insert");
        ContentValues values = new ContentValues();
        values.put("name", "土土");
        values.put("color", "灰色");
        resolver.insert(uri, values);
    }
}
