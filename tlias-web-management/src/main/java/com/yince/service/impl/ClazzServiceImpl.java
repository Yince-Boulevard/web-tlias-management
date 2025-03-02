package com.yince.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yince.mapper.ClazzMapper;
import com.yince.pojo.Clazz;
import com.yince.pojo.ClazzQueryParam;
import com.yince.pojo.Emp;
import com.yince.pojo.PageResult;
import com.yince.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    /**
     * 分页查询
     * @param clazzQueryParam 班级查询条件实体类
     * @return 统一分页结果封装实体类
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 1. 设置分页参数(PageHelper)
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        // 2. 执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        // 3. 解析查询结果
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    /**
     * 根据id删除班级
     * @param id 班级id
     */
    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    /**
     * 保存班级
     * @param clazz 班级实体类
     */
    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
       clazzMapper.insert(clazz);
    }

    /**
     * 根据id查询班级
     * @param id 班级id
     * @return 班级实体类
     */
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    /**
     * 更新班级
     * @param clazz
     */
    @Override
    public void update(Clazz clazz) {
        // 补全基础属性
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    /**
     * 查询班级列表
     * @return
     */
    @Override
    public List<Clazz> getAll() {
        return clazzMapper.getAll();
    }
}
