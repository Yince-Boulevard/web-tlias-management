package com.yince.mapper;

import com.yince.pojo.PageResult;
import com.yince.pojo.Student;
import com.yince.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    /**
     * 分页查询
     * @param studentQueryParam
     * @return
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 批量删除
     * @param ids
     * @Author: Yince Boulevard
     * @Date: 2025-3-3 15:52:18
     */
    void deleteBatchByIds(List<Integer> ids);

    /**
     * 新增
     * @param student
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("insert into student(name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id,create_time,update_time) " +
            "values(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void insert(Student student);

    /**
     * 根据id查询学生信息
     * @param id
     * @return
     */
    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    /**
     * 修改学生信息
     * @param student
     */
    void updateById(Student student);

    /**
     * 学生学历统计
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> countStudentDegreeData();

}
