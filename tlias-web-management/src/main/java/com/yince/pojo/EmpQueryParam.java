package com.yince.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/*
* @Author: Yince Boulevard
* @Date: 2025-2-28 14:14:35
* @LastEditTime: 2025-2-28 14:14:35
* @LastEditors: Yince Boulevard
* @Description: 员工条件查询Controller层接受参数实体类
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpQueryParam {
    private Integer page = 1; //当前页码
    private Integer pageSize = 10; // 每页显示条数
    private String name; //员工姓名
    private Integer gender; //员工性别
    // 将前端不同日期格式转换为指定格式
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; //入职日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; //离职日期
}
