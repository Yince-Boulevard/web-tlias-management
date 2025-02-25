package com.yince.controller;

import com.yince.pojo.Dept;
import com.yince.pojo.Result;
import com.yince.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

/*
    第一种获取请求参数方式
    @DeleteMapping("/depts")
    public Result delete(HttpServletRequest request) {
        // 获取请求参数中的id字段
        String idStr = request.getParameter("id"); // getParameter方法总是返回String类型
        int id = Integer.parseInt(idStr);
        System.out.println("根据id删除部门: " + id);
        deptService.deleteById(id);
        return Result.success();
    }*/

    /*
    * 方式二： @RequestParam
    * */
    @DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id", required = true) Integer id) {
        System.out.println("根据id删除部门: " + id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        System.out.println("修改部门信息: " + dept);
        deptService.update(dept);
        return Result.success();
    }

    /*
    * 新增部门
    * */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        System.out.println("新增部门: " + dept);
        deptService.add(dept);
        return Result.success();
    }
}

