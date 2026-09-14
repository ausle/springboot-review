<template>
   <div class="person">
    <h1>监听ref类型的数据-基本类型</h1>
    <h2>当前求和为：{{ sum }}</h2>
    <button @click="changeSum">点我sum+1</button>
  </div>
  <hr/>
  <div class="car_ref">
    <h1>监听ref类型的数据-对象类型</h1>
    <p>品牌：{{ car.brand }}</p>
    <p>价格：{{ car.price }}</p>
    <p>颜色：{{ car.color }}</p>
    <button @click="changeColor">改变颜色</button>
    <button @click="changePrice">改变价格</button>
    <button @click="changeCar">改变车</button>
</div>
<hr/>
  <div class="car_reactive">
    <h1>监听reactive类型的数据-对象类型</h1>
    <p>品牌：{{ car_reactive.brand }}</p>
    <p>价格：{{ car_reactive.price }}</p>
    <p>颜色：{{ car_reactive.color }}</p>
    <button @click="changeColor_reactive">改变颜色</button>
    <button @click="changePrice_reactive">改变价格</button>
    <button @click="changeCar_reactive">改变车</button>
</div>

<hr/>
  <div class="car_per_attr">
    <h2>姓名：{{ person_attr.name }}</h2>
    <h2>年龄：{{ person_attr.age }}</h2>
    <h2>汽车：{{ person_attr.car.c1 }}、{{ person_attr.car.c2 }}</h2>

    <button @click="changePerName">修改名字</button>
    <button @click="changePerAge">修改年龄</button>
    <button @click="changePerC1">修改第一台车</button>
    <button @click="changePerC2">修改第二台车</button>
    <button @click="changePerCar">修改整个车</button>
  </div>
</template>
<!-- 

1、watch主要是监视数据的变化

2、vue3的watch只能监视以下四种数据：
    1、ref类型的数据
      - 基本类型
      - 对象类型
    2、reactive类型的数据
      - 对象类型
    
    3、函数返回值getter函数

    
    4、数组类型的数据

-->

<script lang="ts" setup name="Student">
  import { h, reactive, ref,watch } from 'vue'

    // 数据
  let sum = ref(0)
  function changeSum() {
    sum.value++
  }
  
  // （1）监视ref定义的基本类型数据
  const unwatch = watch(sum, (newValue, oldValue) => {
    console.log('sum的值发生了变化：', '新值为：', newValue, '旧值为：', oldValue)
    if (newValue === 10) {
      unwatch() // 取消监视
    }
  })


  // （2）监视ref定义的对象类型数据
    let car = ref({
        brand: '宝马',
        price: 50,
        color: '黑色'
    })


    function changeColor() {
      car.value.color +='~'  
    }

    function changePrice() {
      car.value.price +=100
    }

    function changeCar() {
      car.value = {
        brand: '奔驰',
        price: 100,
        color: '白色'
      }
    }

    //  监视ref定义的对象类型数据，监视的是对象的引用地址，只有当对象的引用地址发生变化时才会触发watch回调函数。如果想要监视对象的属性值的变化，需要使用deep选项=true。
    //  但如果只是修改对象的某个属性值（changePrice方法），其实对象的引用没变，newValue和oldValue输出的值是一样的。
    //  当修改整个对象（changeCar方法），newValue和oldValue的值才会是不一样的。
    const carWatcher = watch(car, (newValue, oldValue) => {
        console.log('car的值发生了变化：', '新值为：', newValue, '旧值为：', oldValue)
    }, { deep: true })


    // （3）监视reactive定义的对象类型数据
    let car_reactive = reactive({
        brand: '宝马',
        price: 50,
        color: '黑色'
    })

    function changeColor_reactive() {
      car_reactive.color +='~'  
    }
    function changePrice_reactive() {
      car_reactive.price +=100
    }

    // 
    /* 
      reactive定义的对象，不能通过以下方式进行整体修改：
        car_reactive = {
            brand: '奔驰',
            price: 100,
            color: '白色'
        }
      只能通过Object.assign()方法来修改reactive定义的对象的属性值。修改的是属性值，不是改为一个新的对象。
    */
    function changeCar_reactive() {
      Object.assign(car_reactive, {
        brand: '奔驰',
        price: 100,
        color: '白色'
      })
    }
    // watch默认是监视对象引用的变化。但对于reactive定义的对象， 默认是开启deep=true的，因为reactive无法修改对象，只能修改对象的值。不开deep，watch没有意义。
    // 属性值发生变化，都会触发。newValue和oldValue的值都是一样的，因为对象的引用地址没变。
    const carRWatcher = watch(car_reactive, (newValue, oldValue) => {
        console.log('carRWatcher的值发生了变化：', '新值为：', newValue, '旧值为：', oldValue)
    })


    // （4）监视ref类型或reactive类型对象数据中的某个属性，属性需要保证为一个函数返回值，才能被监听到。
    let person_attr = reactive({
      name: '张三',
      age: 18,
      car: {
        c1: '奔驰',
        c2: '宝马'
      }
    })
    // 方法
    function changePerName() {
      person_attr.name += '~~~'
    }
    function changePerAge() {
      person_attr.age = 20
    }
    function changePerC1() {  
      person_attr.car.c1 = '奥迪'
    }
    function changePerC2() {
      person_attr.car.c2 = '比亚迪'
    }
    function changePerCar() {
      person_attr.car = {
        c1: '特斯拉', 
        c2: '蔚来'
      }
    }
    // 无法直接监视某个属性，需要包装成一个函数返回值。
    watch(() => person_attr.name, (newValue, oldValue) => {
      console.log('person_attr.name的值发生了变化：', '新值为：', newValue, '旧值为：', oldValue)
    })  
    // watch(() => {return person_attr.name}, (newValue, oldValue) => {
    //   console.log('========》person_attr.name的值发生了变化：', '新值为：', newValue, '旧值为：', oldValue)
    // })
    watch(()=> person_attr.name, (newValue, oldValue) => {
      console.log('========》person_attr.name的值发生了变化：', '新值为：', newValue, '旧值为：', oldValue)
    })

    // 监听响应式对象的对象属性car,car中的某个属性改变或者整个car对象改变都会触发watch回调函数。
    watch(()=>person_attr.car, (newValue, oldValue) => {
      console.log('========》person_attr.car的值发生了变化：', '新值为：', newValue, '旧值为：', oldValue)
    }, { deep: true })  


    // （5）监视多个值，监听一个数组。当name或car的属性值发生变化时，都会触发。
    watch(()=>[()=>person_attr.name,person_attr.car], (newValue, oldValue) => {
      console.log('========》监听一个数组的值发生了变化：', '新值为：', newValue, '旧值为：', oldValue)
    }, { deep: true })  


</script>


<style scoped>
.person {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}
</style>