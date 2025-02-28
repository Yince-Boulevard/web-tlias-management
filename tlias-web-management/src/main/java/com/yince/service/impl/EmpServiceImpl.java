package com.yince.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yince.mapper.EmpMapper;
import com.yince.pojo.Emp;
import com.yince.pojo.PageResult;
import com.yince.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/*
* 员工业务实现类
* @Author: Yince Boulevard
* @Date: 2025-2-26 20:24:29
* */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    /*
    * 基于pagehelper分页查询员工信息s
    * @param page 起始页码
    * @param pageSize 每页显示条数
    * @return 员工信息列表
    * */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        // 1. 设置分页参数(PageHelper)
//        PageHelper.startPage(page, pageSize);
//        // 2. 执行查询
//        List<Emp> empList = empMapper.list();
//        // 3. 解析查询结果
//        Page<Emp> p = (Page<Emp>) empList;
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//    }

    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        // 1. 设置分页参数(PageHelper)
        PageHelper.startPage(page, pageSize);
        // 2. 执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        // 3. 解析查询结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }
}
