package com.toly1994.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:9:57<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class Cat implements Serializable, Parcelable {
    public String name;
    public String color;

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.color);
    }

    public Cat() {
    }

    protected Cat(Parcel in) {
        this.name = in.readString();
        this.color = in.readString();
    }

    public static final Parcelable.Creator<Cat> CREATOR = new Parcelable.Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel source) {
            return new Cat(source);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };
}
