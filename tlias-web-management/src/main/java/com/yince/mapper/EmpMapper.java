package com.yince.mapper;

import com.yince.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/*
* 员工数据访问接口
* @Author: Yince Boulevard
* @Date: 2025-2-26 20:22:45
*
* */
@Mapper
public interface EmpMapper {

    /*
    * 分页查询，根据PageHelper插件
    *
    * */
    List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
}
