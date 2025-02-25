package com.yince.mapper;

import com.yince.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    // 手动映射
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
    /*
    * 查询所有部门数据
    * */
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(int id);

    /*
    * 根据id修改部门名称
    * */
    @Update("update dept set name = #{name}, update_time = now() where id = #{id}")
    void updateById(Dept dept);

    /*
    * 根据部门名，添加部门
    * */
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insertByName(Dept dept);
}
