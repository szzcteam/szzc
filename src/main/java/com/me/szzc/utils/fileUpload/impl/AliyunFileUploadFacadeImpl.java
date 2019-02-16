package com.me.szzc.utils.fileUpload.impl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.me.szzc.enums.FilePostfixEnum;
import com.me.szzc.exceptions.BaseException;
import com.me.szzc.log.LOG;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.JsonHelper;
import com.me.szzc.utils.Utils;
import com.me.szzc.utils.fileUpload.facade.AliyunFileUploadFacade;
import com.me.szzc.utils.fileUpload.facade.request.AliyunConfigReq;
import com.me.szzc.utils.fileUpload.facade.response.FileUploadResult;
import com.me.szzc.utils.fileUpload.impl.request.AliyunOSSReq;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author luwei
 * @Date 16-10-23 下午1:30
 */
public class AliyunFileUploadFacadeImpl implements AliyunFileUploadFacade {

    private final String REGION = "cn-shanghai";

    //当前STS API版本
    private final String STS_API_VERSION = "2015-04-01";


    //数据访问点
    private String endpoint;

    //主账户key
    private String accessKeyId;

    //主账户secret
    private String accessKeySecret;

    //只有RAM子账户才能调用AssumeRole接口,  阿里云主账户不能用于发起AssumeRole请求
    //子账户key
    private String ramAccessKeyId;

    //子账户KeySecret
    private String ramAccessKeySecret;

    //角色arm   roleArm从ram控制台获取
    private String ramRoleArm;


    //构造方法赋值
    public AliyunFileUploadFacadeImpl(AliyunOSSReq req) {
        if (req == null) {
            throw new BaseException("参数不能为空");
        }

        LOG.i(AliyunFileUploadFacadeImpl.class, "req:{}", JsonHelper.obj2JsonStr(req));

        if (StringUtils.isNotBlank(req.getEndpoint())) {
            this.endpoint = req.getEndpoint();
        } else {
            throw new BaseException("参数endpoint不能为空");
        }

        if (StringUtils.isNotBlank(req.getAccessKeyId())) {
            this.accessKeyId = req.getAccessKeyId();
        } else {
            throw new BaseException("参数accessKeyId不能为空");
        }

        if (StringUtils.isNotBlank(req.getAccessKeySecret())) {
            this.accessKeySecret = req.getAccessKeySecret();
        } else {
            throw new BaseException("参数accessKeySecret不能为空");
        }

        if (StringUtils.isNotBlank(req.getRamAccessKeyId())) {
            this.ramAccessKeyId = req.getRamAccessKeyId();
        } else {
            throw new BaseException("参数ramAccessKeyId不能为空");
        }


        if (StringUtils.isNotBlank(req.getRamAccessKeySecret())) {
            this.ramAccessKeySecret = req.getRamAccessKeySecret();
        } else {
            throw new BaseException("参数ramAccessKeySecret不能为空");
        }

        if (StringUtils.isNotBlank(req.getRamRoleArm())) {
            this.ramRoleArm = req.getRamRoleArm();
        } else {
            throw new BaseException("参数ramRoleArm不能为空");
        }
    }


    @Override
    public boolean checkFileType(CommonsMultipartFile file, FilePostfixEnum postfixEnum) {
        boolean flag = false;
        if(file == null || postfixEnum == null) {
            return flag;
        }

        try {
            String fileRealName = file.getOriginalFilename();
            String[] nameSplit = fileRealName.split("\\.");
            String ext = nameSplit[nameSplit.length - 1];
            if(StringUtils.isBlank(ext)) {
                return flag;
            }
            ext = ext.trim().toUpperCase();
            String[] postfix_arr = postfixEnum.getPostfix().split("\\.");
            for(int i=0; i<postfix_arr.length; i++) {
                if(ext.equals(postfix_arr[i])) {
                    flag = true;
                    break;
                }
            }

        } catch (Exception e) {
            LOG.e(this, "校验文件后缀格式异常", e);
            return flag;
        }

        return flag;
    }

