package com.toly1994.ipc;

import android.app.Application;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:9:47<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class CatApplication extends Application {
    private static final String TAG = "CatApplication";

    @Override
    public void onCreate() {
        super.onCreate();

//        Log.e("CatApplication", manager.getCat().toString());

//        Log.e(TAG, "onCreate: 创建了小猫土土");
    }
}
