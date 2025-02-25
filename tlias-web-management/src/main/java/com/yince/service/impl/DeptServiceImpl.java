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
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(int id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void update(Dept dept) {
        deptMapper.updateById(dept);
    }

    @Override
    public void add(Dept dept) {
        // 补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 调用持久层方法
        deptMapper.insertByName(dept);
    }
}
