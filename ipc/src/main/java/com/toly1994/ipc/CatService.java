package com.toly1994.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:12:14<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class CatService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(new Handler(msg -> {
            //接收客户端数据/信息/对象
            String data = msg.getData().getString("request");
            Log.e("MessengerActivity", "handleMessage: " + data);

            //向客户端发送数据/信息/对象
            Messenger client = msg.replyTo;
            Message message = Message.obtain();
            Cat cat = new CatManager().getCatAt(0);
            Bundle bundle = new Bundle();//创建Bundle对象
            bundle.putParcelable("cat", cat);//把猫装到Bundle里，贴个标签cat
            message.setData(bundle);
            try {
                client.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            return false;
        })).getBinder();
    }
}
