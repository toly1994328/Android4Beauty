package com.toly1994.tolyservice.service.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/17/017:21:30<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：Service测试
 */
class ToastService : Service() {

    /**
     * 绑定Service
     * @param intent 意图
     * @return IBinder对象
     */
    override fun onBind(intent: Intent): IBinder? {
        Log.e(TAG, "onBind: ")
        return null
    }

    /**
     * 创建Service
     */
    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: ")
    }

    /**
     * 开始执行命令
     * @param intent 意图
     * @param flags 启动命令的额外数据
     * @param startId id
     * @return
     */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val data = intent.getStringExtra("toast_data")
        Log.e(TAG, "onStartCommand:--- $data")
        //data?:"NO MSG"表示如果data是空，就取"NO MSG"
        Toast.makeText(this, data ?: "NO MSG", Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }


    /**
     * 解绑服务
     * @param intent 意图
     * @return
     */
    override fun onUnbind(intent: Intent): Boolean {
        Log.e(TAG, "onUnbind: 成功解绑")
        return super.onUnbind(intent)
    }

    /**
     * 销毁服务
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: 销毁服务")
    }

    companion object {
        private val TAG = "ToastService"
    }
}
