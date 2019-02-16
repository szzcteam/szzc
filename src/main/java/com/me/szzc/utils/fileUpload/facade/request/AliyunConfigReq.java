package com.me.szzc.utils.fileUpload.facade.request;

/**
 * @author luwei
 * @Date 12/2/16 11:34 AM
 */
public class AliyunConfigReq {


     public String folder = "";

     public String bucketName = "";

     //权限 0私有  1公共读   2公共读写
     public int acl = 0;

     //读取文件域
     public String fileLoadPath = "";

     //是否生成新的随机文件名
     public boolean createNewFileName;

     //图片处理样式
     private String style;

     public AliyunConfigReq(String bucketName) {
          this.bucketName = bucketName;
     }

     public AliyunConfigReq(String folder, String bucketName, int acl, String fileLoadPath, boolean createNewFileName) {
          this.folder = folder;
          this.bucketName = bucketName;
          this.acl = acl;
          this.fileLoadPath = fileLoadPath;
          this.createNewFileName = createNewFileName;
     }

     public AliyunConfigReq(String folder, String bucketName, int acl, String fileLoadPath, boolean createNewFileName, String style) {
          this.folder = folder;
          this.bucketName = bucketName;
          this.acl = acl;
          this.fileLoadPath = fileLoadPath;
          this.createNewFileName = createNewFileName;
          this.style = style;
     }

     /************* get()、set() ************************/


     public String getFolder() {
          return folder;
     }

     public void setFolder(String folder) {
          this.folder = folder;
     }

     public String getBucketName() {
          return bucketName;
     }

     public void setBucketName(String bucketName) {
          this.bucketName = bucketName;
     }

     public int getAcl() {
          return acl;
     }

     public void setAcl(int acl) {
          this.acl = acl;
     }

     public String getFileLoadPath() {
          return fileLoadPath;
     }

     public void setFileLoadPath(String fileLoadPath) {
          this.fileLoadPath = fileLoadPath;
     }

     public boolean isCreateNewFileName() {
          return createNewFileName;
     }

     public void setCreateNewFileName(boolean createNewFileName) {
          this.createNewFileName = createNewFileName;
     }

     public String getStyle() {
          return style;
     }

     public void setStyle(String style) {
          this.style = style;
     }
}
