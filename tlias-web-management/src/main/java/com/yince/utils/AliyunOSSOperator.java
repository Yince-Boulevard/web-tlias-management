package com.yince.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.yince.pojo.AliyunOSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AliyunOSSOperator {
    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    public String upload(byte[] content, String originalFileName) throws Exception {
        String endpoint = aliyunOSSProperties.getEndpoint();
        String bucketName = aliyunOSSProperties.getBucketName();
        String region = aliyunOSSProperties.getRegion();
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        // 获取当前系统时间
        String uploadTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        // 构建一个新的不重复的文件名
        String dir = uploadTime + "/";
        // 获取文件后缀名
        assert originalFileName != null; // 断言，如果为空则抛出异常
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        // 通过UUID生成新的文件名
        String newFileName = UUID.randomUUID().toString() + suffix;
        // 通过不同后缀判断存在哪一个文件夹(使用switch)
        dir = switch (suffix) {
            case ".jpg", ".png", ".gif" ->
                // 保存图片
                    uploadTime + "/images";
            case ".mp4", ".avi" ->
                // 保存视频
                    uploadTime + "/videos";
            case ".mp3", ".wav" ->
                // 保存音频
                    uploadTime + "/audios";
            case ".txt", ".pdf", ".doc", ".docx" ->
                // 保存文档
                    uploadTime + "/documents";
            default ->
                // 保存其他文件
                    uploadTime + "/others";
        };
        String objectName = dir + "/" + newFileName;
        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();
        // 上传文件
        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        } finally {
            ossClient.shutdown();
        }
        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" +objectName;
    }
}
