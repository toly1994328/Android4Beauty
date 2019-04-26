package com.toly1994.anotherapp;

import java.util.Random;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/26/026:19:56<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class Utils {
    public static String randomColor() {
        String[] range = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

        Random random = new Random();

        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(range.length - 1);
            sb.append(range[index]);
        }
        return sb.toString();
    }
}
