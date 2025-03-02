package com.yince.controller;

import com.yince.pojo.PageResult;
import com.yince.pojo.Result;
import com.yince.pojo.Student;
import com.yince.pojo.StudentQueryParam;
import com.yince.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
