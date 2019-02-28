package com.toly1994.tolyservice.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import com.toly1994.tolyservice.provider.bean.ContactBean;
import com.toly1994.tolyservice.provider.bean.SMSBean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/27/027:19:50<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ProviderData {
    /**
     * 获取联系人：ContactBean字段：name姓名  address地址  email邮箱 phone手机号
     *
     * @param ctx 上下文
     * @return ContactBean集合
     */
    public static List<ContactBean> getContact(Context ctx) {
        //创建一个容器放结果
        List<ContactBean> contactBeans = new ArrayList<>();
        //[1.]获得ContentResolver对象
        ContentResolver resolver = ctx.getContentResolver();
        //[2.1]得到Uri :访问raw_contacts的url
        Uri raw_contactsUri = Uri.parse("content://com.android.contacts/raw_contacts");
        //[2.2]得到Uri ://访问data的url
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        //[3]查询表，获得raw_contact表游标结果集
        Cursor raw_contactsCursor = resolver.query(
                raw_contactsUri, new String[]{"contact_id"}, null, null, null);
        //[4]遍历游标，获取数据，储存在bean中
        while (raw_contactsCursor.moveToNext()) {
            //[4.1]查询到contact_id
            String contact_id = raw_contactsCursor.getString(0);
            if (contact_id != null) {
                //[4.2]查询表，获得data表游标结果集
                Cursor dataCursor = resolver.query(dataUri,
                        new String[]{"data1", "mimetype"},//注意不是mimetype_id
                        "raw_contact_id=?",
                        new String[]{contact_id}, null);
                ContactBean contactBean = new ContactBean();
                while (dataCursor.moveToNext()) {
                    String result = dataCursor.getString(0);
                    //[4.4]根据实体类判断数据，放入实体类中
                    String mimetype = dataCursor.getString(1);
                    if (mimetype != null) {
                        //[4.3]新建实体类
                        switch (mimetype) {
                            case "vnd.android.cursor.item/phone_v2"://手机号
                                contactBean.setPhone(result);
                                break;
                            case "vnd.android.cursor.item/email_v2"://email
                                contactBean.setEmail(result);
                                break;
                            case "vnd.android.cursor.item/name"://姓名
                                contactBean.setName(result);
                                break;
                            case "vnd.android.cursor.item/postal-address_v2"://地址
                                contactBean.setAddress(result);
                                break;
                        }
                    }
                }
                if (contactBean.getPhone() != null) {
                    contactBeans.add(contactBean);//加入集合
                }
                //[5.1]关闭data表Cursor
                dataCursor.close();

            }
        }
        //[5.2]关闭raw_contacts表Cursor
        raw_contactsCursor.close();
        return contactBeans;
    }

    /**
     * 根据号码获得联系人头像
     *
     * @param ctx    上下文
     * @param number 号码
     * @return 图片
     */
    public static Bitmap getContactPhoto(Context ctx, String number) {
        Bitmap bmpHead = null;
        ContentResolver resolver = ctx.getContentResolver();//获得ContentResolver对象
        // 获得Uri
        Uri uriNumber2Contacts = Uri.parse("content://com.android.contacts/"
                + "data/phones/filter/" + number);
        // 查询Uri，返回数据集
        Cursor cursorCantacts = resolver.query(uriNumber2Contacts, null, null, null, null);
        // 如果该联系人存在
        if (cursorCantacts.getCount() > 0) {
            // 移动到第一条数据
            cursorCantacts.moveToFirst();
            // 获得该联系人的contact_id
            Long contactID = cursorCantacts.getLong(cursorCantacts.getColumnIndex("contact_id"));
            // 获得contact_id的Uri
            Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactID);
            // 打开头像图片的InputStream
            InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);
            // 从InputStream获得bitmap
            bmpHead = BitmapFactory.decodeStream(input);
        }
        return bmpHead;
    }


    /**
     * 获取短信：SMSBean：address发信人  date时间  body信息内容
     *
     * @param ctx 上下文
     * @return 短信bean集合 注意添加读取短信权限
     */
    public static List<SMSBean> getSMS(Context ctx) {
        List<SMSBean> smsBeans = new ArrayList<>();
        //[1.]获得ContentResolver对象
        ContentResolver resolver = ctx.getContentResolver();
        //[2.1]得到Uri :访问raw_contacts的url
        Uri uri = Uri.parse("content://sms");
        //[3]查询表，获得sms表游标结果集
        String[] projection = {"address", "date", "body", "type", "person", "thread_id"};//访问表的字段
        Cursor cursor = resolver.query(
                uri, projection, null, null, null);
        while (cursor.moveToNext()) {//遍历游标，获取数据，储存在bean中
            SMSBean smsBean = new SMSBean();
            smsBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            smsBean.setDate(cursor.getString(cursor.getColumnIndex("date")));
            smsBean.setBody(cursor.getString(cursor.getColumnIndex("body")));
            smsBean.setType(cursor.getInt(cursor.getColumnIndex("type")));
            smsBean.setName(cursor.getString(cursor.getColumnIndex("person")));
            smsBean.setThread_id(cursor.getInt(cursor.getColumnIndex("thread_id")));
            smsBeans.add(smsBean);
        }
        //[4] 关闭cursor
        cursor.close();
        return smsBeans;
    }
    //---------------------

    /**
     * 获取短信：SMSBean：address发信人  date时间  body信息内容
     *
     * @param ctx 上下文
     * @return 短信bean集合 注意添加读取短信权限
     */
    public static List<SMSBean> getSMSByPhone(Context ctx, String phone) {
        List<SMSBean> smsBeans = new ArrayList<>();
        //[1.]获得ContentResolver对象
        ContentResolver resolver = ctx.getContentResolver();
        //[2.1]得到Uri :访问raw_contacts的url
        Uri uri = Uri.parse("content://sms");
        //[3]查询表，获得sms表游标结果集
        String[] projection = {"address", "date", "body", "type", "person", "thread_id"};//访问表的字段
        Cursor cursor = resolver.query(
                uri, projection, "address=?", new String[]{phone}, "date desc  limit 0,8");
        while (cursor.moveToNext()) {//遍历游标，获取数据，储存在bean中
            SMSBean smsBean = new SMSBean();
            smsBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            smsBean.setDate(cursor.getString(cursor.getColumnIndex("date")));
            smsBean.setBody(cursor.getString(cursor.getColumnIndex("body")));
            smsBean.setType(cursor.getInt(cursor.getColumnIndex("type")));
            smsBean.setName(cursor.getString(cursor.getColumnIndex("person")));
            smsBean.setThread_id(cursor.getInt(cursor.getColumnIndex("thread_id")));
            smsBeans.add(smsBean);
        }
        //[4] 关闭cursor
        cursor.close();
        return smsBeans;
    }

}
