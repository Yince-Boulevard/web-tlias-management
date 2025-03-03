package com.yince.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 学员实体类
 * @Author: YinceBoulevard
 * @Date: 2025-3-2 16:04:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id; // 主键ID
    private String name; // 姓名
    private String no; // 学号
    private Byte gender; // 性别,1:男,2:女
    private String phone; // 手机号
    private String idCard; // 身份证号
    private Byte isCollege; // 是否来自院校,1:是,0:否
    private String address; // 联系地址
    private Byte degree; // 学历,1:初中,2:高中,3:大专,4:本科,5:硕士,6:博士
    private LocalDate graduationDate; // 毕业时间
    private Integer clazzId; // 班级ID
    private Byte violationCount; // 违规次数
    private Byte violationScore; // 违规扣分
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间

    /*手动封装额外的查询结果*/
    private String clazzName;
}
