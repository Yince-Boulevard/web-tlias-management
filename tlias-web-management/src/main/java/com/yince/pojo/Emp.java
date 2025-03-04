package com.yince.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
* @Description: 员工实体类
* @Author: Yince Boulevard
* @Date: 2025-2-26 20:21:51
* @LastEditTime: 2025-2-28 15:32:55
* */
@Data
public class Emp {
    private Integer id; //主键id
    private String username; //用户名
    private String password; //密码
    private String name; //姓名
    private Integer gender; //性别
    private String phone; //手机号
    private Integer job; //职位
    private Integer salary; //薪资
    private String image; //头像
    private LocalDate entryDate; //入职日期
    private Integer deptId; //部门id
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //更新时间

    // 关联属性
    // 封装部门名称
    private String deptName;

    // 封装员工经历信息
    private List<EmpExpr> exprList;
}
