package com.me.szzc.utils.fileUpload.facade.response;

/**
 * @author luwei
 * @Date 11/9/16 3:35 PM
 */
public class FileUploadResult {

    /**
     * 绝对地址,如果文件所在的bucket是私有的，则不可直接存储，仅显示预览。实际存储用relativeUrl
     */
    private String absoluteUrl;

    /**
     * 相对地址,如果文件所在的bucket是公共读，则可直接存储，并访问
     */
    private String relativeUrl;


    public String getAbsoluteUrl() {
        return absoluteUrl;
    }

    public void setAbsoluteUrl(String absoluteUrl) {
        this.absoluteUrl = absoluteUrl;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    public void setRelativeUrl(String relativeUrl) {
        this.relativeUrl = relativeUrl;
    }
}
