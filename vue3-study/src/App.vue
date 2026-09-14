
<!-- html结构 -->
<template>
    <Demo3 />
</template>

<!-- script中写脚本 -->
 <!-- 
  1、<script setup> 不允许直接写 export default，因为它本身已经会自动生成组件配置。
 -->
<script lang="ts">
import Demo1 from './components/Demo1.vue';
import Demo2 from './components/Demo2.vue';
import Demo3 from './components/Demo3.vue';

    export default {
        name: 'App',
        // 引入进来还需要在components中注册才能使用
        components: {
            Demo1,Demo2,Demo3
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
