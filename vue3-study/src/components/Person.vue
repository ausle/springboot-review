<template>
  <div class="person">
    <h1>人物信息</h1>
    <p>姓名：{{ name }}</p>
    <p>年龄：{{ age }}</p>
    <button @click="changeName">改变姓名</button>
    <button @click="changeAge">改变年龄</button>
    <button @click="sayHello">打招呼</button>
  </div>
</template>

<!-- 
  script setup 会自动导出组件。直接import就可以使用。
  setup函数中，不存在this。
-->
<script lang="ts">
    export default {
        name: 'Person',
        setup() {
            // 这种方式声明变量，数据不是响应式的。修改数据后不会触发视图更新。
            let name = '王五'
            let age = 25
            const sayHello = () => {
                alert(`你好，我叫${name}，今年${age}岁`)
            },
            changeName = () => {
                name = '李四'
            },
            changeAge = () => {
                age = 30
            }
            return {
                name,
                age,
                sayHello,
                changeName,
                changeAge
            }
        }
    } 
</script>

<style scoped>
.person {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}
</style>