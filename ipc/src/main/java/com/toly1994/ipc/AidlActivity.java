package com.toly1994.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:12:30<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class AidlActivity extends AppCompatActivity {
    private ServiceConnection conn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = new ServiceConnection() {

            private ICatService catService;

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                catService = ICatService.Stub.asInterface(service);
                try {
                    catService.feed("鱼");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };



        bindService(new Intent(this, FeedCatService.class), conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
