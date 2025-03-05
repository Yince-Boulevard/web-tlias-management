package com.yince.service;

import com.yince.pojo.OperateLog;
import com.yince.pojo.PageResult;

public interface OperateLogService {
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
