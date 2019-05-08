package com.toly1994.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:12:14<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class FeedCatService extends Service {
    private static final String TAG = "FeedCatService";
    private Binder binder = new ICatService.Stub() {
        @Override
        public void feed(String aString) throws RemoteException {
            Log.e(TAG, "feed: 你已喂" + aString + "给小猫土土了");
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
