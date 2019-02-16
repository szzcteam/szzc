package com.me.szzc.utils.fileUpload.client;


import com.me.szzc.utils.CustomizedPropertyConfigurer;
import com.me.szzc.utils.fileUpload.facade.AliyunFileUploadFacade;
import com.me.szzc.utils.fileUpload.impl.AliyunFileUploadFacadeImpl;
import com.me.szzc.utils.fileUpload.impl.request.AliyunOSSReq;

/**
 * @author luwei
 * @Date 12/2/16 2:29 PM
 */
public class AliyunFileUploadUtils {

    // 数据访问的点
    public static final String ENDPOINT = CustomizedPropertyConfigurer.getValue("aliyun.endpoint", "");

    // 主账户key id 、secrent
    private static final String ACCESS_KEY_ID = CustomizedPropertyConfigurer.getValue("aliyun.access_key_id", "");
    private static final String ACCESS_KEY_SECRET = CustomizedPropertyConfigurer.getValue("aliyun.access_key_secret", "");

    //ram子账户
    private static final String RAM_ACCESS_KEY_ID = CustomizedPropertyConfigurer.getValue("aliyun.ram.access_key_id", "");
    private static final String RAM_ACCESS_KEY_SECRET = CustomizedPropertyConfigurer.getValue("aliyun.ram.access_key_secret", "");
    private static final String RAM_ROLE_ARM = CustomizedPropertyConfigurer.getValue("aliyun.ram.role_arm", "");


    //bucket名称
    public static final String PUBLIC_BUCKET = CustomizedPropertyConfigurer.getValue("aliyun.bucket_name_pub", "");
    public static final String PRIVATE_BUCKET = CustomizedPropertyConfigurer.getValue("aliyun.bucket_name_pri", "");

    //域名
    public static final String PUBLIC_FILE_LOAD_PATH = CustomizedPropertyConfigurer.getValue("aliyun.obj_load_path_pub", "");
    public static final String PRIVATE_FILE_LOAD_PATH = CustomizedPropertyConfigurer.getValue("aliyun.obj_load_path_pri", "");


    private static AliyunFileUploadFacade fileUploadFacade;

    public static AliyunFileUploadFacade getFileUploadObject() {
        if(fileUploadFacade != null){
            return fileUploadFacade;
        }else{
            AliyunOSSReq req = new AliyunOSSReq(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET, RAM_ACCESS_KEY_ID, RAM_ACCESS_KEY_SECRET, RAM_ROLE_ARM);
            fileUploadFacade = new AliyunFileUploadFacadeImpl(req);
        }
        return fileUploadFacade;
    }



}
