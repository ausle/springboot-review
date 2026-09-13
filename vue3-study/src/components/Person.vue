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
        data() {
            return {
                msg: 'Hello Vue3 + TypeScript + Vite',
                name: '张三',
                age: 20
            }
        },
        methods: {
            sayHello() {
                console.log(`你好，我叫${this.name}，今年${this.age}岁`)
            },
            changeName() {
                this.name = '李四'
            },
            changeAge() {
                this.age = 30
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