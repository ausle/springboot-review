<template>
  <section class="lifecycle-demo">
    <h2>Vue 3 生命周期示例</h2>

    <p>当前数字：{{ count }}</p>
    <button @click="count++">修改数据，触发更新</button>

    <p>当前窗口宽度：{{ windowWidth }}px</p>

    <p class="tip">
      修改数字后观察 <code>beforeUpdate</code> 和 <code>updated</code>；
      隐藏组件后观察 <code>onBeforeUnmount</code> 和 <code>onUnmounted</code>。
    </p>
  </section>
</template>

<script lang="ts" setup name="Demo6">
import {
  onBeforeMount,
  onMounted,
  onBeforeUpdate,
  onUpdated,
  onBeforeUnmount,
  onUnmounted,
  ref
} from 'vue'

const count = ref(0)
const windowWidth = ref(0)

// 页面窗口的大小发生变化，会触发数据变化。
function updateWindowWidth() {
  windowWidth.value = window.innerWidth
}

// 组件实例已经创建，但还没有挂载到页面
onBeforeMount(() => {
  console.log('[Demo6] onBeforeMount：即将挂载到页面')
})

// 组件已经挂载，可以访问真实 DOM，也适合注册事件
onMounted(() => {
  console.log('[Demo6] onMounted：已经挂载到页面')

  updateWindowWidth()
  window.addEventListener('resize', updateWindowWidth)
})

// 响应式数据变化，DOM 即将更新
onBeforeUpdate(() => {
  console.log('[Demo6] onBeforeUpdate：DOM 即将更新')
})

// DOM 更新完成
onUpdated(() => {
  console.log('[Demo6] onUpdated：DOM 已经更新')
})

// 组件即将被卸载，适合清理事件和定时器
onBeforeUnmount(() => {
  console.log('[Demo6] onBeforeUnmount：组件即将卸载')
  window.removeEventListener('resize', updateWindowWidth)
})

// 组件已经卸载完成
onUnmounted(() => {
  console.log('[Demo6] onUnmounted：组件已经卸载')
})
</script>

<style scoped>
.lifecycle-demo {
  padding: 20px;
  border: 1px solid #42b983;
  border-radius: 8px;
  background-color: #f0fff8;
}

button {
  padding: 8px 16px;
  cursor: pointer;
}

.tip {
  color: #666;
  line-height: 1.8;
}
</style>
