package com.yince.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 班级人员统计的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzOption {
    private List clazzList; // 班级列表
    private List dataList; // 数据列表
}
