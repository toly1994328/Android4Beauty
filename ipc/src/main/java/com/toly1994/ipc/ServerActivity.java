package com.toly1994.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:12:30<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, SocketService.class));
        findViewById(R.id.to_one).setOnClickListener(v -> {
            new Thread(() -> {
                Socket socket = null;
                try {
                    socket = new Socket("localhost", 8080);
                    //客户端请求
                    BufferedWriter bw = new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream()));
                    bw.write("我要猫");
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        });

    }
}
