package com.toly1994.tolyservice.receiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.toly1994.tolyservice.R
import com.toly1994.tolyservice.permission.Permission
import com.toly1994.tolyservice.permission.PermissionActivity
import com.toly1994.tolyservice.receiver.receiver.BatteryBReceiver
import kotlinx.android.synthetic.main.ac_br.*


class BatteryBrActivity : PermissionActivity() {
    override fun permissionOk() {

    }

    private lateinit var mBatteryChangeReceiver: BatteryBReceiver

    override//Activity第一次创建时调用
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_br)
        title = "电量改变监听"
        register()//注册广播


        applyPermissions(Permission._READ_SMS)

        id_btn_register.setOnClickListener {
//            register()//注册广播
        }

        id_btn_unregister.setOnClickListener {
            unRegister()//注册广播
        }


    }
    /*
     * 注销广播
     */
    private fun unRegister() {
        unregisterReceiver(mBatteryChangeReceiver)
    }


    /**
     * 动态电量广播接收者
     */
    private fun register() {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)
        mBatteryChangeReceiver = BatteryBReceiver(id_tv_info)
        registerReceiver(mBatteryChangeReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unRegister()//注销广播
    }


}
