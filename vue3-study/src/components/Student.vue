<template>
  <div class="person">
    <h2>姓名：{{ person.name }}</h2>
    <h2>年龄：{{ person.age }}</h2>
    <h2>身高：{{ person.height }}</h2>
    <button @click="changeName">修改名字</button>
    <button @click="changeAge">修改年龄</button>
    <button @click="changeHeight">修改身高</button>
  </div>
</template>
<!-- 

  1、主要讲的是toRef和toRefs的使用
  

-->


<script lang="ts" setup name="Student">
  import { reactive, toRef, toRefs } from 'vue'

  let person = reactive({
    name: '张三',
    age: 18,
    height: 188
  })


  // 解构出来的值，不再是响应式的了
  // let { name, age } = person

  // toRefs可以将响应式对象的属性转换为响应式引用，解构出来的值还是响应式的
  let { name, age } = toRefs(person)

  // 也可使用toRef将单个属性转换为响应式引用
  let height=toRef(person, 'height') 

  function changeName() {
    // person.name = '李四'
    name.value = '李四'
  }

  function changeAge() {
    // person.age = 25
    age.value = 25
  }
  function changeHeight() {
    height.value = 200
  }
</script>


<style scoped>
.person {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}
</style>