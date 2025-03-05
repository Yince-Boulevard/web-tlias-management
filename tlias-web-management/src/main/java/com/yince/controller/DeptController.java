package com.yince.controller;
import com.yince.anno.Log;
import com.yince.pojo.Dept;
import com.yince.pojo.Result;
import com.yince.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/*
* 部门管理控制层
* @Author: Yince Boulevard
* @Date: 2025-2-26 19:28:38
* @Version: 1.0.0
* @Description: 部门的查询(查询所有，根据id查询，删除，修改，新增)
* */
@Slf4j // 日志注解,提供log
@RequestMapping("/depts") //类中方法公共url路径，抽取
@RestController// @Controller+@ResponseBody
public class DeptController {
//    private static final Logger log = LoggerFactory.getLogger(DeptController.class); //日志的固定代码

    @Autowired
    private DeptService deptService;
    /*
    * 查询部门 -- 查询所有
    * @Param: 无
    * @Return: Result
    * */
    //    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        log.info("查询所有部门");
        List<Dept> deptList = deptService.findAll(); // 调用部门管理业务层 的查询业务查询
        return Result.success(deptList); // 返回结果
    }
    /*
    * 查询部门 -- 根据id查询
    * @Param: @PathVariable("id") Integer id 获取路径参数，若路径参数中value的属性名和参数名一致，则value可以省略
    * @Return: Result
    * /depts/1
    * */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("根据id查询部门: {}", id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
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
    /*
    * 删除部门
    * @Param: @RequestParam(value = "id", required = true) Integer id 获取前端请求参数中的id字段
    * /depts?id=1
    * @Return: Result
    * */
    @Log
    @DeleteMapping
    public Result delete(@RequestParam(value = "id", required = true) Integer id) {
        log.info("根据id删除部门: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }
    /*
    * 修改部门
    * @Param: @RequestBody Dept dept 获取前端发送请求体中json格式的参数，转换为Dept对象
    * @Return: Result
    * */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        // 查询回显，先把原先数据查询出来放在修改框
        log.info("修改部门: {}", dept);
        deptService.update(dept);
        return Result.success();
    }
    /*
    * 新增部门
    * @Param: @RequestBody Dept dept 获取请求体中json格式的参数，转换为Dept对象
    * @Return: Result
    * */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门: {}" , dept);
        deptService.add(dept);
        return Result.success();
    }
}

