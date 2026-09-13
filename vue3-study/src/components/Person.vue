<template>
  <div class="person">
    <h1>人物信息</h1>
    <p>姓名：{{ name }}</p>
    <p>年龄：{{ age }}</p>
    <button @click="changeName">改变姓名</button>
    <button @click="changeAge">改变年龄</button>
    <button @click="sayHello">打招呼</button>
  </div>
  <hr />
  <h2>{{ msg }}</h2>
</template>

<!-- 
  
  1、安装该插件后，可以使用name属性来定义组件名称，避免了在<script setup>中使用export default的方式来定义组件名称。
     npm isntall vite-plugin-vue-setup-extend -D
   
  
  -->
<script setup lang="ts" name="PersonSuper">
    // 这种方式声明变量，数据是响应式的。修改数据后会触发视图更新。
    import { ref } from 'vue'
    const name = ref('张三')
    const age = ref(20)
    const msg = ref('Hello Vue3 + TypeScript + Vite')
    const sayHello = () => {
        alert(`你好，我叫${name.value}，今年${age.value}岁`)
    },
    changeName = () => {
        name.value = '赵六'
    },
    changeAge = () => {
        age.value = 35
    }
</script>



<style scoped>
.person {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}
</style>