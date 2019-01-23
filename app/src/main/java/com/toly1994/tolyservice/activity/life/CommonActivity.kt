package com.toly1994.tolyservice.activity.life

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.LinearLayout

class CommonActivity : AppCompatActivity() {

    override//Activity第一次创建时调用
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LinearLayout(this))
        Log.e(TAG, "BrActivity--onCreate: ")
        title = "BrActivity"

    }


    override//Activity可见的时候调用
    fun onStart() {
        super.onStart()
        Log.e(TAG, "BrActivity--onStart: ")

    }

    override//Activity位于栈顶，获取焦点时调用
    fun onResume() {
        super.onResume()
        Log.e(TAG, "BrActivity--onResume: ")
    }


    override//Activity移除栈顶，失去焦点时调用
    fun onPause() {
        super.onPause()
        Log.e(TAG, "BrActivity--onPause: ")
    }

    override//由停止状态变为运行状态之前调用
    fun onRestart() {
        super.onRestart()
        Log.e(TAG, "BrActivity--onRestart: ")
    }

    override//Activity不可见的时候调用
    fun onStop() {
        super.onStop()
        Log.e(TAG, "BrActivity--onStop: ")
    }

    override//Activity销毁的时候调用
    fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "BrActivity--onDestroy: ")
    }


    companion object {
        private val TAG = "LifeCycleActivity"
    }
}
