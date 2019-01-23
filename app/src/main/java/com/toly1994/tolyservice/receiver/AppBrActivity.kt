package com.toly1994.tolyservice.receiver

import android.content.IntentFilter
import android.os.Bundle
import com.toly1994.tolyservice.R
import com.toly1994.tolyservice.permission.Permission
import com.toly1994.tolyservice.permission.PermissionActivity
import com.toly1994.tolyservice.receiver.receiver.AppBReceiver
import kotlinx.android.synthetic.main.ac_br.*


class AppBrActivity : PermissionActivity() {
    override fun permissionOk() {

    }

    private lateinit var mAppReceiver: AppBReceiver

    override//Activity第一次创建时调用
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_br)
        title = "App安装/卸载监听"

        applyPermissions(Permission._READ_SMS)



        register()//注册广播

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
        unregisterReceiver(mAppReceiver)
    }


    /**
     * 动态注册app安装/卸载改变时广播监听
     */
    private fun register() {
        val filter = IntentFilter()
        filter.addAction("android.intent.action.PACKAGE_ADDED")
        filter.addAction("android.intent.action.PACKAGE_REPLACED")
        filter.addAction("android.intent.action.PACKAGE_REMOVED")
        filter.addDataScheme("package")
        mAppReceiver = AppBReceiver()
        registerReceiver(mAppReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unRegister()//注销广播
    }


}
