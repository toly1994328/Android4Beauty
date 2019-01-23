package com.toly1994.tolyservice;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/1/18/018:8:33<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class JavaSourceParser {

    private List<String> attrs;
    private int annoLineCount;//注释行数
    private int blankLineCount;//空白行数
    private int allCount;//全部行数
    private StringBuffer pureCodeSb;
    private StringBuffer codeSb;

    private boolean mainOk;

    @Test
    //android.content.Intent
    //android.net.Uri
    //android.app.Activity
    //android.app.Activity
    // android.content.BroadcastReceiver
    public void parse() throws IOException {
        String Activity = "H:\\sdk\\sources\\android-27\\android\\app\\Service.java";
        SourceBean read = read(Activity);
        System.out.println(read);
    }

    private final SourceBean mMainSource;

    public JavaSourceParser() {

        mMainSource = new SourceBean();
    }

    private SourceBean read(String name) throws IOException {

        File file = new File(name);
        BufferedReader br = new BufferedReader(new FileReader(file));

        codeSb = new StringBuffer();
        //无注释的代码
        pureCodeSb = new StringBuffer();


        //成员变量集合
        attrs = new ArrayList<>();

        String aLine;

        String packageRegx = "\\bpackage\\b.*";
        String importRegx = "\\bimport\\b.*";
        String pkgName = "";
        ArrayList<String> importClasses = new ArrayList<>();//导入类名列表
        ArrayList<String> sonClasses = new ArrayList<>();//导入类名列表

        while ((aLine = br.readLine()) != null) {
            codeSb.append(aLine + "\n");
            //处理数量
            allCount++;
            if (aLine.contains("*")) {
                annoLineCount++;
                continue;
            }
            if (aLine.equals("")) {
                blankLineCount++;
                continue;
            }
            pureCodeSb.append(aLine + "\n");
            //处理包名
            if (aLine.matches(packageRegx)) {
                pkgName = aLine.split("package ")[1].replace(";", "");
            }
            //处理导入包名
            if (aLine.matches(importRegx)) {
                String importClass = aLine.split("import ")[1].replace(";", "");
                importClasses.add(importClass);
            }
        }
        br.close();


        String code = pureCodeSb.toString();
        String classRegx = ".* (class|interface|enum) [A-Z].*";

        Pattern pattern = Pattern.compile(classRegx);
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            String aClass = matcher.group(0);
            sonClasses.add(aClass.replaceAll("\\{|( {2,})", ""));
        }


        SourceBean sourceBean = parseCode(pureCodeSb.toString(), mMainSource);
        mMainSource.pkgName = pkgName;
        mMainSource.importClass = importClasses;
        mMainSource.innerClassName = sonClasses;
        mMainSource.classBean.fullName = mMainSource.pkgName + "." + mMainSource.classBean.name;
        return sourceBean;
    }

    private SourceBean parseCode(String code, SourceBean sourceBean) {
        CountBean countBean = new CountBean();
        ClassBean classBean = new ClassBean();

        String classRegx = ".*class [A-Z].*";
        String methodRegex = "(.*(private|public|protecte).*\\(.*)\\{";

        ArrayList<String> methods = new ArrayList<>();

        String className = "";//类名
        String fatherName = "";//父类名
        String perFix = "";//前缀修饰
        ArrayList<String> itfNames = new ArrayList<>();//接口名

        String[] lines = code.split("\n");
        for (String line : lines) {

            //方法名的解析
            if (line.matches(methodRegex)) {
                String result = line.replaceAll("\\{|( {2,})", "");
                methods.add(result);
            }

            //处理类名、父类名、接口名
            if (line.matches(classRegx) && !mainOk) {
                perFix = line.split(" class ")[0];
                className = getNextWordBy(line, "class");//类名
                if (line.contains("extends")) {//父类名
                    fatherName = getNextWordBy(line, "extends");
                } else {
                    fatherName = "Object";
                }
                if (line.contains("implements")) {
                    String implementsStr = line.split("implements ")[1].split(" \\{")[0];

                    String[] split = implementsStr.replaceAll(" ", "").split(",");
                    itfNames.addAll(Arrays.asList(split));
                }
                mainOk = true;
            }
        }

//        System.out.println(pureCodeSb.toString());
        handleAttr(pureCodeSb.toString());//无注释的纯代码

        countBean.annoLineCount = annoLineCount;
        countBean.blankLineCount = blankLineCount;
        countBean.lineCount = allCount;
        countBean.realLineCount = allCount - blankLineCount - annoLineCount;
        countBean.attrCount = attrs.size();
        countBean.methodCount = methods.size();

        sourceBean.countBean = countBean;

        classBean.name = className;
        classBean.fatherName = fatherName;

        classBean.itfNames = itfNames;
        classBean.perFix = perFix;
        sourceBean.classBean = classBean;

        sourceBean.attrs = attrs;
        sourceBean.methods = methods;

        return sourceBean;
    }

    private void handleAttr(String code) {
        String attrDirty = code.split("\\{")[1];//脏乱的属性
        String[] split = attrDirty.split(";");
        for (int i = 0; i < split.length - 1; i++) {
            String result = split[i].replaceAll("\n|( {2,})", "-");
            attrs.add(result);
        }
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
