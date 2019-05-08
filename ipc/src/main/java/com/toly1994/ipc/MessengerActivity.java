package com.toly1994.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:12:30<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class MessengerActivity extends AppCompatActivity {
    private static final String TAG = "MessengerActivity";
    private ServiceConnection conn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = new ServiceConnection() {
            private Messenger messenger;

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                messenger = new Messenger(service);
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("request", "来自客户端：给我一只猫");
                message.setData(bundle);


                message.replyTo = new Messenger(new Handler((msg) -> {//服务端回应监听
                    Bundle data = msg.getData();
                    data.setClassLoader(getClass().getClassLoader());
                    Cat cat = (Cat) (data.get("cat"));
                    Log.e(TAG, "来自服务端: "+cat);
                    return false;

                }));

                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };

        bindService(new Intent(this, CatService.class), conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
