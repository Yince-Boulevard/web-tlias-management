package com.yince.mapper;

import com.yince.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    @Insert("insert into operate_log(operate_emp_id,operate_time,class_name,method_name,method_params,return_value,cost_time) " +
            "values(#{operateEmpId},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    void insert(OperateLog log);

    /**
     * 查询所有操作日志
     * @return
     */
    List<OperateLog> list();
}
