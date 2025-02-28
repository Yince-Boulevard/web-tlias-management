package com.yince.service.impl;

import com.yince.mapper.DeptMapper;
import com.yince.pojo.Dept;
import com.yince.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 获取所有部门信息列表
     * @return 部门信息实体列表
     */
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /**
     * 根据部门主键ID删除部门信息
     * @param id 部门主键ID
     */
    @Override
    public void deleteById(int id) {
        deptMapper.deleteById(id);
    }

    /**
     * 根据ID更新部门信息，自动维护更新时间字段
     * @param dept 部门信息实体对象，需包含有效ID和待更新字段
     */
    @Override
    public void update(Dept dept) {
        // 补全基础属性
        // 设置修改时间为当前系统时间
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateById(dept);
    }

    /**
     * 新增部门信息，自动维护创建时间和更新时间字段
     * @param dept 部门信息实体对象，需包含必要字段
     */
    @Override
    public void add(Dept dept) {
        // 补全基础属性
        // 设置创建时间和修改时间为当前系统时间
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 调用持久层方法
        deptMapper.insertByName(dept);
    }

    /**
     * 根据部门主键ID查询部门详情
     * @param id 部门主键ID
     * @return 部门信息实体，未找到时返回null
     */
    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

}
