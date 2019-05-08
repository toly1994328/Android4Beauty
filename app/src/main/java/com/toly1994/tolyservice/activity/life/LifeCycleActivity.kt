package com.toly1994.tolyservice.activity.life

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.toly1994.tolyservice.R
import kotlinx.android.synthetic.main.ac_lifecycle.*

class LifeCycleActivity : AppCompatActivity() {

    override//Activity第一次创建时调用
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_lifecycle)
        Log.e(TAG, "LifeCycleActivity--onCreate: ")
        title = "LifeCycleActivity"

        if (savedInstanceState != null) {
            title = savedInstanceState.getString("name")
        }
        initEvent()
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.e(TAG, "LifeCycleActivity--onSaveInstanceState: ")
        outState?.putString("name", "toly")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e(TAG, "LifeCycleActivity--onRestoreInstanceState: ")
    }
    private fun initEvent() {
        id_btn_task_activity.setOnClickListener {
            startActivity(Intent(this, CommonActivity::class.java))
        }

        id_btn_dialog.setOnClickListener {
            startActivity(Intent(this, DialogActivity::class.java))
        }

    }

    override//Activity可见的时候调用
    fun onStart() {
        super.onStart()
        Log.e(TAG, "LifeCycleActivity--onStart: ")

    }

    override//Activity位于栈顶，获取焦点时调用
    fun onResume() {
        super.onResume()
        Log.e(TAG, "LifeCycleActivity--onResume: ")
    }


    override//Activity移除栈顶，失去焦点时调用
    fun onPause() {
        super.onPause()
        Log.e(TAG, "LifeCycleActivity--onPause: ")
    }

    override//由停止状态变为运行状态之前调用
    fun onRestart() {
        super.onRestart()
        Log.e(TAG, "LifeCycleActivity--onRestart: ")
    }

    override//Activity不可见的时候调用
    fun onStop() {
        super.onStop()
        Log.e(TAG, "LifeCycleActivity--onStop: ")
    }

    override//Activity销毁的时候调用
    fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "LifeCycleActivity--onDestroy: ")
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Log.e(TAG, "LifeCycleActivity--onConfigurationChanged: " + resources.configuration.orientation)
    }


    companion object {
        private val TAG = "LifeCycleActivity"
    }
}
