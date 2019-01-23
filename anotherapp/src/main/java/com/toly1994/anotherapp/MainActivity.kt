package com.toly1994.anotherapp

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.ac_br.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_br)
        title="另一个App"

        id_btn_send.setOnClickListener {
            val intent = Intent("www.toly1994.com.br.toast")
            id_et_txt.text
            //为intent添加数据
            intent.putExtra("toast_data", id_et_txt.text.toString())
            intent.component = ComponentName(
                "com.toly1994.tolyservice",//项目包名
                "com.toly1994.tolyservice.receiver.receiver.ToastBroadcastReceiver"//广播接收者全类名
            )
            sendBroadcast(intent)
        }
    }
}
