package com.yince.service.impl;

import com.yince.mapper.EmpLogMapper;
import com.yince.pojo.EmpLog;
import com.yince.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
* @Description: 日志服务实现类
* @Author: Yince Boulevard
* @Date: 2025-2-28 17:15:05
* @LastEditTime: 2025-2-28 17:15:05
* @Version: 1.0.0
* */
@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;


    /**
    * 插入日志
    * 需要在一个新事物中运行
    * */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
