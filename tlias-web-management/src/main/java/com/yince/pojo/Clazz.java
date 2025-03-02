package com.yince.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 班级实体类
 * @Author: YinceBoulevard
 * @Date: 2025-3-2 16:04:48
 */
@Data
public class Clazz {
    private Integer id; // 主键ID
    private String name; // 班级名称
    private String room; // 教室
    private LocalDate beginDate; // 开课时间
    private LocalDate endDate; // 结课时间
    private Integer masterId; // 班主任ID
    private Byte subject; // 学科,1:java,2:前端,3:大数据,4:Python,5:Go,6:嵌入式
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间

    /*手动封装*/
    private String status; // 已开班，未开班
    private String masterName; // 班主任姓名
}
