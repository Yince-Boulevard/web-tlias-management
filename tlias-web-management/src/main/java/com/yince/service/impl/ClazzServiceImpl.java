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
}
