package com.toly1994.tolyservice.receiver

import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.toly1994.tolyservice.R
import com.toly1994.tolyservice.receiver.receiver.ScreenBReceiver
import kotlinx.android.synthetic.main.ac_br.*


class ScreenBrActivity : AppCompatActivity() {

    private lateinit var mScreenReceiver: ScreenBReceiver

    override//Activity第一次创建时调用
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_br)
        title = "BrActivity"

        id_btn_register.setOnClickListener {
            register()//注册广播
        }

        id_btn_unregister.setOnClickListener {
            unRegister()//注册广播
        }


    }
    /*
     * 注销广播
     */
    private fun unRegister() {
        unregisterReceiver(mScreenReceiver)
    }


    /**
     * 动态的去注册屏幕解锁和锁屏的广播
     */
    private fun register() {
        // [1]动态的去注册屏幕解锁和锁屏的广播
        mScreenReceiver = ScreenBReceiver()
        // [2]创建intent-filter对象
        val filter = IntentFilter()
        // [3]添加要注册的action
        filter.addAction("android.intent.action.SCREEN_OFF")
        filter.addAction("android.intent.action.SCREEN_ON")
        // [4]注册广播接收者
        registerReceiver(mScreenReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unRegister()//注销广播
    }


}
