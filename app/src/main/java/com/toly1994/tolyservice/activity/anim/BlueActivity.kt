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
 * 说明：绿色Activity
 */
class BlueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = View(this)
        view.setBackgroundColor(Color.BLUE)
        title = "BlueActivity"
        view.setOnClickListener { v ->
            startActivity(Intent(this, RedActivity::class.java))
//            overridePendingTransition(R.anim.close_enter, R.anim.close_exit)
        }
        setContentView(view)
    }

    override fun onBackPressed() {
        super.onBackPressed()//右移入---右移出
//                overridePendingTransition(R.anim.close_enter, R.anim.close_exit)
    }
}