    /**
     * 根据fileUrl获取存储在阿里云上的文件key
     * @param fileUrl
     * @return
     */
    public String getFileKey(String fileUrl) {
        String result = "";
        if (StringUtils.isBlank(fileUrl)) {
            return result;
        }

        result = fileUrl;

        if(fileUrl.indexOf(".aliyuncs.com") == -1) {  //不是绝对路径，是相对路径，则直接返回
            return result;
        }

        //绝对路径则进行截取处理
        result = fileUrl.substring( fileUrl.indexOf(".aliyuncs.com") +14 ,fileUrl.length());
        //部分数据有双斜杠
        if(result.substring(0,1).equals("/")) {
            result = result.substring( 1 ,result.length());
        }
        return result;
    }


    /**
     * 获取文件url
     *
     * @param fileUrl
     * @param configReq 配置子类
     * @return 文件绝对路径
     * @update1 2017-05-19基于51数字资产存储绝对路径的兼容，此处需做更改，仅处理私有权限的文件名称，追加超时时间，公有的可直接访问
     * 传递进来的fileUrl格式：https://51szzc.oss-cn-shanghai.aliyuncs.com/user/kycimg/201703081425037_g85RP.jpeg，实际文件key user/kycimg/XXXX.jpeg
     */
    @Override
    public String getFileUrl(String fileUrl, AliyunConfigReq configReq) {
        return this.getFileUrl(fileUrl, configReq, Boolean.FALSE);
    }


    /**
     * 获取文件url
     *
     * @param fileUrl
     * @param configReq 配置子类
     * @param isProcess 是否需要处理
     * @return 文件绝对路径
     */
    @Override
    public String getFileUrl(String fileUrl, AliyunConfigReq configReq, boolean isProcess) {
        String result = "";
        try {

            if (StringUtils.isBlank(fileUrl)) {
                return result;
            }

            if(fileUrl.indexOf(".aliyuncs.com") == -1) {
                return result;
            }

            if (configReq.getAcl() == 0) {  //私有,返回的路径需带签名
                fileUrl = getFileKey(fileUrl);
                //获取该object的签名信息
                int read_time = 1 * 60 * 60 * 1000; //生成的路径时效
                Date expires = new Date(System.currentTimeMillis() + read_time);
                OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                String fileKey = URLDecoder.decode(fileUrl, "UTF-8");
                //URL url = client.generatePresignedUrl(configReq.getBucketName(), fileKey, expires);
                GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(configReq.getBucketName(), fileKey, HttpMethod.GET);
                req.setExpiration(expires);
                //特殊处理，带文字水印
                if(isProcess && StringUtils.isNotBlank(configReq.getStyle())) {
                    req.setProcess(configReq.getStyle());
                }
                URL url = client.generatePresignedUrl(req);
                result = url.toString();
                client.shutdown();
            } else {  //不带签名
//                result = configReq.getFileLoadPath() + "/" + fileUrl;
                result = fileUrl;
            }

        } catch (Exception e) {
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }
        return result;



    }

    /**
     * 获取显示在页面的文件url
     *
     * @param fileUrl 文件的相对url，即上传后返回的相对路径
     * @param isSubTime 是否截取，即将文件名称上的日期格式化删除
     * @return 截取上传时，给出的yyyyMMddHHmmssSSS格式数字
     */
    @Override
    public String subStrUrl(String fileUrl, boolean isSubTime) throws Exception {
        if (StringUtils.isBlank(fileUrl)) {
            return fileUrl;
        }
        fileUrl = URLDecoder.decode(fileUrl, "UTF-8");
        fileUrl = fileUrl.substring(fileUrl.lastIndexOf("/") + 1, fileUrl.length());
        //将毫秒数去掉17位
        if(isSubTime) {
            fileUrl = fileUrl.substring(18, fileUrl.length());
        }

        return fileUrl;
    }

    /**
     * 对转码后的文件路径进行解码,还原文件名称
     *
     * @param url
     * @return
     */
    @Override
    public String decodeUrl(String url) {
        try {
            if (StringUtils.isBlank(url)) {
                return url;
            }
            url = URLDecoder.decode(url, "UTF-8");
        } catch (Exception e) {
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }
        return url;
    }

