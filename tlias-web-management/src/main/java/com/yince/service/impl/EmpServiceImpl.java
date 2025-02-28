package com.yince.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yince.mapper.EmpExprMapper;
import com.yince.mapper.EmpMapper;
import com.yince.pojo.*;
import com.yince.service.EmpLogService;
import com.yince.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
* 员工业务实现类
* @Author: Yince Boulevard
* @Date: 2025-2-26 20:24:29
* @Version: 1.0.1
* */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
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
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 1. 设置分页参数(PageHelper)
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        // 3. 解析查询结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }


    /**
    * 新增员工信息
    * */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            // 保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            // 保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            // 如果员工经历列表不为空，则保存
            if(!CollectionUtils.isEmpty(exprList)){
                // batch: 批量保存
                // 遍历集合，为empId赋值
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                // 调用empExprMapper中的批量保存方法
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            // 记录员工操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工" + emp);
            empLogService.insertLog(empLog);
        }


    }
}
