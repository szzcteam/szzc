package com.me.szzc.enums;

/**
 * 文件后缀枚举（后缀大写）
 * 注意：postfix第一个字符不能是点，多个后缀以点号分割
 * Created by luwei on 17-6-2.
 */
public enum FilePostfixEnum {

    IMG("img", "PNG.JPG.JPEG.BMP"),
    IMG2("img", "PNG.JPG"),
    IMG3("img", "PNG.JPG.GIF");


    private String name;

    private String postfix;

    FilePostfixEnum(String name, String postfix) {
        this.name = name;
        this.postfix = postfix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
}
