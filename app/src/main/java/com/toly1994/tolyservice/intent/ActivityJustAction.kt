package com.toly1994.tolyservice.intent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout


class ActivityJustAction : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LinearLayout(this))
        title = "ActivityJustAction"
    }
}
