package com.yince.mapper;

import com.yince.pojo.EmpLog;
import com.yince.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpLogMapper {
    @Insert("insert into emp_log (operate_time, info) values (#{operateTime},#{info})")
    void insert(EmpLog empLog);


}
