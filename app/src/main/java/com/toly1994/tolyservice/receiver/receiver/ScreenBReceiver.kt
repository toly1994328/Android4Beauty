package com.toly1994.tolyservice.receiver.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/22/022:16:43<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：开屏锁屏广播
 */
class ScreenBReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //[1]获取到当前广播的事件类型
        val action = intent.action
        //[2]对当前广播事件类型做一个判断
        if ("android.intent.action.SCREEN_OFF" == action) {
            Log.e(TAG, "屏幕锁屏了")
        } else if ("android.intent.action.SCREEN_ON" == action) {
            Log.e(TAG, "屏幕解锁了")
        }
    }

    companion object {
        private const val TAG = "ScreenBReceiver"
    }
}
