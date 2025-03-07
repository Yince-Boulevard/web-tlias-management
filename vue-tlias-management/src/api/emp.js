import request from "@/utils/request";

// 条件查询员工
export const queryPageApi = (name,gender,begin,end,page,pageSize) => request.get(`/emps?name=${name}&gender=${gender}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`);
// 修改员工信息

// 新增员工

// 删除员工