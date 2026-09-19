import { createRouter, createWebHistory,createWebHashHistory } from 'vue-router'

import AboutView from '@/views/AboutView.vue'
import HomeView from '@/views/HomeView.vue'
import NewsView from '@/views/NewsView.vue'


// 路由器管理多条路由
// 路由器会监视到路径的变化，然后路由到指定的组件。


//  1、history模式，URL更加美观，不带有#。hash模式，URL带有#。

//  
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
//   history: createWebHashHistory(),

// 里面配置了很多路由规则，对应的组件都是路由组件。
  routes: [
    {
      path: '/',
      name: 'home', 
      component: HomeView,
    },
    {
      path: '/news',
      name: 'news',
      component: NewsView,
    },
    {
      path: '/about',
      name: 'myabout',
      component: AboutView,
    },
    {
      path: '/:pathMatch(.*)*',
      redirect: '/',
    },
  ],
})

export default router
