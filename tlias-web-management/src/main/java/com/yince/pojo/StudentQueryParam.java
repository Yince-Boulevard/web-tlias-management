package com.yince.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生查询参数
 * @Author: Yince Boulevard
 * @Date: 2025-3-2 19:51:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private String name;
    private String degree;
    private Integer clazzId;
    private Integer Page = 1 ;
    private Integer PageSize = 10;
}
