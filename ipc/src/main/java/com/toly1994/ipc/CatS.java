package com.toly1994.ipc;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:16:02<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class CatS  implements Serializable {
    public String name;
    public String color;

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}