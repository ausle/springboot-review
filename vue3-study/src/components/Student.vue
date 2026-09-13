<template>
  <div class="person">
    <h2>姓名：{{ person.name }}</h2>
    <h2>年龄：{{ person.age }}</h2>
    <h2>身高：{{ person.height }}</h2>
    <button @click="changeName">修改名字</button>
    <button @click="changeAge">修改年龄</button>
    <button @click="changeHeight">修改身高</button>
  </div>
  <hr />
    <div class="person">
      姓：<input type="text" v-model="firstName"> <br>
      名：<input type="text" v-model="lastName"> <br>
      全名：<span>{{ fullName }}</span> <br>
      <button @click="changeFullName">修改计算属性</button>
    </div>
</template>
<!-- 

  1、主要讲的是toRef和toRefs的使用

  2、什么是双向绑定?
    页面上输入的值修改时，数据也会跟着修改，数据修改时，页面上显示的值也会跟着修改，这种现象就叫做双向绑定.


  3、计算属性
    （1）计算属性，计算时依赖的数据发生变化时，计算属性就会重新计算。
     (2) 。

-->


<script lang="ts" setup name="Student">
  import { reactive, toRef, toRefs,ref,computed } from 'vue'

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


  let firstName = ref('')
  let lastName = ref('')

  // 计算属性，计算时依赖的数据发生变化时，计算属性就会重新计算
  // 这种写法的计算属性是只读的，不可以直接修改值
  // let fullName = computed(() => {
  //   return firstName.value.slice(0, 1).toUpperCase() +
  //     firstName.value.slice(1) +'-' +lastName.value
  // })


   // 此时计算属性是可读可写的，可以直接修改值
   let fullName = computed({  
    get() {
      return firstName.value.slice(0, 1).toUpperCase() +firstName.value.slice(1) + '-' + lastName.value
    },
    set(newValue: string) {
      let names = newValue.split('-')
      firstName.value = names[0]
      lastName.value = names[1]
    } 
  })  


  const changeFullName = () => {
    fullName.value = '王-五'
  }

</script>


<style scoped>
.person {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}
</style>