<template>
  <div id="app">
    <h1>Vue 2 生命周期小例子</h1>

    <p>
      打开浏览器控制台，可以观察子组件创建、更新和销毁时的生命周期日志。
    </p>

    <button @click="showDemo = !showDemo">
      {{ showDemo ? '销毁子组件' : '重新创建子组件' }}
    </button>

    <LifecycleDemo v-if="showDemo" />

    <p v-if="!showDemo" class="tip">
      子组件已经销毁，点击按钮可以重新创建它。
    </p>
  </div>
</template>

<script lang="ts">
import LifecycleDemo from './components/LifecycleDemo.vue'

export default {
  name: 'App',

  components: {
    LifecycleDemo
  },

  data() {
    return {
      showDemo: true
    }
  }
}
</script>

<style scoped>
.app {
  max-width: 760px;
  margin: 0 auto;
  padding: 32px;
  color: #2c3e50;
}

button {
  margin: 16px 0;
  padding: 8px 16px;
  cursor: pointer;
}

.tip {
  color: #888;
}
</style>
