package com.toly1994.ipc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream

class MainActivity0 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "MainActivity0"




        to_one.setOnClickListener {
            startActivity(Intent(this, MainActivity0::class.java))
        }
        to_two.setOnClickListener {
            val cat = CatManager().getCatAt(0)
            val bundle = Bundle()//创建Bundle对象
            bundle.putParcelable("cat", cat)//把猫装到Bundle里，贴个标签cat

            val intent = Intent(this, MainActivity1::class.java)
            intent.putExtras(bundle)

            startActivity(intent)
        }
        to_three.setOnClickListener {
            val cat = CatManager().getCatAt(0)
            val file = File(cacheDir, "cat.obj")
            val oos = ObjectOutputStream(FileOutputStream(file))
            oos.writeObject(cat)
            oos.close()

            startActivity(Intent(this, MainActivity2::class.java))
        }
    }
}
