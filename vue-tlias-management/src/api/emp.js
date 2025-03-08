import request from "@/utils/request";

// 条件查询员工
export const queryPageApi = (name,gender,begin,end,page,pageSize) => request.get(`/emps?name=${name}&gender=${gender}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`);
// 修改员工信息
    // 根据id查询员工信息
export const queryInfoApi = (id) => request.get(`/emps/${id}`);
    // 修改员工信息
export const updateApi = (emp) => request.put('/emps',emp);
// 新增员工
export const addApi = (emp) => request.post('/emps',emp);
// 删除员工
export const deleteApi = (ids) => request.delete(`/emps?ids=${ids}`);