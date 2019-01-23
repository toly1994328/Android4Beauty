package com.toly1994.tolyservice.receiver.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/21/021:16:53<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：谈一个吐司的BroadcastReceiver
 */
class ToastBroadcastReceiver : BroadcastReceiver() {

    /**
     * 接收时调用的方法
     */
    override fun onReceive(context: Context, intent: Intent) {
        val data = intent.getStringExtra("toast_data")
        //data?:"NO MSG"表示如果data是空，就取"NO MSG"
        Toast.makeText(context, data?:"NO MSG", Toast.LENGTH_SHORT).show()
    }
}
