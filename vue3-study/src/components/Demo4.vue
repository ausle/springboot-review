<template>
  <div class="person">
    <h2 ref="title">需求：当水温达到60度，或水位达到80cm时，给服务器发请求</h2>
    <h2>当前水温：{{ temp }}℃</h2>
    <h2>当前水位：{{ height }}cm</h2>
    <h2>当前河流：{{ river }}</h2>
    <button @click="changeTemp">水温+10</button>
    <button @click="changeHeight">水位+10</button>
    <button @click="changeRiver">改变河流</button>
    <button @click="changeH2">输出h2这个元素</button>
  </div>
</template>


<script lang="ts" setup name="Demo4">
import { ref, watch, watchEffect } from 'vue'
/**
  1、watch和watchEffect的区别，watchEffect的特点


  2、ref的使用


  3、scoped局部样式
       限定样式的范围，只在当前页面生效。

 */



  // 数据
  let temp = ref(10)
  let height = ref(0)
  let river = ref('长江')

  // 方法
  function changeTemp() {
    temp.value += 10
  }

  function changeHeight() {
    height.value += 10
  }

  function changeRiver() {
    river.value = '黄河'
  }

  // 1、watch和watchEffect的区别

  // watch需要指定监听哪些值，默认不会初始化执行，只有数据改变会执行。
  watch([temp,height],(result)=>{
      let [temp,height] = result
      if(temp >=60 || height>=80){
        console.log('watch发送网络请求')
      }
  })

  // watch这种写法，如果监听的值非常多，就会非常麻烦。
  // 使用watchEffect，在回调函数中指定有哪些值，就会监视哪些值。
  // 当这些值发生变化时，就会调用回调函数。（比如改变河流，就不会触发watchEffect）
  // watchEffect默认会初始化执行，会首先执行一次。
  watchEffect(()=>{
    console.log('watchEffect run')
    if(temp.value >=60 || height.value>=80){
        console.log('watchEffect发送网络请求')
      }
  })


  // 2、ref使用

  // 创建一个名为title的容器，用于存储ref=title标记的内容。注意变量名需要和ref中的名称相同。
  // 整个DOM元素会存在名为title的容器里。
  let title=ref()

  
  // ref标记在html标签，可以获得标签的内容。
  // ref标记在vue组件上，在 Vue 中，把 ref 标记在一个子组件上，父组件可以获得这个子组件的“组件实例”，从而调用子组件暴露出来的方法或读取暴露出来的数据。

  function changeH2() {
    // 直接根据id，获取id为title的DOM元素：<h2 id="title">需求：当水温达到60度，或水位达到80cm时，给服务器发请求</h2>
    // 但是这样做会有很大的问题，如果其他页面引入了该页面，也有一个id为title，那么此时获得到的DOM元素就不是本页面的元素。
    // 怎么解决?解决的方法是使用ref。
    // console.log(document.getElementById("title"))

    // 即使其他页面也有同名的ref，也不会受到影响，获取的是本页面被ref标记的内容。
    // 输出ref=title的DOM
    console.log(title.value)
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