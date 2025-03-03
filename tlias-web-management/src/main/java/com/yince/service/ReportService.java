package com.yince.service;

import com.yince.pojo.ClazzOption;
import com.yince.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 获取员工岗位数据的接口
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 获取员工性别数据的接口
     * @return
     * @Example: {"male":10,"female":20}
     */
    List<Map<String,Object>> getEmpGenderData();

    /**
     * 获取学生学历数据的接口
     * @return
     */
    List<Map<String, Object>> getStudentDegreeData();

    /**
     * 获取学生班级数量的接口
     * @return
     */
    ClazzOption getStudentCountData();
}
