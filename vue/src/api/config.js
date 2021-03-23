import axios from 'axios'

//创建一个axios实例
const service = axios.create({
    baseURL: 'http://localhost:8080/',
    // 请求超时时间
    timeout: 3000
})

//请求拦截器
service.interceptors.request.use(
    config => {
        return config
    },
    err => {
        console.error(err)
        Promise.reject(err)
    }
)
//response拦截器
service.interceptors.response.use(
    response => {
        console.log(response)
        const res = response.data;
        return res;
    }, err => {
        console.error(response)
        Promise.reject(response)
    }
)

export default service