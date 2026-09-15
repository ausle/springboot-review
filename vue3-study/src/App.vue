
<!-- html结构 -->
<template>
      <div class="person">
        <h2 id="title">APP的标题</h2>
    </div>
    <!-- 
        这里的冒号‘：’，是v-bind的缩写。
        类似于：v-bind:personList="personList"
        把父组件的personList变量，传给子组件，传递是数组本身。
        如果不加冒号，则只是作为普通字符串传递。
    -->
    <Son :personList="personList1" :person="person" name="son"/>
</template>

<!-- script中写脚本 -->
 <!-- 
  1、<script setup> 不允许直接写 export default，组件会自动return出去。

  2、
 -->
<script lang="ts" setup name="App">
    import { ref,reactive } from 'vue';
import Demo1 from './components/Demo1.vue';
    import Demo2 from './components/Demo2.vue';
    import Demo3 from './components/Demo3.vue';
    import Demo4 from './components/Demo4.vue';
    import Son from './components/Demo5.vue';
    import {type Person} from '@/types'
    import {type Persons} from '@/types'

    // 声明变量时，需符合Person指定的属性名和类型。
    let person:Person={id:'1',name:'张三',age:18}

    //需符合数组规范
    let personList1:Array<Person>=reactive([
      {id:'1',name:'张三',age:18},
      {id:'2',name:'李四',age:19},
      {id:'3',name:'王五',age:20}])

    let personList2:Persons=reactive([
      {id:'10',name:'张三三',age:18},
      {id:'12',name:'李四四',age:19},
      {id:'13',name:'王五五',age:20}])

</script>


<!-- 样式 -->
<style scoped>

</style>
