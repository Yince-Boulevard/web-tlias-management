package com.yince.mapper;

import com.yince.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*
    * 查询所有部门数据
    * */
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();
}
