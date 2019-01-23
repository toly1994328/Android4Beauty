package com.toly1994.tolyservice.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/1/21/021:22:30<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：Parcelable序列化
 */
public class Book implements Parcelable {

    private String name;
    private int price;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.price);
    }

    protected Book(Parcel in) {
        this.name = in.readString();
        this.price = in.readInt();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

