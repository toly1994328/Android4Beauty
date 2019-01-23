package com.toly1994.tolyservice.activity.anim

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/20/020:18:25<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：红色Activity
 */
class RedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = View(this)
        view.setBackgroundColor(Color.RED)
        title = "RedActivity"
        view.setOnClickListener { v ->
            startActivity(Intent(this, BlueActivity::class.java))
//            overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
        }
        setContentView(view)
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }
}
