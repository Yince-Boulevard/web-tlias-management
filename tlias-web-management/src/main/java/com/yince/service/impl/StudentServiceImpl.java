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

import java.time.LocalDateTime;
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

    /**
     * 批量删除
     * @param ids
     * @Author: Yince Boulevard
     * @Date: 2025-3-3 15:53:32
     */
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.deleteBatchByIds(ids);
    }

    /**
     * 保存学生
     * @param student
     */
    @Override
    public void save(Student student) {
        // 1. 补充基础信息
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    /**
     * 根据id查询学生信息的实现类
     * @param id
     * @return
     * @Author: Yince Boulevard
     * @Date: 2025-3-3 16:13:08
     */
    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getById(id);
    }

    /**
     * 修改学生信息
     * @param student
     */
    @Override
    public void update(Student student) {
        // 1. 补充基础信息
        student.setUpdateTime(LocalDateTime.now());
        // 2. 执行修改
        studentMapper.updateById(student);
    }


}
