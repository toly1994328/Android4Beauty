package com.toly1994.anotherapp

import android.content.ComponentName
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.ac_br.*

class MusicServiceTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_br)
        title="另一个App"




        id_btn_send.setOnClickListener {
            id_et_txt.text
            //为intent添加数据
            intent.putExtra("toast_data", id_et_txt.text.toString())
            intent.component = ComponentName(
                "com.toly1994.tolyservice",//项目包名
                "com.toly1994.tolyservice.service.service.ToastService"//组件全类名
            )
            startService(intent)
        }
    }
}
