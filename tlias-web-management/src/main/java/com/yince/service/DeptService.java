package com.yince.service;

import com.yince.pojo.Dept;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {
    /*
    * 查询所有部门
    * */
    List<Dept> findAll();

    /*
    * 根据id删除部门
    * */
    void deleteById(int id);
    /*
    * 修改部门
    * */
    void update(Dept dept);

    /*
    * 添加部门
    * */
    void add(Dept dept);
}
