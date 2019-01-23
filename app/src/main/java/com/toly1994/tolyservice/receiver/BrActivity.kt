package com.toly1994.tolyservice.receiver

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.toly1994.tolyservice.R
import com.toly1994.tolyservice.receiver.order.BoyBReceiver
import com.toly1994.tolyservice.receiver.order.CollectorBReceiver
import com.toly1994.tolyservice.receiver.order.GraverBReceiver
import com.toly1994.tolyservice.receiver.order.RubyManBReceiver
import com.toly1994.tolyservice.receiver.receiver.Toast2BroadcastReceiver
import kotlinx.android.synthetic.main.ac_br.*


class BrActivity : AppCompatActivity() {


    private lateinit var mReceiver: Toast2BroadcastReceiver

    private lateinit var boyReceiver: BoyBReceiver
    private lateinit var graverReceiver: GraverBReceiver
    private lateinit var rubyManReceiver: RubyManBReceiver


    override//Activity第一次创建时调用
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_br)
        title = "BrActivity"


//        receiver = ToastBroadcastReceiver()


        id_btn_send.setOnClickListener {

            //            sendMsg() //发送广播
//            sendStatic()//发送广播
            sendOrder()
        }

        id_btn_register.setOnClickListener {
            register()//注册广播
        }

        id_btn_unregister.setOnClickListener {
            unRegister()//注册广播
        }


    }

    /**
     * 发送有序广播
     */
    private fun sendOrder() {
        val intent = Intent()
        intent.action = "www.toly1994.com.br.toast2"
        val collectorBReceiver = CollectorBReceiver()
        sendOrderedBroadcast(
            intent, null,
            collectorBReceiver, null, 1,
            "价值1元", null
        )
    }

    /**
     * 注销广播
     */
    private fun unRegister() {
//        unregisterReceiver(mReceiver)
        unregisterReceiver(boyReceiver)
        unregisterReceiver(graverReceiver)
        unregisterReceiver(rubyManReceiver)
    }

    /**
     * 发送广播
     */
    private fun sendMsg() {
        val intent = Intent()
        intent.action = "www.toly1994.com.br.toast2"
        intent.putExtra("toast_data", id_et_msg.text.toString())
        sendBroadcast(intent)
    }

    /**
     * 注册广播
     */
    private fun register() {
        boyReceiver = BoyBReceiver()
        val boyFilter = IntentFilter()//创建意图过滤器
        boyFilter.addAction("www.toly1994.com.br.toast2")//添加意图
        boyFilter.priority = 10

        graverReceiver = GraverBReceiver()
        val graverFilter = IntentFilter()//创建意图过滤器
        graverFilter.addAction("www.toly1994.com.br.toast2")//添加意图
        graverFilter.priority = 20

        rubyManReceiver = RubyManBReceiver()

        val rubyManFilter = IntentFilter()//创建意图过滤器
        rubyManFilter.addAction("www.toly1994.com.br.toast2")//添加意图
        rubyManFilter.priority = 21

//        mReceiver = Toast2BroadcastReceiver()//创建 Toast2BroadcastReceiver
//        registerReceiver(mReceiver, filter)//注册

        registerReceiver(boyReceiver, boyFilter)//注册
        registerReceiver(graverReceiver, graverFilter)//注册
        registerReceiver(rubyManReceiver, rubyManFilter)//注册
    }


    private fun sendStatic() {
        val intent = Intent("www.toly1994.com.br.toast")
        id_et_msg.text
        //为intent添加数据
        intent.putExtra("toast_data", id_et_msg.text.toString())

        intent.component = ComponentName(
            "com.toly1994.tolyservice",//项目包名
            "com.toly1994.tolyservice.receiver.receiver.ToastBroadcastReceiver"//广播接收者全类名
        )
        sendBroadcast(intent)
    }


    override fun onDestroy() {
        super.onDestroy()
        unRegister()//注销广播
    }


}
