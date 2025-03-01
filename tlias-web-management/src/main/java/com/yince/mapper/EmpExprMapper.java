package com.yince.mapper;

import com.yince.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
* 员工经历表数据操作类
* @Author: Yince Boulevard
* @Date: 2025-2-26 20:23:37
*
* */
@Mapper
public interface EmpExprMapper {

    /*
     * 批量插入员工经历信息
     * @param empExpr 员工经历信息列表
     * @return void
     * */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工id批量删除员工经历信息
     * @param: ids
     */
    void deleteByEmpIds(List<Integer> empIds);
}
