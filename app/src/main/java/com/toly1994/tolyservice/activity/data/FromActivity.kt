package com.toly1994.tolyservice.activity.data

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.toly1994.tolyservice.R
import com.toly1994.tolyservice.bean.Person
import kotlinx.android.synthetic.main.ac_from.*


class FromActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_from)
        title = "FromActivity"

        id_btn_for_result.setOnClickListener {
            val intent = Intent(this, ToActivity::class.java)
//            val bundle = Bundle()
//            bundle.putSerializable("person", Person("form", 24))
//            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.wall_a)
//            bundle.putParcelable("bitmap", bitmap)
//            intent.putExtra("from", bundle)
//            intent.putExtra("title", "张风捷特烈")
            startActivityForResult(intent, DATA_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            DATA_CODE -> if (resultCode == Activity.RESULT_OK) {
                val dataFormTarget = data.getStringExtra("data")

                val personData = data.getBundleExtra("To")
                val person = personData.get("person") as Person
                id_tv_result_back.text = ("dataFormTarget:" + dataFormTarget
                        + "\nperson:" + person.toString())
            }
        }
    }

    companion object {
        private const val DATA_CODE = 0x0001
    }
}
