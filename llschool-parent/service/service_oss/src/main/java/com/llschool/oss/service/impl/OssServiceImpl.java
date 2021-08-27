package com.llschool.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.llschool.oss.service.OssService;
import com.llschool.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;


@Service
public class OssServiceImpl implements OssService {

    //上传文件到Oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //通过工具类获取配置值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取上传文件的输入流。
            InputStream inputStream = file.getInputStream();
           //获取文件名称
            String fileName = file.getOriginalFilename();
            //在文件名称中添加随机唯一值
            String Uid = UUID.randomUUID().toString().replaceAll("-","");
            fileName = Uid + fileName;
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath + "/" + fileName;
            // 使用oss方法上传，第一个参数bucketName,第二个参数上传到oss中的文件路径,第三个参数上传文件的输入流
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient
            ossClient.shutdown();
            //把上传的文件路径返回
            //将上传到阿里云oss路径手动拼接出来
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // 创建OSSClient实例。

    }
}
