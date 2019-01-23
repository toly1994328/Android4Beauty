package com.toly1994.tolyservice.intent

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.toly1994.tolyservice.app.Compat
import com.toly1994.tolyservice.R
import com.toly1994.tolyservice.permission.Permission
import com.toly1994.tolyservice.permission.PermissionActivity
import com.toly1994.tolyservice.service.MusicActivity
import kotlinx.android.synthetic.main.activity_intent_test.*
import java.io.File

/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/18/018:7:59<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：
 */
class IntentActivity : PermissionActivity() {
    override fun permissionOk() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_test)
        applyPermissions(Permission._WRITE_EXTERNAL_STORAGE)

        id_btn_send_sms.setOnClickListener { v ->
            var number = "10086"
            var body = "2333"

            sendMsg(number, body)
        }

        id_btn_photo.setOnClickListener { v ->
            openGallery()
        }

        id_btn_comp.setOnClickListener { v ->
            openComponent()
        }

        id_tv_weichat.setOnClickListener { v ->
            openWeiChat()
        }

        id_btn_just_action.setOnClickListener { v ->
            val intent = Intent("www.toly1994.com.ActivityJustAction")
            intent.addCategory("www.toly1994.com.coder")
            startActivity(intent)
        }


        id_btn_open_web.setOnClickListener { v ->
            val intent = Intent(Intent.ACTION_VIEW)
            val uri = Uri.parse("https://juejin.im/user/5b42c0656fb9a04fe727eb37")
            intent.data = uri

            val intentCopy = Intent(intent)


            startActivity(intentCopy)
        }

        id_btn_music.setOnClickListener { v ->
            //音频
            val intent = Intent(Intent.ACTION_VIEW)
            val file = File("/sdcard/toly/勇气-梁静茹-1772728608-1.mp3")
            Compat.fileUri(this, intent, file, "audio/mp3")
            startActivity(intent)
        }

        id_btn_video.setOnClickListener { v ->
            //视频
            val intent = Intent(Intent.ACTION_VIEW)
            val file = File("/sdcard/toly/cy3d.mp4")
            Compat.fileUri(this, intent, file, "video/mp4")
            startActivity(intent)
        }

        id_btn_txt.setOnClickListener { v ->
            //文本
            val intent = Intent(Intent.ACTION_VIEW)
            val file = File("/sdcard/toly/应龙.txt")
            Compat.fileUri(this, intent, file, "text/*")



            startActivity(intent)
        }

        id_btn_pic.setOnClickListener { v ->
            //图片
            val intent = Intent(Intent.ACTION_VIEW)
            val file = File("/sdcard/toly/touch.jpg.png")
            Compat.fileUri(this, intent, file, "image/*")
            startActivity(intent)
        }

    }

    /**
     * 打开微信
     */
    private fun openWeiChat() {
        val intentOpenWeiChat = Intent()
        intentOpenWeiChat.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val weiChatComp = ComponentName(
            "com.tencent.mm",//本组件的包名
            "com.tencent.mm.ui.LauncherUI"
        )//要打开的组件全类名
        intentOpenWeiChat.component = weiChatComp
        startActivity(intentOpenWeiChat)
    }

    /**
     * 打开组件
     */
    private fun openComponent() {
        val intentOpenActivity = Intent(this, MusicActivity::class.java)

//        val compName = ComponentName(
//            "com.toly1994.tolyservice",//本组件的包名
//            "com.toly1994.tolyservice.service.MusicActivity")//要打开的组件全类名
        startActivity(intentOpenActivity)
    }

    /**
     * 打开图库
     */
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*";
        startActivity(intent)
    }

    /**
     * 发送短信
     * @param number 号码
     * @param body 内容
     */
    private fun sendMsg(number: String, body: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$number"))
        intent.putExtra("sms_body", body)

        val clone = intent.clone()
        startActivity(clone as Intent?)
    }
}
