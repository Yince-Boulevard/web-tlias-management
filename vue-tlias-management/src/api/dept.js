import request from "@/utils/request";

// 查询所有部门
export const queryAllApi = () => request.get('/depts');

// 新增部门
export const addApi = (dept) => request.post('/depts', dept);

// 查询回显
export const queryByIdApi = (id) => request.get(`/depts/${id}`);
// 修改部门
export const updateApi = (dept) => request.put('/depts', dept);
// 删除部门
export const deleteByIdApi = (id) => request.delete(`/depts/${id}`);