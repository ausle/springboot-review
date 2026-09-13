// 从vue中引入一个createApp方法
// App.vue 通常是整个页面的根组件，也就是 Vue 应用最外层的那个页面。
// 使用 createApp 创建一个 Vue 应用，并且告诉 Vue，应用的主页是APP。
// 把刚刚创建的 Vue 应用，挂载到网页中 id="app" 的元素上。

import { createApp } from 'vue'
import App from './App.vue'

createApp(App).mount('#app')
