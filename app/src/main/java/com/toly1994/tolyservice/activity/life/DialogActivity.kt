package com.toly1994.tolyservice.activity.life

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.toly1994.tolyservice.R


class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_dialog)
        title = "DialogActivity"
        Log.e(TAG, "DialogActivity--onCreate: ")
    }

    override//Activity可见的时候调用
    fun onStart() {
        super.onStart()
        Log.e(TAG, "DialogActivity--onStart: ")

    }

    override//Activity位于栈顶，获取焦点时调用
    fun onResume() {
        super.onResume()
        Log.e(TAG, "DialogActivity--onResume: ")
    }


    override//Activity移除栈顶，失去焦点时调用
    fun onPause() {
        super.onPause()
        Log.e(TAG, "DialogActivity--onPause: ")
    }

    override//由停止状态变为运行状态之前调用
    fun onRestart() {
        super.onRestart()
        Log.e(TAG, "DialogActivity--onRestart: ")
    }

    override//Activity不可见的时候调用
    fun onStop() {
        super.onStop()
        Log.e(TAG, "DialogActivity--onStop: ")
    }

    override//Activity销毁的时候调用
    fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "DialogActivity--onDestroy: ")
    }


    companion object {
        private val TAG = "LifeCycleActivity"
    }

}
