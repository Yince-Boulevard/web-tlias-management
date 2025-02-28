package com.yince.controller;

import com.yince.pojo.Emp;
import com.yince.pojo.PageResult;
import com.yince.pojo.Result;
import com.yince.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /*
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

    /*
    * 条件分页查询
    * @param emp: 查询条件
    * @param page: 当前页码
    * @param pageSize: 每页条数
    * @return: 返回分页结果
    * */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
                       ) {
        log.info("条件分页查询,参数:{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageResult<Emp> result = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(result);
    }
}
