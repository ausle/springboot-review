<template>
  <section class="demo">
    <h2>LifecycleDemo 子组件</h2>

    <p>当前数字：{{ count }}</p>

    <button @click="count++">修改数据，触发更新</button>

    <p class="tip">
      点击上面的按钮后，观察控制台中的 beforeUpdate 和 updated。
    </p>
  </section>
</template>

<script lang="ts">
export default {
  name: 'LifecycleDemo',

  data() {
    return {
      count: 0
    }
  },

  // 创建前和创建完毕
  beforeCreate() {
    console.log('[LifecycleDemo] beforeCreate：组件实例刚创建')
  },

  created() {
    console.log('[LifecycleDemo] created：data 和 methods 已准备好')
  },

  // 挂载前和挂载完毕
  beforeMount() {
    console.log('[LifecycleDemo] beforeMount：即将挂载到页面')
  },

  mounted() {
    console.log('[LifecycleDemo] mounted：已经挂载到页面')
  },

  // 更新前和更新完毕
  // 页面数据发生变化会触发。
  beforeUpdate() {
    console.log('[LifecycleDemo] beforeUpdate：数据变化，页面即将更新')
  },

  updated() {
    console.log('[LifecycleDemo] updated：页面已经更新')
  },

  // 销毁前和销毁完毕
  // v-if，组件卸载掉了，已经移除掉了DOM，此时会触发销毁。当重新可见时，会触发组件创建、挂载。
  // v-show，组件从可见到不可见,组件内容只是被隐藏了。不会触发销毁。
  beforeDestroy() {
    console.log('[LifecycleDemo] beforeDestroy：组件即将销毁')
  },

  destroyed() {
    console.log('[LifecycleDemo] destroyed：组件已经销毁')
  }
}
</script>

<style scoped>
.demo {
  margin-top: 24px;
  padding: 24px;
  border: 1px solid #42b983;
  border-radius: 8px;
  background: #f4fff9;
}

button {
  padding: 8px 16px;
  cursor: pointer;
}

.tip {
  color: #666;
  font-size: 14px;
}
</style>
