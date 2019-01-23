package com.toly1994.tolyservice;

import java.util.List;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/1/18/018:8:30<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：源码对象
 */
public class SourceBean {
    public List<String> innerClassName;

    public String pkgName;//包名
    public List<String> importClass;//导入的类


    public ClassBean classBean;//类的基本信息


    public CountBean countBean;//数目对象
    public List<String> attrs;//成员变量集合

    public List<String> methods;//方法名

    public int getPublicMethodCount() {
        int result = 0;
        for (String method : methods) {
            if (method.contains("public")) {
                result++;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "类名:" + classBean.name + "      父类:" + classBean.fatherName +  "      修饰:" + classBean.perFix + "\n" +
                "实现的接口:" + classBean.itfNames.toString() + "\n" +
                "包名:" + pkgName + "   依赖类个数:" + importClass.size() + "\n" +
                "内部类/接口个数：" + (innerClassName.size() - 1) + "\n" +
                "源码行数：" + countBean.lineCount + "       源码行数(除注释):" + countBean.realLineCount + "\n" +
                "属性个数：" + countBean.attrCount + "       方法个数:" + countBean.methodCount + "       public方法个数:" + getPublicMethodCount();
    }
}
