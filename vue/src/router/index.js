import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
const Note = () => import('../views/Note.vue')
const Demo2 = () => import('../views/Demo2.vue')
const Demo3 = () => import('../views/Demo3.vue')
const Demo4 = () => import('../views/Demo4.vue')
const Demo5 = () => import('../views/Demo5.vue')
const Demo6 = () => import('../views/Demo6.vue')
const Demo7 = () => import('../views/Demo7.vue')
const Demo8 = () => import('../views/Demo8.vue')
const Demo9 = () => import('../views/Demo9.vue')
const Demo10 = () => import('../views/Demo10.vue')
const Demo11 = () => import('../views/Demo11.vue')
const Login = () => import('../components/Login.vue')
const Register = () => import('../components/Register.vue')
const Finance = () => import('../views/Finance.vue')

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [
      {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import( '../views/About.vue')
      },
      {
        path: '/note',
        name: 'Note',
        component: Note
      },
      {
        path: '/demo2',
        name: 'Demo2',
        component: Demo2
      },
      {
        path: '/demo3',
        name: 'Demo3',
        component: Demo3
      },
      {
        path: '/demo4',
        name: 'Demo4',
        component: Demo4
      },
      {
        path: '/demo5',
        name: 'Demo5',
        component: Demo5
      },
      {
        path: '/demo6',
        name: 'Demo6',
        component: Demo6
      },
      {
        path: '/demo7',
        name: 'Demo7',
        component: Demo7
      },
      {
        path: '/demo8',
        name: 'Demo8',
        component: Demo8
      },
      {
        path: '/demo9',
        name: 'Demo9',
        component: Demo9
      },
      {
        path: '/demo10',
        name: 'Demo10',
        component: Demo10
      },
      {
        path: '/demo11',
        name: 'Demo11',
        component: Demo11
      },
      {
        path: '/',
        name: 'Finance',
        component: Finance
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
