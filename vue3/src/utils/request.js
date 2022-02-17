import axios from 'axios'
import { storageUtils } from './common'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const router = useRouter()

const service = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_URL,
  timeout: 60000
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    const user = storageUtils.get('user', true)
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

// 响应拦截器
service.interceptors.response.use(
  response => {
    const { data } = response
    if (data.returnStatus !== 'SUCCEED') {
      ElMessage.error(data.errorMessage)
    }
    return response
  },
  err => {
    console.log(err.response)
    const { status } = err.response
    if (status === 401) {
      storageUtils.remove('user')
      router.push('/login')
    } else {
      ElMessage.error('请求异常')
    }
    console.error(err)
    return Promise.reject(err)
  }
)

export default service