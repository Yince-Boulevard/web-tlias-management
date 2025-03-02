package com.yince.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yince.mapper.StudentMapper;
import com.yince.pojo.Emp;
import com.yince.pojo.PageResult;
import com.yince.pojo.Student;
import com.yince.pojo.StudentQueryParam;
import com.yince.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询
     * @param studentQueryParam
     * @return
     */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        // 1. 设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        // 2. 执行查询
        List<Student> studentList = studentMapper.list(studentQueryParam);
        // 3. 解析查询结果
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<Student>(p.getTotal(), p.getResult());
    }
}
