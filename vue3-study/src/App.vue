
<!-- html结构 -->
<template>
    <Student />
</template>

<!-- script中写脚本 -->
 <!-- 
  1、<script setup> 不允许直接写 export default，因为它本身已经会自动生成组件配置。
 -->
<script lang="ts">
import Person from './components/Person.vue';
import Student from './components/Student.vue';

    export default {
        name: 'App',
        // 引入进来还需要在components中注册才能使用
        components: {
            Person,Student
        },
        data() {
            return {
                msg: 'Hello Vue3 + TypeScript + Vite'

            }
        }
    } 
</script>


<!-- 样式 -->
<style scoped>

</style>
