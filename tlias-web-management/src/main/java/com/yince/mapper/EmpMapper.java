package com.yince.mapper;

import com.yince.pojo.Emp;
import com.yince.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    List<Emp> list(EmpQueryParam empQueryParam);


    /*
    * 新增员工基本信息
    * */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date,dept_id, create_time, update_time) " +
    "values (#{username}, #{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    Integer insert(Emp emp);


    /**
     * 根据id批量删除员工
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工基本信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 更新员工基本信息
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 查询员工岗位数据
     * @return
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    /**
     * 查询员工性别数据
     * @return: Map<String,Integer>
     * @Example: ["男": 10, "女": 20]
     */
    @MapKey("name")
    List<Map<String,Object>> countEmpGenderData();

    /**
     * 根据用户名密码查询员工
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /**
     * 根据用户名查询员工
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username}")
    Emp getByUsername(Emp emp);
}
