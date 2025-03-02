package com.yince.mapper;

import com.yince.pojo.Clazz;
import com.yince.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.jetbrains.annotations.PropertyKey;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 分页查询
     * @param clazzQueryParam
     * @return
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 根据id删除
     * @param id
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 保存
     * @param clazz
     */
    void insert(Clazz clazz);

    /**
     * 根据id查询
     * @param id
     */
    @Select("select name,room,begin_date,end_date,master_id,subject,create_time,update_time from clazz where id = #{id}")
    Clazz getById(Integer id);

    /**
     * 更新
     * @param clazz
     */
    void update(Clazz clazz);

    /**
     * 查询所有
     * @return
     */
    @Select("select id,name,room,begin_date,end_date,master_id,subject,create_time,update_time from clazz")
    List<Clazz> getAll();
}
