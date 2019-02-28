package com.toly1994.tolyservice.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.toly1994.tolyservice.R;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/1/25/025:14:24<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：三种创建消息对象的方式
 */
public class HandlerActivity extends AppCompatActivity {
    private TextView msgTv;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler.Callback callback = msg -> {
            String txt = (String) msg.obj;
            switch (msg.what) {
                case 0x01:
                    txt += "----第一条";
                    break;
                case 0x02:
                    txt += "----第二条";
                    break;
                case 0x03:
                    txt += "----第三条";
                    break;
            }
            msgTv.setText(txt);
            return true;
        };
        mHandler = new Handler(callback);

        setContentView(R.layout.ac_handler);
        msgTv = findViewById(R.id.id_tv_handler);

        msgTv.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                Message newMsg = new Message();
                newMsg.what = 0x01;
                newMsg.obj = "玉面奕星龙";
                mHandler.sendMessage(newMsg);

                Message msgObtain = Message.obtain();
                msgObtain.what = 0x02;
                msgObtain.obj = "张风捷特烈";
                mHandler.sendMessageDelayed(msgObtain, 3000);

                Message handlerObtain = mHandler.obtainMessage();
                handlerObtain.what = 0x03;
                handlerObtain.obj = "千里巫缨";
                mHandler.sendMessageDelayed(handlerObtain, 6000);
            });
            thread.start();
        });
    }
}
