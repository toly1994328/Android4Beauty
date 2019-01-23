package com.toly1994.tolyservice.activity.task

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.toly1994.tolyservice.R
import kotlinx.android.synthetic.main.ac_task.*

/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/19/019:15:57<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：
 */
class Activity1 : AppCompatActivity() {
    companion object {
        private val TAG = "ActivityTASK"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_task)
        title = "Activity1"
        Log.e(TAG, "Activity1 Task id is: $taskId")

        id_btn_activity1.setOnClickListener {
            startActivity(Intent(this, Activity1::class.java))
        }
        id_btn_activity2.setOnClickListener {
            startActivity(Intent(this, Activity2::class.java))

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "Activity1 销毁")
    }
}
