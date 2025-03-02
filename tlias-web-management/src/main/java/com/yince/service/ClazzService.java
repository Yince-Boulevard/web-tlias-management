package com.yince.service;

import com.yince.pojo.Clazz;
import com.yince.pojo.ClazzQueryParam;
import com.yince.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 分页查询
     * @param clazzQueryParam
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);
}
