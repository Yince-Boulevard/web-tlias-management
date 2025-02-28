package com.yince.service;

import com.yince.pojo.Dept;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {
    /**
    * 查询所有部门
    * @Param: 无参数, 查询所有不需要参数
    * @Return: List<Dept> 部门的集合，返回查询到的部门集合
    * */
    List<Dept> findAll();

    /**
    * 根据id删除部门
    * @Param: id 整型，前端发送的要删除的部门的id
    * @Return: void 无返回值
    * */
    void deleteById(int id);
    /**
    * 修改部门
    * @Param: dept 部门的实体类，前端发送的要修改的部门信息
    * @Return: void 无返回值
    * */
    void update(Dept dept);

    /*
    * 添加部门
    * @Param: dept 部门的实体类，前端发送的要添加的部门信息
    * @Return: void 无返回值
    * */
    void add(Dept dept);

    /**
    * 根据id查询部门
    * @Param: id 整型，前端发送的要查询的id
    * @Return: Dept 部门实体类，返回查询到的部门
    * */
    Dept findById(Integer id);
}
