package com.yince.controller;

import com.yince.pojo.PageResult;
import com.yince.pojo.Result;
import com.yince.pojo.Student;
import com.yince.pojo.StudentQueryParam;
import com.yince.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 条件分页查询
     * @return
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询,参数:{}", studentQueryParam);
        PageResult<Student> result = studentService.page(studentQueryParam);
        return Result.success(result);
    }

    /**
     * 批量删除学生
     * @param ids
     * @return
     * @PathVariable: 路径参数 /students/1,2,3
     * @Author: Yince Boulevard
     * @Date: 2025-3-3 15:52:55
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除,id:{}", ids); // id: [1,2,3]
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 新增学生
     * @param student
     * @return
     * @Author: Yince Boulevard
     * @Date: 2025-3-3 15:55:50
     */
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("保存,参数:{}", student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 根据id查询学生信息
     * @param id
     * @return
     * @Author: Yince Boulevard
     * @Date: 2025-3-3 16:12:10
     * @PathVariable: 路径参数 /students/1
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("获取学生信息,id:{}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     * @Author: Yince Boulevard
     * @Date: 2025-3-3 16:16:18
     */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改,参数:{}", student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 修改学生违纪记录
     * @param id
     * @param score
     * @return
     * @Author Yince Boulevard
     * @Date 2025-3-3 16:43:35
     */
    @PutMapping("/violation/{id}/{score}")
    public Result updateScore(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("修改,id:{},score:{}", id, score); //修改,id:23,score:7
        Student student = new Student();
        student.setId(id);
        // 将Integer转Byte
        student.setViolationScore(score.byteValue());
        studentService.update(student);
        return Result.success();
    }
}
