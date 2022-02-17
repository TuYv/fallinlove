import { createRouter, createWebHistory } from "vue-router"
import { storageUtils } from "@/utils/common"

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/Home.vue'),
    meta: {
      title: '常用功能',
      icon: ''
    },
    children: [
      {
        path: '/',
        name: 'Finance',
        component: () => import('@/views/Finance.vue'),
        meta: {
          login: true
        }
      },
      {
        path: 'note',
        name: 'Note',
        component: () => import('@/views/Note.vue'),
        meta: {
          login: true
        }
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from) => {

  // 需要登录的页面检查是否有登录信息
  const needLogin = to.meta?.login
  if (needLogin) {
    const user = storageUtils.get('user')
    if (!user) {
      return '/login'
    }
  }
})

export default router