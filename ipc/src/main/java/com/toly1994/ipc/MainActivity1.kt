package com.toly1994.ipc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "MainActivity1"

        val cat = intent.extras?.get("cat") as Cat//把Bundle用打开标签cat,然后猫到手
        Log.e("MainActivity1", ": " + cat.name)//MainActivity1可以对猫为所欲为,IPC 通信完成



        to_one.setOnClickListener {
            startActivity(Intent(this, MainActivity0::class.java))
        }

        to_two.setOnClickListener {
            startActivity(Intent(this, MainActivity1::class.java))
        }
        to_three.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }
}
