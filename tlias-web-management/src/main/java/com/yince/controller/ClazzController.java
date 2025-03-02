package com.yince.controller;

import com.yince.pojo.Clazz;
import com.yince.pojo.ClazzQueryParam;
import com.yince.pojo.PageResult;
import com.yince.pojo.Result;
import com.yince.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 分页查询班级
     * @param clazzQueryParam
     * @return 统一返回结果
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("班级分页查询,参数:{}", clazzQueryParam);
        PageResult<Clazz> result = clazzService.page(clazzQueryParam);
        return Result.success(result);
    }

    /**
     * 根据id删除班级
     * @param id 路径参数
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除班级: {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增班级
     * @param clazz
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("新增班级: {}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    /**
     * 根据id查询班级
     * @param id 路径参数
     * @retrun 班级信息
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        log.info("根据id查询班级: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息
     * @param clazz
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 查询班级列表
     * @return
     */
    @GetMapping("/list")
    public Result getAll(){
        List<Clazz> list = clazzService.getAll();
        return Result.success(list);
    }
}
