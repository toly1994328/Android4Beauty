package com.toly1994.anotherapp

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import com.toly1994.tolyservice.IMusicPlayerService
import kotlinx.android.synthetic.main.ac_br.*

class ServiceTestActivity : AppCompatActivity() {
    private var mConn: ServiceConnection? = null
    private lateinit var mMusicPlayer: IMusicPlayerService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_br)
        title="另一个App"
        bindMusicService()
        id_btn_send.text="播放音乐"
        id_btn_send.setOnClickListener {
            mMusicPlayer.start()
        }
    }


    /**
     * 绑定服务
     */
    private fun bindMusicService() {
        val intent = Intent()
        //坑点：5.0以后要加 服务包名，不然报错
        intent.setPackage("com.toly1994.tolyservice")
        intent.action = "www.toly1994.com.music.player"
        mConn = object : ServiceConnection {
            // 当连接成功时候调用
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                //核心点获取IMusicPlayerService对象
                mMusicPlayer = IMusicPlayerService.Stub.asInterface(service)
            }

            // 当连接断开时候调用
            override fun onServiceDisconnected(name: ComponentName) {
            }
        }
        //[2]绑定服务启动
        bindService(intent, mConn, BIND_AUTO_CREATE);
    }
}
