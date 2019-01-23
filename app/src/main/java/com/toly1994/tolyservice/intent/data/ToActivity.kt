package com.toly1994.tolyservice.intent.data

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.toly1994.tolyservice.R
import com.toly1994.tolyservice.bean.Book
import com.toly1994.tolyservice.bean.Person
import kotlinx.android.synthetic.main.ac_to.*


class ToActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_to)
        id_btn_send.visibility= View.INVISIBLE


        this.title = "ToActivity"

        var result = ""
        val stringData = intent.getStringExtra("stringData")
        val intData = intent.getIntExtra("intData", 10)
        val arrData = intent.getIntegerArrayListExtra("arrData")
        result+=intData.toString()+"\n"
        if (stringData != null) {
            result+=stringData+"\n"
        }
        if (arrData != null) {
            result+=arrData.toString()+"\n"
        }

        val bundle = intent.getBundleExtra("bean")
        if (bundle != null) {
            val personBean = bundle.get("person") as Person
            val bookBean = bundle.get("book") as Book
            result += personBean.toString()+"\n"
            result += bookBean.toString()+"\n"
//            val icon = extra.get("bitmap") as Bitmap
//            id_iv_icon.setImageBitmap(icon)
        }

        id_tv_result.append(result)

    }

}
