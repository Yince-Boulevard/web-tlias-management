package com.yince.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;//新建一个JwtProperties类
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "com.jwt")
public class JwtProperties {

    /**
     * 用户端用户生成jwt令牌相关配置
     */
    private String userSecretKey; //jwt令牌密钥
    private long userTtl; //令牌过期时间
    private String userTokenName; //jwt令牌名称
}
