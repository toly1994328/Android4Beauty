package com.toly1994.tolyservice.service

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.toly1994.tolyservice.R
import kotlinx.android.synthetic.main.ac_music.*


class ToastSActivity : AppCompatActivity() {

    private var toastIntent: Intent? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_music)
        id_btn_bind.visibility = View.INVISIBLE
        id_btn_unbind.visibility = View.INVISIBLE

        id_btn_start.setOnClickListener {
//            toastIntent = Intent(this, ToastService::class.java)

            toastIntent=Intent("www.toly1994.com.service.toast")
            toastIntent?.putExtra("toast_data", id_et_msg.text.toString())
            startService(toastIntent)
        }

        id_btn_kill.setOnClickListener {
            stopService(toastIntent)
        }
    }

}
