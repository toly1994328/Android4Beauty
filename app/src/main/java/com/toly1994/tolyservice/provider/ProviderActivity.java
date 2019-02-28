package com.toly1994.tolyservice.provider;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.toly1994.tolyservice.R;
import com.toly1994.tolyservice.permission.Permission;
import com.toly1994.tolyservice.permission.PermissionActivity;
import com.toly1994.tolyservice.provider.adapter.ChatRvAdapter;
import com.toly1994.tolyservice.provider.bean.ContactBean;
import com.toly1994.tolyservice.provider.bean.FolderBean;
import com.toly1994.tolyservice.provider.bean.SMSBean;
import com.toly1994.tolyservice.provider.swordProvider.SwordDatabaseHelper;

import java.util.List;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/27/027:19:52<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ProviderActivity extends PermissionActivity {
    private static final String TAG = "ProviderActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_cp);
        applyPermissions(Permission._READ_CONTACTS);

    }

    @Override
    protected void permissionOk() {

        SwordDatabaseHelper helper = new SwordDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
//        db.execSQL("INSERT INTO sword(name,atk,user) VALUES" +
//                "('绝世好剑',7000,'步惊云')");

//        db.execSQL("UPDATE sword SET atk=3500 WHERE id=1");

//        Cursor cursor = db.rawQuery("SELECT * FROM sword", null);
//        while (cursor.moveToNext()) {
//            String id = cursor.getString(cursor.getColumnIndex("id"));
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String atk = cursor.getString(cursor.getColumnIndex("atk"));
//            String user = cursor.getString(cursor.getColumnIndex("user"));
//            Log.e(TAG, "rawQuery: "+id + "---" + name + "---" + atk + "---" + user );
//            //rawQuery: 1---绝世好剑---3500---步惊云
//        }
//        cursor.close();//关闭游标

        db.execSQL("DELETE FROM sword WHERE id=1");

        List<ContactBean> contact = ProviderData.getContact(this);
        List<SMSBean> sms = ProviderData.getSMSByPhone(this, "10086012");

        RecyclerView mIdRvContent = findViewById(R.id.id_rv_cp);

        ChatRvAdapter adapter = new ChatRvAdapter(sms);
        mIdRvContent.setAdapter(adapter);
        mIdRvContent.setLayoutManager(new LinearLayoutManager(this));

        List<FolderBean> imagePaths = PictureLoader.getAllImagePath(this);
        FolderBean folderBean = imagePaths.get(0);

    }
}
