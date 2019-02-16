package com.me.szzc.enums;

import com.me.szzc.utils.fileUpload.client.AliyunFileUploadUtils;
import com.me.szzc.utils.fileUpload.facade.request.AliyunConfigReq;
import org.apache.commons.lang.StringUtils;

/**
 * Created by luwei on 17-2-28.
 */
public enum FileUploadTypeEnum {

    SYSARGS("1", "系统参数图片", new AliyunConfigReq("console/sysargs", AliyunFileUploadUtils.PUBLIC_BUCKET, 1, AliyunFileUploadUtils.PUBLIC_FILE_LOAD_PATH, Boolean.TRUE)),

    ;


    private  String code;

    private String name;

    private AliyunConfigReq configReq;

    FileUploadTypeEnum(String code, String name, AliyunConfigReq configReq) {
        this.code = code;
        this.name = name;
        this.configReq = configReq;
    }


    public static FileUploadTypeEnum getObjectByCode(String code){
        for (FileUploadTypeEnum value : FileUploadTypeEnum.values()){
            if (StringUtils.equals(code, value.code)){
                return  value;
            }
        }
        return null;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AliyunConfigReq getConfigReq() {
        return configReq;
    }

    public void setConfigReq(AliyunConfigReq configReq) {
        this.configReq = configReq;
    }
}
