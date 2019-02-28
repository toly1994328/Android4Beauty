package com.toly1994.tolyservice.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import com.toly1994.tolyservice.R;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/1/25/025:14:24<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：子线程间通信
 */
public class HandlerOtherActivity extends AppCompatActivity {
    private TextView msgTv;
    private Handler mHandler;

    private Looper mOtherLooper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ac_handler);
        msgTv = findViewById(R.id.id_tv_handler);

        msgTv.setOnClickListener(v -> {

            new Thread("第一子宇宙") {//第一子宇宙
                @Override
                public void run() {
                    Message newMsg = new Message();
                    newMsg.what = 0x01;
                    newMsg.obj = "玉面奕星龙";
                    mHandler.sendMessage(newMsg);
                    Log.e("HandlerOtherActivity", "当前线程名称: " + Thread.currentThread().getName() + " 发送：" + newMsg.obj);
                }
            }.start();
        });

        new Thread("第二子宇宙") {//第二子宇宙
            @Override
            public void run() {
                Looper.prepare();//让当前子宇宙生成--looper能源
                mOtherLooper = Looper.myLooper();

                Log.e("HandlerOtherActivity", "run: ");
                Looper.loop();//looper能源启动--此时该线程会阻塞------下面的方法无法执行
                Log.e("HandlerOtherActivity", "run:-------- ");
            }
        }.start();

        try {
            Thread.sleep(100);
            //Handler通过第二子宇宙的looper能源能源构造
            mHandler = new Handler(mOtherLooper,msg -> {
                Log.e("HandlerOtherActivity", "当前线程名称: " + Thread.currentThread().getName() + " 接收：" + msg.obj);
                return false;
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
