package com.yince.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yince.mapper.EmpLogMapper;
import com.yince.mapper.OperateLogMapper;
import com.yince.pojo.Emp;
import com.yince.pojo.OperateLog;
import com.yince.pojo.PageResult;
import com.yince.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        // 1. 设置分页参数(PageHelper)
        PageHelper.startPage(page, pageSize);
        // 2. 执行查询
        List<OperateLog> OperateLogList = operateLogMapper.list();
        // 3. 解析查询结果
        Page<OperateLog> p = (Page<OperateLog>) OperateLogList;
        return new PageResult<OperateLog>(p.getTotal(), p.getResult());
    }
}
