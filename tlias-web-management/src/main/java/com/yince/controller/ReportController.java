package com.yince.controller;

import com.yince.mapper.EmpMapper;
import com.yince.pojo.ClazzOption;
import com.yince.pojo.JobOption;
import com.yince.pojo.Result;
import com.yince.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 统计数据的控制类
 * @Author: Yince Boulevard
 * @Date: 2025-3-2 15:14:31
 */
@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 查询员工岗位数据
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("查询员工岗位数据");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("查询员工性别数据");
        List<Map<String,Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("查询学生学历数据");
        List<Map<String,Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("查询学生学历数据");
        ClazzOption clazzOption = reportService.getStudentCountData();
        return Result.success(clazzOption);
    }
}
