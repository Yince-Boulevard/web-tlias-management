import axios from 'axios'

const request = axios.create({
  baseURL: '/api',
  timeout: 6000,
})

request.interceptors.response.use(
    (response) => {
        return response.data
    }, 
    (error) => {
    return Promise.reject(error)
})

export default request