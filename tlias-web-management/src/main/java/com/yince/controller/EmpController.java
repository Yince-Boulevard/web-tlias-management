package com.yince.controller;

import com.yince.pojo.Emp;
import com.yince.pojo.EmpQueryParam;
import com.yince.pojo.PageResult;
import com.yince.pojo.Result;
import com.yince.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: 员工管理控制层
* @Author: Yince Boulevard
* @Date: 2025-2-28 14:18:12
* @Version: 1.0
* @LastEditors: Yince Boulevard
* @LastEditTime: 2025-2-28 14:18:12
*/
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

    /**
     * 删除员工
     * @param ids 前端请求的员工的id数组
     * @return
     * @Author: Yince Boulevard
     * @Date: 2025-3-1 21:11:42
     * @Example: /emps/delete?ids=1,2,3
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工: {}" , ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 修改员工信息
     * @Param emp 员工信息,接受前端json格式参数
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工: {}" , emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 根据id查询员工信息
     * @param id 路径参数
     * @return 员工信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工: {}" , id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

}
