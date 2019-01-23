package com.toly1994.tolyservice.receiver.order

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/21/021:16:53<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：雕刻家
 */
class GraverBReceiver : BroadcastReceiver() {

    /**
     * 接收时调用的方法
     */
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "雕刻家:$resultData", //[1]获取结果并展示
            Toast.LENGTH_LONG).show()
      abortBroadcast();//[2]终止广播
        resultData = "价值10W元"//[3]传递数据---给下一个广播
    }
}
