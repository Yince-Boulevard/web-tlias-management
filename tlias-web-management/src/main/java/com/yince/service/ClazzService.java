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

    /**
     * 删除班级
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 保存班级
     * @param clazz
     */
    void add(Clazz clazz);

    /**
     * 根据id查询班级
     * @param id
     * @return
     */
    Clazz getById(Integer id);

    /**
     * 更新班级
     * @param clazz
     */
    void update(Clazz clazz);

    /**
     * 查询班级列表
     * @return
     */
    List<Clazz> getAll();
}
