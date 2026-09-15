
<!-- html结构 -->
<template>
      <div class="person">
        <h2 id="title">APP的标题</h2>
    </div>
    <Demo4 />
</template>

<!-- script中写脚本 -->
 <!-- 
  1、<script setup> 不允许直接写 export default，组件会自动return出去。

  2、
 -->
<script lang="ts" setup name="App">
import Demo1 from './components/Demo1.vue';
import Demo2 from './components/Demo2.vue';
import Demo3 from './components/Demo3.vue';
import Demo4 from './components/Demo4.vue';
</script>


<!-- 样式 -->
<style scoped>

</style>
