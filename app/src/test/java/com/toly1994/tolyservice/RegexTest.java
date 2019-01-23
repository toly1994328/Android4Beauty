package com.toly1994.tolyservice;

import org.junit.Test;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/1/18/018:9:17<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class RegexTest {

    @Test
    public void match() {
        String str1 = "package android.os;";
        String str2 = "int countOfpackage = 1;";
        String line = "hello , my Name is toly";
        String class1 = " Log.w(TAG, \"The following Handler class should be static or leaks might occur: \" +";
        String class2 = "public class Activity extends ContextThemeWrapper";
        String class3 = "public class Handler {";
        String class4 = "public int classId= 1";




//        System.out.println("str1:"+str1.contains("package"));//str1:true
//        System.out.println("str2:"+str2.contains("package"));//str2:true
//
//        String regex = "\\b?package\\b.*";
//        System.out.println("str1:" + str1.matches(regex));//str1:true
//        System.out.println("str2:" + str2.matches(regex));//str2:false

        String nextWord = getNextWordBy(line, ",");

//        System.out.println(nextWord);

String method = "private void maybePrefillHasFds() {";
String method2 = "private String maybe=0;";
String method3 = "public String maybe=new String();";
String regex = "(\\b?(private|public|protecte).*\\(.*)\\{";
//System.out.println("method:" + method.matches(regex));//str1:true
//System.out.println("method:" + method3.matches(regex));//str1:true

        String regexClass = ".*class [A-Z].*";
        System.out.println("method:" + class2.matches(regexClass));//str1:true

    }


    /**
     * 获取下一个单词(//TODO 适用：单词必须一个空格隔开)
     *
     * @param line   字符串
     * @param target 目标字符串
     * @return 下一个单词
     */
    private String getNextWordBy(String line, String target) {
        if (!line.contains(target + " ") || line.endsWith(target)) {
            return "NO FOUND";
        }
        return line.split(target + " ")[1].split(" ")[0];
    }
}














































