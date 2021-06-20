import axios from 'axios'

//创建一个axios实例
const service = axios.create({
    baseURL: 'http://localhost:8081/',
    // 请求超时时间
    timeout: 60000
})

//请求拦截器
service.interceptors.request.use(
    config => {
        //添加token
        let user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            config.headers['Authorization'] = user.token;
            config.headers['id'] = user.id;
        }
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
        console.error(err)
        Promise.reject(err)
    }
)

export default service