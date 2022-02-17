import request from '@/utils/request'

const userApi = {
  login: (params) => {
    return request({
      url: '/account/user/login',
      method: 'get',
      params
    })
  },
  register: (data) => {
    return request({
      url: '/account/user/register',
      method: 'post',
      data
    })
  }
}

export default userApi