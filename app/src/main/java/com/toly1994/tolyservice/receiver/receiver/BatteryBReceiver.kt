package com.toly1994.tolyservice.receiver.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.BatteryManager
import android.util.Log
import android.widget.TextView
import java.util.*


/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/22/022:16:43<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：监听电量变化
 */
class BatteryBReceiver(private val mTV:TextView ) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        // 当前电量
        val currLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
        // 总电量
        val total = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1)
        val percent = currLevel * 100 / total
        Log.e("BatteryBReceiver", "battery: $percent%")
        mTV.setTextColor(randomRGB())
        mTV.text = "battery: $percent%"
    }

    /**
     * 返回随机颜色
     *
     * @return 随机颜色
     */
    fun randomRGB(): Int {
        val random = Random()
        val r = 30 + random.nextInt(200)
        val g = 30 + random.nextInt(200)
        val b = 30 + random.nextInt(200)
        return Color.rgb(r, g, b)
    }
}
