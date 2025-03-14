package com.yince.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yince.mapper.EmpExprMapper;
import com.yince.mapper.EmpMapper;
import com.yince.pojo.*;
import com.yince.service.EmpLogService;
import com.yince.service.EmpService;
import com.yince.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 员工业务实现类
* @Author: Yince Boulevard
* @Date: 2025-2-26 20:24:29
* @Version: 1.0.1
* */
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
    @Autowired
    private JwtProperties jwtProperties;
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

    /**
     * 删除员工信息
     * @param: 前端请求的员工ids
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        // 1. 批量删除员工基本信息
        empMapper.deleteByIds(ids);
        // 2. 批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改员工信息
     * @param emp
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        // 1. 修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        // 2. 修改员工工作经历信息
            // 2.1 先删
        empExprMapper.deleteByEmpIds(List.of(emp.getId()));
            // 2.2 再新增
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            // 遍历集合，为empId赋值
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            // 调用empExprMapper中的批量保存方法
            empExprMapper.insertBatch(exprList);
        }

    }

    /**
     * 员工登录
     * @param emp
     * @return
     */
    @Override
    public LoginInfo login(Emp emp) {
        // 1. 调用数据访问层方法查询员工信息
        Emp e1 = empMapper.getByUsername(emp);
        // 2. 判断查询结果是否为空
        if(e1 != null){
            log.info("数据库存在该用户: {}", e1);
            Emp e2 = empMapper.getByUsernameAndPassword(emp);
            if(e2 != null){
                log.info("用户名和密码正确: {}", e2);
                // 生成jwt token
                //登陆controller中生成，返回给客户端
                Map<String, Object> claims = new HashMap<>();
                claims.put("id",e2.getId());
                claims.put("username",e2.getUsername());
                String token = JwtUtils.createJwt(
                        jwtProperties.getUserSecretKey(),
                        jwtProperties.getUserTtl(),
                        claims
                );
                return new LoginInfo(e2.getId(), e2.getUsername(), e2.getName(), token);
            }else {
                return new LoginInfo(0,"密码错误"," "," ");
            }
        }
        return new LoginInfo(-1,"用户不存在"," ", " ");
    }

}
