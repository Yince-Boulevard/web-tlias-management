package com.yince.mapper;

import com.yince.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
* 部门mapper
* @Author: Yince Boulevard
* @Date: 2025-2-26 19:30:17
* @LastEditTime: 2025-2-26 19:30:17
* @LastEditors: Yince Boulevard
* @Description: 部门实体类的数据操作接口，涉及部门的数据库操作
* @FilePath: \tlias-web-management\src\main\java\com\yince\mapper\DeptMapper.java
* @Copyright (c) 2025 by Yince Boulevard, All Rights Reserved.
* @Version: v1.0.0
* @Warning:
* @Reference:
* @History:
* @Reference:
* */
@Mapper
public interface DeptMapper {
    // 手动映射
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
    /*
    * 查询所有部门数据
    * @Param("id") 中的id会传递给 Integer id
    * @Return: List<Dept>
    * */
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    /*
    * 根据id删除部门
    * @Param("id") 中的id会传递给 Integer id
    * @Return: void 但不需要接受，所以可以返回void
    * */
    @Delete("delete from dept where id = #{id}")
    void deleteById(int id);

    /*
    * 根据id修改部门名称
    * @Param("name") 中的name会传递给 String name
    * @Return: int 但不需要接受，所以可以返回void
    * */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void updateById(Dept dept);

    /*
    * 根据部门名，添加部门
    * @Param("name") 中的name会传递给 String name
    * @Return: int 但不需要接受，所以可以返回void
    * */
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insertByName(Dept dept);

    /*
    * 根据id查询部门
    * @Param("id") 中的id会传递给 Integer id
    * @Results({})
    * */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept findById(Integer id);
}
