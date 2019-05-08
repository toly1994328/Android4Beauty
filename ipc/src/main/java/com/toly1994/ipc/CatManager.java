package com.toly1994.ipc;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/5/8/008:9:56<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class CatManager {
    private static List<Cat> cats = new ArrayList<>();

    public CatManager() {
        Cat tutu = new Cat();
        tutu.color = "灰色" + Math.random();
        tutu.name = "土土";
        add(tutu);
    }


    public void add(Cat cat) {
        cats.add(cat);
    }

    public Cat getCatAt(int index) {
        return cats.get(index);
    }
}
