package com.toly1994.tolyservice.provider.bean

/**
 * 作者：张风捷特烈
 * 时间：2018/4/12:16:46
 * 邮箱：1981462002@qq.com
 * 说明：短信实体类
 */
class SMSBean {
    /**
     * 短信发送方
     */
    var address: String? = null

    /**
     * 号码在通讯录中的姓名：无为null
     */
    var name: String? = null
    /**
     * 短信时间
     */
    var date: String? = null
    /**
     * 短信内容
     */
    var body: String? = null
    /**
     * 1 接收短信 2 发送短信
     */
    var type: Int = 0

    /**
     * 同一个手机号互发的短信，其序号是相同的
     */
    var thread_id: Int = 0
}