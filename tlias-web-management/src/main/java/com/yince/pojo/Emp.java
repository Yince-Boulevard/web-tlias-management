package com.yince.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
/*
* 员工实体类
* @Author: Yince Boulevard
* @Date: 2025-2-26 20:21:51
* */
@Data
public class Emp {
    /*帮我创建emp表的实体类*/
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private String phone;
    private Integer job;
    private Integer salary;
    private String image;
    private LocalDate entryTime;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 关联属性
    // 封装部门名称
    private String deptName;
}