    /**
     * 对中文的文件路径进行转码,斜杠不转
     *
     * @param url
     * @return
     */
    @Override
    public String encodeUrl(String url) {
        try {
            if (StringUtils.isBlank(url)) {
                LOG.i(AliyunFileUploadFacadeImpl.class, "url:{}", url);
                return url;
            }
            String[] key_arr = url.split("/");
            url = "";
            for (int i = 0; i < key_arr.length; i++) {
                if (i == key_arr.length - 1) {
                    url += URLEncoder.encode(key_arr[i], "UTF-8");
                } else {
                    url += URLEncoder.encode(key_arr[i], "UTF-8") + "/";
                }
            }
        } catch (Exception e) {
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }
        return url;
    }


    /**
     * 文件上传
     *
     * @param file      文件
     * @param configReq 配置子类
     * @return  默认返回的是相对路径
     */
    @Override
    public String fileUpload(CommonsMultipartFile file, AliyunConfigReq configReq) {
        String filePath = "";
        try {
            if (file == null) {
                throw new BaseException("文件上传时,文件对象不能为空");
            }
            DiskFileItem fi = (DiskFileItem) file.getFileItem();
            String contentType = file.getContentType();

            File f = fi.getStoreLocation();
            String fileName = "";
            //使用随机创建的文件名
            if(configReq.isCreateNewFileName()) {
                String fileRealName = file.getOriginalFilename();
                String[] nameSplit = fileRealName.split("\\.");
                String ext = nameSplit[nameSplit.length - 1];
                fileName = Utils.getRandomImageName() + "." + ext;   //文件名称格式：201703081425037_g85RP.后缀
            }else{
                //判断文件的格式，采用不同的对象读取,不同的浏览器，获取的名称不同，如火狐，返回/fileName.jpg，是路径+文件名
                fileName = fi.getName();
                LOG.i(AliyunFileUploadFacadeImpl.class, "fileName:{}", fileName);
                if (StringUtils.isBlank(fileName)) {
                    return filePath;
                } else {  //判断是否带斜杠
                    int index = fileName.lastIndexOf("/");
                    if (index != -1) {
                        fileName = fileName.substring(index + 1);
                    }
                    LOG.i(AliyunFileUploadFacadeImpl.class, "fileName:{}", fileName);
                }
                String timeStr = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
                fileName = timeStr + "_" + fileName;
                LOG.i(AliyunFileUploadFacadeImpl.class, "fileName:{}", fileName);
            }

            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            //设置上传文件的元信息
            ObjectMetadata meta = new ObjectMetadata();
            // 设置上传文件长度
            meta.setContentLength(f.length());
            meta.setContentType(contentType);  //设置上传内容类型，不填则默认是application/octet-stream，通过地址栏访问时，会直接下载文件

            //putObject，第二个参数fileName是指上传到oss后，显示的Object对象文件名称
            //object不存在文件夹的说法，仅以/标识目录层级
            String folder_name = configReq.getFolder();
            filePath = folder_name + "/" + fileName;
            PutObjectResult resultObj = client.putObject(configReq.getBucketName(), filePath, f, meta);
            LOG.i(AliyunFileUploadFacadeImpl.class, "resultObj:{}", JsonHelper.obj2JsonStr(resultObj));
            //操作完成之后关闭client
            client.shutdown();
            //分开转换，目的/不转，区分文件夹
            filePath = encodeUrl(filePath);
            filePath = configReq.getFileLoadPath() + "/" + filePath;
            LOG.i(AliyunFileUploadFacadeImpl.class, "filePath:{}", filePath);
        } catch (Exception e) {
            LOG.i(AliyunFileUploadFacadeImpl.class, "exception:{}", e.getMessage());
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage(), e);
        }
        return filePath;
    }


    /**
     * 文件上传
     *
     * @param file             文件
     * @param configReq        配置子类
     * @param isResultAbsolute 是否返回绝对路径
     * @return
     */
    @Override
    public FileUploadResult fileUpload(CommonsMultipartFile file, AliyunConfigReq configReq, Boolean isResultAbsolute) {
        FileUploadResult fileUploadResult = new FileUploadResult();
        try {
            String relativeUrl = fileUpload(file, configReq);
            fileUploadResult.setRelativeUrl(relativeUrl);
            if (isResultAbsolute == true) {
                fileUploadResult.setAbsoluteUrl(getFileUrl(relativeUrl, configReq));
            }
        } catch (Exception e) {
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }
        return fileUploadResult;
    }


    /**
     * 删除文件，校验出异常时，使用
     *
     * @param file
     * @param configReq
     */
    @Override
    public void delFile(MultipartFile file, AliyunConfigReq configReq) {
        try {
            if (file == null) {
                throw new BaseException("删除文件时,文件对象不能为空");
            }
            CommonsMultipartFile cf = (CommonsMultipartFile) file;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();

            File f = fi.getStoreLocation();
            String fileName = fi.getName();
            String timeStr = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
            fileName = timeStr + "_" + fileName;
            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            String filePath = configReq.getFolder() + "/" + fileName;

            //删除文件信息
            client.deleteObject(configReq.getBucketName(), filePath);
            //操作完成之后关闭client
            client.shutdown();
        } catch (Exception e) {
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }
    }

    /**
     * 根据文件名删除文件，主要用于删除业务数据时，使用
     * fileName是带%格式的字符
     *
     * @param filePath
     */
    public void delFile(String filePath, AliyunConfigReq configReq) {
        try {
            if (filePath == null) {
                throw new BaseException("删除文件时,文件名称不能为空");
            }
            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            filePath = getFileKey(filePath);

            //删除文件信息
            filePath = URLDecoder.decode(filePath, "UTF-8");
            client.deleteObject(configReq.getBucketName(), filePath);
            //操作完成之后关闭client
            client.shutdown();
        } catch (Exception e) {
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }
    }

    /**
     * 文件下载，获取文件流
     * @param filePath
     * @param configReq
     * @return
     */
    @Override
    public byte[] getFileInputStream(String filePath, AliyunConfigReq configReq) {
        byte[] documentBody = null;
        try {
            if (filePath == null) {
                throw new BaseException("文件下载时,文件名称不能为空");
            }

            filePath = getFileKey(filePath);

            //解码
            filePath = decodeUrl(filePath);

            OSSClient client  = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            // 下载Object
            OSSObject ossObject = client.getObject(configReq.getBucketName(), filePath);

            InputStream inputStream = ossObject.getObjectContent();
            documentBody = IOUtils.toByteArray(inputStream);
            inputStream.close();
            client.shutdown();
        } catch (Exception e) {
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }

        return documentBody;
    }


    /**
     * 读取文件每行信息
     *
     * @param filePath
     */
    @Override
    public Set<String> readFileLine(String filePath, AliyunConfigReq configReq) {

        Set<String> set = new HashSet<>();
        try {
            if (filePath == null) {
                throw new BaseException("文件下载时,文件名称不能为空");
            }

            filePath = getFileKey(filePath);

            //解码
            filePath = decodeUrl(filePath);

            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            // 下载Object
            OSSObject ossObject = client.getObject(configReq.getBucketName(), filePath);

            InputStream contentFile = ossObject.getObjectContent();
            if (contentFile != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(contentFile));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    set.add(line);
                }
                reader.close();
            }
            contentFile.close();
            //操作完成之后关闭client
            client.shutdown();
        } catch (Exception e) {
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }
        return set;
    }


    /************
     * STS临时授权访问
     ********************/

    private AssumeRoleResponse assumeRole(String ram_access_key_id, String ram_access_key_secret, String roleArn, String roleSessionName, String policy, ProtocolType protocolType) {
        try {
            //创建一个aliyun acs client，用于发送OpenApi请求
            IClientProfile profile = DefaultProfile.getProfile(REGION, ram_access_key_id, ram_access_key_secret);
            DefaultAcsClient client = new DefaultAcsClient(profile);

            //创建一个AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);

            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);

            //发起请求，并得到response
            final AssumeRoleResponse response = client.getAcsResponse(request);

            return response;
        } catch (Exception e) {
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }

        return null;
    }

    @Override
    public AssumeRoleResponse getStsAssume() {

        //roleSessionName是临时token的会话名称，自己指定用于标识你的用户id，主要用于审计，或者区分token颁发给谁
        //注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
        String roleSessionName = "t-001";

        //定制policy
        String policy = "{\n" +
                "    \"Version\": \"1\", \n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": [\n" +
                //"                \"oss:GetBucket\", \n" +
                //"                \"oss:putObject\", \n" +
                "                \"oss:*\" \n" +
                "            ], \n" +
                "            \"Resource\": [\n" +
                "                \"acs:oss:*:*:*\"\n" +
                "            ], \n" +
                "            \"Effect\": \"Allow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        //此处必须为htts
        ProtocolType protocolType = ProtocolType.HTTPS;
        final AssumeRoleResponse response = assumeRole(ramAccessKeyId, ramAccessKeySecret, ramRoleArm, roleSessionName, policy, protocolType);
        if (response != null) {
            LOG.i(AliyunFileUploadFacadeImpl.class, "response:{}", JsonHelper.obj2JsonStr(response));
        } else {
            LOG.i(AliyunFileUploadFacadeImpl.class, "response 为空");
        }
        return response;

    }



    /**
     * 判断文件是否存在
     * @param fileUrl
     * @return
     */
    @Override
    public boolean objectExists(String fileUrl, String bucketName) {
        boolean flag = Boolean.FALSE;
        try {

            if (StringUtils.isBlank(fileUrl) || StringUtils.isBlank(bucketName)) {
                return flag;
            }

            if (fileUrl.indexOf(".aliyuncs.com") == -1) {
                return flag;
            }


            fileUrl = getFileKey(fileUrl);
            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            String fileKey = URLDecoder.decode(fileUrl, "UTF-8");
            flag = client.doesObjectExist(bucketName, fileKey);
            client.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }
        return flag;
    }


    /**
     * 拷贝文件，不改变文件名
     * @param fileUrl   源文件key
     * @param sourceBucket  源bucket
     * @param destinationKey 目标文件key
     * @param destinationBucket  目标bucket
     * @return
     */
    @Override
    public boolean copyObject(String fileUrl, String sourceBucket, String destinationKey, String destinationBucket) {
        boolean flag = Boolean.FALSE;
        try {

            if (StringUtils.isBlank(fileUrl) || StringUtils.isBlank(sourceBucket) || StringUtils.isBlank(destinationBucket )) {
                return flag;
            }

            if (fileUrl.indexOf(".aliyuncs.com") == -1) {
                return flag;
            }


            fileUrl = getFileKey(fileUrl);
            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            String fileKey = URLDecoder.decode(fileUrl, "UTF-8");
            CopyObjectResult copyObjectResult = client.copyObject(sourceBucket, fileKey, destinationBucket, destinationKey);
            if(copyObjectResult != null && copyObjectResult.getETag() != null) {
                flag = true;
            }
            client.shutdown();
        } catch (Exception e) {
            LOG.i(this, "拷贝异常：fileUrl="+fileUrl+" &sourceBucket="+sourceBucket+" &destinationKey="+destinationKey+" &destinationBucket="+destinationBucket);
            e.printStackTrace();
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }
        return flag;
    }




    /**
     * 批量删除文件
     * @param fileUrlSet  文件路径集合,相对路径bucket往下一级
     * @param bucketName   文件所在的buck
     * @return
     */
    @Override
    public boolean batchDelete(List<String> fileUrlSet, String bucketName) {
        boolean flag = Boolean.FALSE;
        try {

            if (fileUrlSet == null || fileUrlSet.size() == 0) {
                return flag;
            }

            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            DeleteObjectsRequest delReq = new DeleteObjectsRequest(bucketName).withKeys(fileUrlSet);
            delReq.setQuiet(true);

            DeleteObjectsResult deleteObjectsResult = client.deleteObjects(delReq);
            List<String> deleteObjects = deleteObjectsResult.getDeletedObjects();
            if(deleteObjects != null && deleteObjects.size() > 0) {
                LOG.i(this, "删除失败的文件key："+JsonHelper.obj2JsonStr(deleteObjects));
            }
            flag = Boolean.TRUE;
            client.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
        }
        return flag;
    }


    /**
     * 删除文件
     * @param bucketName
     * @param fileUrl  相对路径，是文件的key
     * @return
     */
    @Override
    public boolean deleteFile(String bucketName, String fileUrl) {
        boolean flag = false;
        try {
            if (fileUrl == null) {
                throw new BaseException("删除文件时,文件名称不能为空");
            }
            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            client.deleteObject(bucketName, fileUrl);
            //操作完成之后关闭client
            client.shutdown();
            flag = true;
        } catch (Exception e) {
            LOG.e(AliyunFileUploadFacadeImpl.class, e.getMessage());
            flag = false;
        }

        return  flag;
    }
}
