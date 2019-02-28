package com.toly1994.tolyservice.provider.bean;

/**
 * 作者：张风捷特烈
 * 时间：2018/4/18:11:36
 * 邮箱：1981462002@qq.com
 * 说明：文件夹实体类
 */
public class FolderBean {

    /**
     * 当前文件夹路径
     */
    private String dir;

    /**
     * 当前文件夹第一个照片的路径
     */
    private String firstImgPath;
    /**
     *
     */
    private String name;
    /**
     * 当前文件夹内图片数量
     */
    private int count;


    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
        String[] names = this.dir.split("/");
        this.name = names[names.length - 1];
    }

    public String getFirstImgPath() {
        return firstImgPath;
    }

    public void setFirstImgPath(String firstImgPath) {
        this.firstImgPath = firstImgPath;
    }

    public String getName() {
        return name;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}