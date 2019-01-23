package com.toly1994.tolyservice;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/1/18/018:10:16<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：数数bean
 */
public class CountBean {
    public int lineCount;//源码行数

    public int annoLineCount;//注释行数
    public int blankLineCount;//空格行数

    public int realLineCount;//真实源码行数---去除注释和空行

    public int methodCount;//方法个数
    public int attrCount;//成员字段个数
}
