package com.toly1994.tolyservice.receiver

import android.content.IntentFilter
import android.os.Bundle
import com.toly1994.tolyservice.R
import com.toly1994.tolyservice.permission.Permission
import com.toly1994.tolyservice.permission.PermissionActivity
import com.toly1994.tolyservice.receiver.receiver.SMSBReceiver
import kotlinx.android.synthetic.main.ac_br.*


class SMSBrActivity : PermissionActivity() {
    override fun permissionOk() {

    }

    private lateinit var mSmsReceiver: SMSBReceiver

    override//Activity第一次创建时调用
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_br)
        title = "BrActivity"

        applyPermissions(Permission._READ_SMS)

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
        unregisterReceiver(mSmsReceiver)
    }


    /**
     * 动态注册短信广播接收者
     */
    private fun register() {
        //注册短信广播接收者
        val smsFilter = IntentFilter()
        smsFilter.addAction("android.provider.Telephony.SMS_RECEIVED")
        mSmsReceiver = SMSBReceiver()
        registerReceiver(mSmsReceiver, smsFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unRegister()//注销广播
    }


}
