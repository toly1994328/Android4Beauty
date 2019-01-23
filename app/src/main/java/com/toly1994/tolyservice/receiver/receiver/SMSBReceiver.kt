package com.toly1994.tolyservice.receiver.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/22/022:16:43<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：短信监听广播
 */
class SMSBReceiver : BroadcastReceiver() {
    //当短信到来的时候 就会执行这个方法
    override fun onReceive(context: Context, intent: Intent) {
        //[1]获取发短信送的号码  和内容
        val objects = intent.extras!!.get("pdus") as Array<*>
        val format = intent.getStringExtra("format")
        for (pdu in objects) {
            //[2]获取smsmessage实例
            val smsMessage =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    SmsMessage.createFromPdu(pdu as ByteArray, format)
                } else {
                    SmsMessage.createFromPdu(pdu as ByteArray)
                }
            //[3]获取发送短信的内容
            val body = smsMessage.messageBody
            val date = Date(smsMessage.timestampMillis)//时间
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
            //[4]获取发送者
            val address = smsMessage.originatingAddress
            val receiveTime = format.format(date)
            Log.e("SMSBReceiver", "body:$body---$address---$receiveTime")
        }
    }
}
