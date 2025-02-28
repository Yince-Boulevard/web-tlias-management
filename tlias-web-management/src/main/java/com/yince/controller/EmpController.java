package com.yince.controller;

import com.yince.pojo.Emp;
import com.yince.pojo.EmpQueryParam;
import com.yince.pojo.PageResult;
import com.yince.pojo.Result;
import com.yince.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/*
* @Description: 员工管理控制层
* @Author: Yince Boulevard
* @Date: 2025-2-28 14:18:12
* @Version: 1.0
* @LastEditors: Yince Boulevard
* @LastEditTime: 2025-2-28 14:18:12
* */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
    * 分页查询
    * @param start: 当前页码
    * @param pageSize: 每页条数
    * @return: 返回分页结果
    * */
/*    @GetMapping
    public Result page(Integer page, Integer pageSize) {
        log.info("分页查询,参数:{},{}", page, pageSize);
        PageResult<Emp> result = empService.page(page, pageSize);
        return Result.success(result);
    }*/

    /**
    * 条件分页查询
    * @param empQueryParam: 查询条件
    * @return: 返回分页结果
    * */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询,参数:{}", empQueryParam);
        PageResult<Emp> result = empService.page(empQueryParam);
        return Result.success(result);
    }

    /**
    * 新增员工
    * @param emp: 员工实体类
    * @return: 返回结果
    * */
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工: {}" , emp);
        empService.save(emp);
        return Result.success();
    }
}
