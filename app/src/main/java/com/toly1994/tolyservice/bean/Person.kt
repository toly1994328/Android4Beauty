package com.toly1994.tolyservice.bean

import java.io.Serializable

/**
 * 作者：张风捷特烈
 * 时间：2018/4/26:12:13
 * 邮箱：1981462002@qq.com
 * 说明：简单实体Person
 */


class Person(@Transient var name: String?, var age: Int) : Serializable {

    override fun toString(): String {
        return "Person{" +
                "name='" + name + '\''.toString() +
                ", age=" + age +
                '}'.toString()
    }
}
