package com.toly1994.anotherapp.model;

import android.arch.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/28/028:20:58<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：注册 绑定 Observer  --> 注册  Observer  绑定
 *
 * setValue
 * dispatchingValue
 * considerNotify
 * observer.mObserver.onChanged((T) mData);
 *
 * mObservers
 */
public class DataFactory {
    private static DataFactory sDataFactory;
    private final Map<String, MutableLiveData<Object>> bus = new HashMap<>();

    private DataFactory() {
    }

    public static DataFactory newInstance() {
        if (sDataFactory == null) {
            synchronized (DataFactory.class) {
                if (sDataFactory == null) {
                    sDataFactory = new DataFactory();
                }
            }
        }
        return sDataFactory;
    }

    public <T> MutableLiveData<T> with(String key, Class<T> clazz) {
        if (bus.containsKey(key)) {
            bus.put(key, new MutableLiveData<>());
        }

        return (MutableLiveData<T>) bus.get(key);
    }

    public <T> MutableLiveData<Object> with(String key) {

        return with(key,Object.class);
    }

}
