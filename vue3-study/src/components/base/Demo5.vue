<template>
  <div class="person">
      <ul>
        <li v-for="person in props.personList" :key="person.id">
          {{ person.name }}--{{ person.age }}
        </li>
      </ul>
  </div>
</template>


<script lang="ts" setup name="Son">
    import { ref, watch, watchEffect } from 'vue'
    import {type Persons} from '@/types'
    import {type Person} from '@/types'

    /**
        1、props的使用：、接收父组件传过来的值，处理父组件传过来的值。

    */ 


    // defineProps 是 <script setup> 专用的编译宏，不需要导入。
    // 仅接收值，但不使用
    // defineProps(['personList'])

    // 接收父组件的多个值，并且限定接收的值的类型。如果父组件值传递错误，在编译器就会有报错提示。
    const props = defineProps<{
      personList: Persons
      person:Person,
      name: string
    }>()

    console.log('来自父组件的值：', props.name)
    console.log('来自父组件的值：',props.person)
    console.log('来自父组件的值：',props.personList)
    

</script>




<style scoped>
.person {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: aqua;
}
</style>