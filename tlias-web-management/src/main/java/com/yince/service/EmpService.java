package com.yince.service;

import com.yince.pojo.Emp;
import com.yince.pojo.EmpQueryParam;
import com.yince.pojo.PageResult;
import java.util.List;

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

    /**
     * 删除员工（包括经历）
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工信息（包括基本和工作经历信息)
     * @param emp
     */
    void update(Emp emp);
}
