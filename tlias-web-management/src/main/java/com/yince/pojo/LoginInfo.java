package com.yince.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录结果实体类
 * @author yince boulevard
 * @date 2025-3-4 18:47:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    private Integer id; //主键id
    private String username; //用户名
    private String name; //姓名
    private String token; //令牌
}
