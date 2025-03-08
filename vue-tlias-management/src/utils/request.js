import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '@/router/index'
// 创建axios实例
const request = axios.create({
  baseURL: '/api',
  timeout: 6000,
})

// 添加请求拦截器 - 获取请求头中的token,在请求头中增加token
request.interceptors.request.use(
    (config) => { // 成功回调
        const loginUser = JSON.parse(localStorage.getItem('loginUser'))
        if (loginUser && loginUser.token) {
            config.headers.token = loginUser.token
        }
        return config
    }, 
    (error) => {
    return Promise.reject(error)
})

// 添加响应拦截器
request.interceptors.response.use(
    (response) => {
        return response.data
    }, 
    (error) => {
      if (error.response.status === 401) { // 全等
        // 跳转到登录页
        router.push('/login')
        ElMessage.error('登录超时，请重新登录')
      }else{
        ElMessage.error(error.response.data.message)
      }
    return Promise.reject(error)
})

export default request