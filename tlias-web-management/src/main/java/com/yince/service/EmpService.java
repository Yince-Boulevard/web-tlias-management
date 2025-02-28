package com.yince.service;

import com.yince.pojo.Emp;
import com.yince.pojo.EmpQueryParam;
import com.yince.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public interface EmpService {

    /**
    * 条件分页查询（包括普通分页查询）
    * @param empQueryParam 条件查询参数的实体类
    * @return 分页查询结果
    * */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
    * 新增员工（包括经历）
    * @param emp 员工实体类
    * */
    void save(Emp emp);
}
