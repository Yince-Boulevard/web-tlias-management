package com.yince.pojo;

import lombok.Data;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;

/*
* 工作经历pojo
* @Author Yince Boulevard
* @Date 2025-2-26 20:20:23
* @Version 1.0
*
* */
@Data
public class EmpExpr {
    private Integer id;
    private Integer empId;
    private LocalDate begin;
    private LocalDate end;
    private String company;
    private String job;
}
