package com.toly1994.anotherapp.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/28/028:20:26<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class NameViewModel extends LiveData<String> {

    private MutableLiveData<String> mNameEvent;

    public MutableLiveData<String> getNameEvent() {
        if (mNameEvent == null) {
            mNameEvent = new MutableLiveData<>();
        }
        return mNameEvent;
    }
}
