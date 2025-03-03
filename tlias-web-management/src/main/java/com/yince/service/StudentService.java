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

    /**
     * 该接口用于删除学员信息
     * @param ids
     * @Author: Yince Boulevard
     * @Date: 2025-3-3 15:53:13
     */
    void delete(List<Integer> ids);

    /**
     * 该接口用于保存学员信息
     * @param student
     */
    void save(Student student);

    /**
     * 该接口用于根据id查询学员信息
     * @param id
     * @return
     */
    Student getInfo(Integer id);

    /**
     * 该接口用于修改学员信息
     * @param student
     * @Author: Yince Boulevard
     * @Date: 2025-3-3 16:17:06
     */
    void update(Student student);

}
