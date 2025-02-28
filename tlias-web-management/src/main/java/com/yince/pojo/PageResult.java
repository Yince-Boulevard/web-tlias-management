package com.yince.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
* 分页结果封装类
* @Author Yince Boulevard
* @Date: 2025-2-26 21:22:38
* */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult<T> {
    // 与接口文档保持一致
    private Long total; // 总记录数
    private List<T> rows; // 数据
}
