import { createRouter, createWebHistory } from "vue-router"
import { storageUtils } from "@/utils/common"

/**
 * menu: 是否在左侧菜单显示
 * login: 是否需要登录才能进入
 * title: 菜单的标题
 * icon: 菜单的图标
 */

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/Home.vue'),
    meta: {
      title: '常用功能',
      icon: 'menu'
    },
    menu: true,
    children: [
      {
        path: '/',
        name: 'Finance',
        component: () => import('@/views/Finance.vue'),
        meta: {
          login: true,
          title: 'finance',
          icon: 'list'
        },
        menu: true
      },
      {
        path: '/note',
        name: 'Note',
        component: () => import('@/views/Note.vue'),
        meta: {
          login: true,
          title: 'note',
          icon: 'checked'
        },
        menu: true
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