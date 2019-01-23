package com.toly1994.tolyservice.receiver.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/22/022:16:43<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：app安装/卸载改变时广播监听
 */
class AppBReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        val uri = intent.data
        if (action == "android.intent.action.PACKAGE_ADDED") {
            Log.e("AppBReceiver", uri.toString() + "被安装了")
        } else if (action == "android.intent.action.PACKAGE_REPLACED") {
            Log.e("AppBReceiver", uri.toString() + "被更新了")
        } else if (action == "android.intent.action.PACKAGE_REMOVED") {
            Log.e("AppBReceiver", uri.toString() + "被卸载了")
        }
    }

}
