package com.toly1994.ipc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "MainActivity2"

        val file = File(cacheDir, "cat.obj")
        val ois = ObjectInputStream(FileInputStream(file))
        val cat = ois.readObject() as Cat//反序列化生成对象
        ois.close()
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
