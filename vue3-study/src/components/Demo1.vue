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
  <div class="car">
    <h1>汽车信息</h1>
    <p>品牌：{{ car.brand }}</p>
    <p>价格：{{ car.price }}</p>
    <p>颜色：{{ car.color }}</p>
    <button @click="changePrice">改变价格</button>
  </div>

   <ul>
      <li v-for="game in games" :key="game.id">{{ game.name }}</li>
    </ul>
    <button @click="changeGame">改变游戏</button>

</template>

<!-- 
  
  1、安装该插件后，可以使用name属性来定义组件名称，避免了在<script setup>中使用export default的方式来定义组件名称。
     npm isntall vite-plugin-vue-setup-extend -D
   
  2、vue2中data中的数据默认是响应式的。


  3、ref和reactive的区别：
      ref是用来定义基本类型数据的响应式引用，而reactive是用来定义对象类型数据的响应式对象。
      ref也可以用来定义对象类型数据，但是需要通过.value来访问和修改对象的属性，而reactive可以直接访问和修改对象的属性。
      ref返回的是一个包含value属性的对象，而reactive返回的是一个响应式对象本身。
      

      ref可以处理，基本数据类型、对象数据类型，任何类型吗？
      reactive() 可以处理对象、数组、Map、Set，但不能处理字符串、数字、布尔值等基本类型。


  4、
  -->
<script setup lang="ts" name="Demo1">
    // 这种方式声明变量，数据是响应式的。修改数据后会触发视图更新。

    import { reactive, ref } from 'vue'
    // 定义响应式数据，使用ref函数来创建响应式引用
    const name = ref('张三')
    const age = ref(20)
    // 数据不需要变化，就不需要声明为响应式。
    let msg = 'Hello Vue3 + TypeScript + Vite'
    console.log('name:', name)
    console.log('age:', age)
    console.log('msg:', msg)
    
    const sayHello = () => {
        alert(`你好，我叫${name.value}，今年${age.value}岁`)
    },
    changeName = () => {
        name.value = '赵六'
    },
    changeAge = () => {
        age.value = 35
    }

    // 使得对象具备响应式的能力，使用reactive函数来创建响应式对象
    let car  = {
        brand: '宝马',
        price: 50,
        color: '黑色'
    }
    car = reactive(car)

    
    // const 限制的是不能让变量重新指向另一个对象。不是说对象的属性不能修改。所以声明为const的对象仍然可以修改其属性。
    const games=reactive([
      { id: 1, name: '英雄联盟' },
      { id: 2, name: '王者荣耀' },
      { id: 3, name: '原神' },
      { id: 4, name: '我的世界' }])

    const changePrice = () => {
        car.price = car.price + 10
    }

    const changeGame = () => {
      games[0].name = 'Dota2'
    }

</script>



<style scoped>
.person {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: aqua;
}
</style>