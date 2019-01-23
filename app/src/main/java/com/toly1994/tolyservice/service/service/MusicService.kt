package com.toly1994.tolyservice.service.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.toly1994.tolyservice.service.MusicPlayer

/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/17/017:21:30<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：播放Service测试
 */
class MusicService : Service() {
    private lateinit var mMusicPlayer: MusicPlayer


    /**
     * 绑定Service
     * @param intent 意图
     * @return IBinder对象
     */
    override fun onBind(intent: Intent): IBinder? {
        Log.e(TAG, "onBind: ")
        Toast.makeText(this, "Bind OK", Toast.LENGTH_SHORT).show()
        return mMusicPlayer
    }

    /**
     * 创建Service
     */
    override fun onCreate() {
        super.onCreate()

        val musicList = arrayListOf<String>()

        musicList.add("/sdcard/toly/此生不换_青鸟飞鱼.aac")
        musicList.add("/sdcard/toly/勇气-梁静茹-1772728608-1.mp3")
        musicList.add("/sdcard/toly/草戒指_魏新雨.aac")
        musicList.add("/sdcard/toly/郭静 - 下一个天亮 [mqms2].flac")


        mMusicPlayer = MusicPlayer(this)
        mMusicPlayer.create(musicList)

        Log.e(TAG, "onCreate: ")
    }

    /**
     * 开始执行命令
     * @param intent 意图
     * @param flags 启动命令的额外数据
     * @param startId id
     * @return
     */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }


    /**
     * 解绑服务
     * @param intent 意图
     * @return
     */
    override fun onUnbind(intent: Intent): Boolean {
        Toast.makeText(this, "onUnbind: 成功解绑", Toast.LENGTH_SHORT).show()
        mMusicPlayer.release()
        Log.e(TAG, "onUnbind: 成功解绑")
        return super.onUnbind(intent)
    }

    /**
     * 销毁服务
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: 销毁服务")
    }

    companion object {
        private val TAG = "MusicService"
    }
}
