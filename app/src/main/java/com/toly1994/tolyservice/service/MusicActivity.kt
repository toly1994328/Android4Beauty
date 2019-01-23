package com.toly1994.tolyservice.service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.toly1994.tolyservice.R
import com.toly1994.tolyservice.permission.Permission
import com.toly1994.tolyservice.permission.PermissionActivity
import com.toly1994.tolyservice.service.service.MusicPlayerService
import com.toly1994.tolyservice.service.service.MusicPlayerStub
import kotlinx.android.synthetic.main.ac_music.*
import kotlinx.android.synthetic.main.in_ac_music_bottom_bar.*


class MusicActivity : PermissionActivity() {
    override fun permissionOk() {

    }

    private var musicIntent: Intent? = null
    private var mConn: ServiceConnection? = null
    private lateinit var mMusicPlayer: MusicPlayerStub


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_music)

        id_btn_bind.setOnClickListener {
            applyPermissions(Permission._WRITE_EXTERNAL_STORAGE)
            bindMusicService()

        }

        id_btn_unbind.setOnClickListener {
            //[3]解绑服务启动
            if (mConn != null) {
                unbindService(mConn)
            }
        }

        id_btn_start.setOnClickListener {
            startService(musicIntent)
        }

        id_btn_kill.setOnClickListener {
            stopService(musicIntent)
        }

        id_iv_next.setOnAlphaListener {
            mMusicPlayer.next()
        }

        id_iv_prev.setOnAlphaListener {
            mMusicPlayer.prev()
        }

        id_iv_next.setOnClickListener {
            stopService(musicIntent)
        }


        id_btn_play.setOnAlphaListener { view, count ->
            if (count == 1) {
                mMusicPlayer.start()
            } else {
                mMusicPlayer.pause()
            }
        }


        id_pv_pre.setOnDragPVListener {
            mMusicPlayer.seek(it)
        }


    }

    /**
     * 绑定服务
     */
    private fun bindMusicService() {
        musicIntent = Intent(this, MusicPlayerService::class.java)
        mConn = object : ServiceConnection {
            // 当连接成功时候调用
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                mMusicPlayer = service as MusicPlayerStub

                mMusicPlayer.setOnSeekListener {
                        per_100 -> id_pv_pre.setProgress(per_100) }
            }

            // 当连接断开时候调用
            override fun onServiceDisconnected(name: ComponentName) {

            }
        }
        //[2]绑定服务启动
        bindService(musicIntent, mConn, BIND_AUTO_CREATE);
    }
}
