package com.toly1994.tolyservice.activity.data

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import com.toly1994.tolyservice.R
import com.toly1994.tolyservice.bean.Person
import kotlinx.android.synthetic.main.ac_to.*


class ToActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_to)
        this.title = "ToActivity"
        val title = intent.getStringExtra("title")
        if (title != null) {
            this.title = title
        }
        val extra = intent.getBundleExtra("from")
        if (extra != null) {
            val from = extra.get("person") as Person
            val icon = extra.get("bitmap") as Bitmap
            id_tv_result.text = from.toString()
            id_iv_icon.setImageBitmap(icon)
        }

        id_btn_send.setOnClickListener {
            backWithData()
            finish()
        }

        id_iv_icon.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*";
            startActivityForResult(intent, 0)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {//成功
            val selectedImage = data?.data ?: return
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = contentResolver.query(// 获取选择照片的数据视图
                selectedImage, filePathColumn, null, null, null
            )
            cursor.moveToFirst()
            // 从数据视图中获取已选择图片的路径
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val picturePath = cursor.getString(columnIndex)
            cursor.close()
            id_iv_icon.setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }
    }


    private fun backWithData() {
        val jt = Person("捷特", 24)
        val intent = Intent()
        intent.putExtra("data", "我是ToActivity的数据")
        val bundle = Bundle()
        bundle.putSerializable("person", jt)
        intent.putExtra("To", bundle)
        setResult(Activity.RESULT_OK, intent)
    }

    /**
     * 重写返回键
     */
    override fun onBackPressed() {
        backWithData()
        super.onBackPressed()
    }
}
