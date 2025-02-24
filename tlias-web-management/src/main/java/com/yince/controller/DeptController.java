package com.yince.controller;

import com.yince.pojo.Dept;
import com.yince.pojo.Result;
import com.yince.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//@Controller+@ResponseBody
public class DeptController {
    @Autowired
    private DeptService deptService;

    //    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list() {
        System.out.println("查询全部部门数据"); // 日志
        List<Dept> deptList = deptService.findAll(); // 调用部门管理业务层 的查询业务查询
        return Result.success(deptList); // 返回结果
    }
}

