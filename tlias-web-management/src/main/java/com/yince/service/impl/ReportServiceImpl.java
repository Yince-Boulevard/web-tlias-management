package com.yince.service.impl;

import com.yince.mapper.ClazzMapper;
import com.yince.mapper.EmpMapper;
import com.yince.mapper.StudentMapper;
import com.yince.pojo.ClazzOption;
import com.yince.pojo.JobOption;
import com.yince.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClazzMapper clazzMapper;


    /**
     * 获取员工岗位数据
     * @return
     */
    @Override
    public JobOption getEmpJobData() {
        // 1. 调用mapper接口获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        // 2. 组装结果并返回
        /* list:
        * pos=教研主管,num=1
        * */
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> numList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, numList);
    }

    /**
     * 获取员工性别数据
     * @return
     * @Example: [{"name":"女","value":2},{"name":"男","value":3}]
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    /**
     * 获取学生班级数据
     * @return
     */
    @Override
    public ClazzOption getStudentCountData() {
        // 1. 调用mapper接口获取统计数据
        List<Map<String, Object>> list = clazzMapper.countClazzData();
        // 2. 组装结果并返回
        /* list:
         * pos=教研主管,num=1
         * */
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("name")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("value")).toList();
        return new ClazzOption(clazzList, dataList);
    }
}
