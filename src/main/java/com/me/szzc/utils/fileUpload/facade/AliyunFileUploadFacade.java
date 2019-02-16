package com.me.szzc.utils.fileUpload.facade;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.me.szzc.enums.FilePostfixEnum;
import com.me.szzc.utils.fileUpload.facade.request.AliyunConfigReq;
import com.me.szzc.utils.fileUpload.facade.response.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @author luwei
 * @Date 12/2/16 11:53 AM
 */
public interface AliyunFileUploadFacade {

    /**
     * 校验文件格式，类型
     * @param postfixEnum
     * @return
     */
    boolean checkFileType(CommonsMultipartFile file, FilePostfixEnum postfixEnum);


    /**
     * 获取文件url
     *
     * @param fileUrl
     * @param configReq 配置子类
     * @return 文件绝对路径
     */

    String getFileUrl(String fileUrl, AliyunConfigReq configReq);

    /**
     * 获取文件url
     *
     * @param fileUrl
     * @param configReq 配置子类
     * @param isProcess 是否需要处理
     * @return 文件绝对路径
     */

    String getFileUrl(String fileUrl, AliyunConfigReq configReq, boolean isProcess);

    /**
     * 获取显示在页面的文件url
     *
     * @param fileUrl 文件的相对url，即上传后返回的相对路径
     * @param isSubTime 是否截取，即将文件名称上的日期格式化删除
     * @return 截取上传时，给出的yyyyMMddHHmmssSSS格式数字
     */
    String subStrUrl(String fileUrl, boolean isSubTime) throws Exception;

    /**
     * 对转码后的文件路径进行解码,还原文件名称
     *
     * @param url
     * @return
     */
    String decodeUrl(String url);

    /**
     * 对中文的文件路径进行转码,斜杠不转
     *
     * @param url
     * @return
     */
    String encodeUrl(String url);


    /**
     * 文件上传
     *
     * @param file       文件
     * @param configReq 配置子类
     * @return
     */
    String fileUpload(CommonsMultipartFile file, AliyunConfigReq configReq);

    /**
     * 文件上传
     *
     * @param file       文件
     * @param configReq 配置子类
     * @param isResultAbsolute 是否返回绝对路径
     * @return
     */
    FileUploadResult fileUpload(CommonsMultipartFile file, AliyunConfigReq configReq, Boolean isResultAbsolute);

    /**
     * 删除文件，校验出异常时，使用
     *
     * @param file
     * @param configReq
     */
    void delFile(MultipartFile file, AliyunConfigReq configReq);


    /**
     * 根据文件名删除文件，主要用于删除业务数据时，使用
     * fileName是带%格式的字符
     *
     * @param filePath
     */
    void delFile(String filePath, AliyunConfigReq configReq);

    /**
     * 文件下载，获取文件流
     * @param filePath
     * @param configReq
     * @return
     */
    byte[] getFileInputStream(String filePath, AliyunConfigReq configReq);

    /**
     * 读取文件每行信息
     *
     * @param filePath
     */
    Set<String> readFileLine(String filePath, AliyunConfigReq configReq);


    /**
     * 获取阿里云oss临时授权
     * @return
     */
    AssumeRoleResponse getStsAssume();

    /**
     * 判断文件是否存在
     * @param fileUrl
     * @return
     */
    boolean objectExists(String fileUrl, String bucketName);

    /**
     * 拷贝文件，不改变文件名
     * @param fileUrl   源文件key
     * @param sourceBucket  源bucket
     * @param destinationKey 目标文件key
     * @param destinationBucket  目标bucket
     * @return
     */
    boolean copyObject(String fileUrl, String sourceBucket, String destinationKey, String destinationBucket);


    /**
     * 批量删除文件
     * @param fileUrlSet  文件路径集合,相对路径bucket往下一级
     * @param bucketName   文件所在的buck
     * @return
     */
    boolean batchDelete(List<String> fileUrlSet, String bucketName);


    /**
     * 删除文件
     * @param bucketName
     * @param fileUrl
     * @return
     */
    boolean deleteFile(String bucketName, String fileUrl);

}
