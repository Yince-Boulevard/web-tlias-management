package com.yince.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 班级条件查询参数
 * @Author: YinceBoulevard
 * @Date: 2025-3-2 16:10:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzQueryParam {
    private String name; //班级名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 开课时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 结课时间
    private Integer page = 1;
    private Integer pageSize = 10;
}
