package com.yince.controller;

import com.yince.pojo.Result;
import com.yince.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description: 文件上传控制类
 * @Author: yince boulevard
 * @Date: 2025-3-1 15:18:48
 */
@Slf4j
@RestController
public class UploadController {

/*    *//**
     * 文件上传(本地存储）
     * @param name: 员工姓名
     * @param age: 员工年龄
     * @param file: 文件
     * @return com.yince.pojo.Result 统一返回结果类
     *//*
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("文件上传: {}, {}, {}", name, age, file);
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀名
        assert originalFilename != null; // 断言，如果为空则抛出异常
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 通过UUID生成新的文件名
        String newFileName = UUID.randomUUID().toString() + suffix;
        // 通过不同后缀判断存在哪一个文件夹(使用switch)
        switch (suffix) {
            case ".jpg":
            case ".png":
            case ".gif":
                // 保存图片
                file.transferTo(new File("E:/uploadFile/images/" + newFileName));
                break;
            case ".mp4":
            case ".avi":
                // 保存视频
                file.transferTo(new File("E:/uploadFile/videos/" + newFileName));
                break;
            case ".mp3":
            case ".wav":
                // 保存音频
               file.transferTo(new File("E:/uploadFile/audios/" + newFileName));
               break;
            case ".txt":
            case ".pdf":
            case ".doc":
            case ".docx":
                // 保存文档
                file.transferTo(new File("E:/uploadFile/documents/" + newFileName));
                break;
            default:
                // 保存其他文件
                file.transferTo(new File("E:/uploadFile/others/" + newFileName));
                break;
        }

        return Result.success();
    }*/
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传: {}" , file.getOriginalFilename());
        // 将文件交给阿里云OSS进行上传
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }
}