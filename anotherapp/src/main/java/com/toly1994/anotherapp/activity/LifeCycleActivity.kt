package com.toly1994.anotherapp.activity

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.toly1994.anotherapp.R
import com.toly1994.anotherapp.Utils
import com.toly1994.anotherapp.fragment.BoxFragment
import com.toly1994.anotherapp.itf.IBoxSender
import kotlinx.android.synthetic.main.activity_fragment.*

class LifeCycleActivity : AppCompatActivity(), IBoxSender {

    override//Activity第一次创建时调用
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        Log.e(TAG, "LifeCycleActivity--onCreate: ")
        title = "LifeCycleActivity"

        if (savedInstanceState != null) {
            title = savedInstanceState.getString("name")
        }
        initFragment()


        fl_title.setOnClickListener {
            update(Utils.randomColor())
        }
    }

    override fun update(color: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_title, BoxFragment.newInstance(color,""))
            .commit()
    }


    /**
     * 动态加载Fragment
     */
    private fun initFragment() {
//        val bundle = Bundle()//创建Bundle对象
//        bundle.putString("color", );//为bundle赋值
//        val boxFragment = BoxFragment()
//        boxFragment.arguments = bundle

        var boxFragment = BoxFragment.newInstance("#238AF8","")
        var radFragment = BoxFragment.newInstance("#ff0000","")


//        radFragment.setmOnDataSend {
//            id_tv_result.text = it
//        }

        //3.动态添加 （控件id,fragment对象）
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fl_title, boxFragment)
            .add(R.id.fl_content, radFragment)
            .commit()//4.提交事务
    }

    override fun setData(s: String) {
        id_tv_result.text = s
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.e(TAG, "LifeCycleActivity--onSaveInstanceState: ")
        outState?.putString("name", "toly")
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
