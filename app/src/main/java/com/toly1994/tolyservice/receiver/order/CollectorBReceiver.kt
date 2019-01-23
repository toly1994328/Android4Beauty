package com.toly1994.tolyservice.receiver.order

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/21/021:16:53<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：收藏家
 */
class CollectorBReceiver : BroadcastReceiver() {

    /**
     * 接收时调用的方法
     */
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,
            "收藏家：$resultData", //获取结果并展示
            Toast.LENGTH_LONG).show()
    }
}
