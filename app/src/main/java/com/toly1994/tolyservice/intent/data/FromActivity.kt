package com.toly1994.tolyservice.intent.data

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.toly1994.tolyservice.R
import com.toly1994.tolyservice.bean.Book
import com.toly1994.tolyservice.bean.Person
import kotlinx.android.synthetic.main.ac_from.*
import java.io.*


class FromActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_from)
        id_tv_result_back.visibility = View.GONE

        title = "FromActivity"
        id_btn_for_result.text = "打开ToActivity"
        id_btn_for_result.setOnClickListener {
            val intent = Intent(this, ToActivity::class.java)
            //String类型数据
            intent.putExtra("stringData", "张风捷特烈")
            //int类型数据
            intent.putExtra("intData", 100)
            //容器类型数据
            val arr = arrayListOf(1, 2, 3, 4, 5)
            intent.putExtra("arrData", arr)

            val bundle = Bundle()
            //存放Serializable序列化对象
            val toly = Person("toly", 24)
            bundle.putSerializable("person", toly)
            //存放Parcelable序列化对象
            val book = Book("《幻将录》", 10000)
            bundle.putParcelable("book", book)

//            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.wall_a)
//            bundle.putParcelable("bitmap", bitmap)

            intent.putExtra("bean", bundle)
//            osTest()
            startActivity(intent)
        }
    }

    private fun osTest() {
        val toly = Person("toly", 24)

        val file = File(cacheDir, "toly.obj")
//        val file = File(cacheDir, "toly_Transient.obj")
        val oos = ObjectOutputStream(FileOutputStream(file))
        oos.writeObject(toly)
        oos.close()

        val ois = ObjectInputStream(FileInputStream(file))
        val tolyis = ois.readObject() as Person
    }

}
