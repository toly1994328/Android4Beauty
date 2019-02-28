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
 * 说明：当msg自身有Runnable回调时
 */
public class HandlerMsgWithCbkActivity extends AppCompatActivity {
    private TextView msgTv;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler.Callback callback = msg -> {
            msgTv.setText("回调的handleMessage");
            return true;
        };

        mHandler = new Handler(callback) {
            @Override
            public void handleMessage(Message msg) {
                msgTv.setText("覆写的handleMessage");

            }
        };

        setContentView(R.layout.ac_handler);
        msgTv = findViewById(R.id.id_tv_handler);

        msgTv.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        msgTv.setText("Message + Runnable");
                    }
                }, 3000);
            });
            thread.start();
        });
    }
}
