import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {path: '/register', name: 'register', component: () => import('../views/Register.vue')},
  {path: '/login', name: 'login', component: () => import('../views/Login.vue')},
  {path: '/manager', name: 'manager', component: () => import('../views/ManagerLayout.vue'),
    children:[
      {path: 'home', component: () => import('../views/manager/HomeView.vue')},
      {path: 'admin', component: () => import('../views/manager/Admin.vue')},
      {path: 'user', component: () => import('../views/manager/User.vue')},
      {path: 'student', component: () => import('../views/manager/Student.vue')},
      {path: 'teacher', component: () => import('../views/manager/Teacher.vue')},
      {path: 'faculties', component: () => import('../views/manager/Faculties.vue')},
      {path: 'major', component: () => import('../views/manager/Major.vue')},
      {path: 'course', component: () => import('../views/manager/Course.vue')},
      {path: 'coursesofmajor', component: () => import('../views/manager/Coursesofmajor.vue')},
      {path: 'studentmajor', component: () => import('../views/manager/Studentmajor.vue')},
      {path: 'teacherfaculties', component: () => import('../views/manager/Teacherfaculties.vue')},
      {path: 'teaching', component: () => import('../views/manager/Teaching.vue')},
      {path: 'learning', component: () => import('../views/manager/Learning.vue')},
    ]
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
// 路由守卫
router.beforeEach((to ,from, next) => {
  if (to.path === '/') {
    return next("/login");
  }
  let user = localStorage.getItem("user");
  if (!user && to.path !== '/login' && to.path !== '/register' && to.path !== '/front/index') {
    return next("/login");
  }
  next();
})

export default router