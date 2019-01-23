package com.toly1994.tolyservice.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/1/23/023:16:32<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：音乐播放服务
 */
public class MusicPlayerService extends Service {
    private MusicPlayerStub musicPlayerStub;

    @Override
    public void onCreate() {
        super.onCreate();
        ArrayList<String> musicList = new ArrayList<>();
        musicList.add("/sdcard/toly/此生不换_青鸟飞鱼.aac");
        musicList.add("/sdcard/toly/勇气-梁静茹-1772728608-1.mp3");
        musicList.add("/sdcard/toly/草戒指_魏新雨.aac");
        musicList.add("/sdcard/toly/郭静 - 下一个天亮 [mqms2].flac");

        musicPlayerStub = new MusicPlayerStub(this);
        try {
            musicPlayerStub.create(musicList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicPlayerStub;
    }
}
