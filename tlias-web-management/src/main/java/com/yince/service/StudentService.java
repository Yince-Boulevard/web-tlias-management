package com.yince.service;

import com.yince.pojo.PageResult;
import com.yince.pojo.Student;
import com.yince.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 该接口用于学员列表数据的条件分页查询
     * @param studentQueryParam
     * @return
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);
}
