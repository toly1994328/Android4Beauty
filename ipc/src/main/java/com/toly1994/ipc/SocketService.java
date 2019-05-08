package com.toly1994.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:15:08<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class SocketService extends Service {
    private static final String TAG = "SocketService";
    private boolean quit = false;


    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: ");
        FeedServer feedServer = new FeedServer();
        new Thread(feedServer).start();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class FeedServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8080);
                Log.e(TAG, "serverSocket onCreate: ");
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (!quit) {
                try {
                    Socket socket = serverSocket.accept();
                    //接收客户端消息
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    String s = br.readLine();
                    Log.e(TAG, "来自客户端: " + socket.getInetAddress() + "说:" + s);

                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
