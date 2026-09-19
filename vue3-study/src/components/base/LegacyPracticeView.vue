<template>
  <div class="practice-page">
    <div class="person">
      <h2 id="title">APP的标题</h2>
    </div>

    <Son :personList="personList1" :person="person" name="son" />

    <hr />
    <button @click="showDemo6 = !showDemo6">
      {{ showDemo6 ? '隐藏 Demo6' : '显示 Demo6' }}
    </button>
    <Demo6 v-if="showDemo6" />
  </div>
</template>

<script lang="ts" setup name="LegacyPracticeView">
import { reactive, ref } from 'vue'
import Demo1 from '@/components/base/Demo1.vue'
import Demo2 from '@/components/base/Demo2.vue'
import Demo3 from '@/components/base/Demo3.vue'
import Demo4 from '@/components/base/Demo4.vue'
import Son from '@/components/base/Demo5.vue'
import Demo6 from '@/components/base/Demo6.vue'
import type { Person, Persons } from '@/types'

// 这些 Demo1~Demo4 导入暂时保留，后续可以在这个练习页继续组合展示。
void Demo1
void Demo2
void Demo3
void Demo4

const person: Person = { id: '1', name: '张三', age: 18 }

const personList1: Array<Person> = reactive([
  { id: '1', name: '张三', age: 18 },
  { id: '2', name: '李四', age: 19 },
  { id: '3', name: '王五', age: 20 },
])

const personList2: Persons = reactive([
  { id: '10', name: '张三三', age: 18 },
  { id: '12', name: '李四四', age: 19 },
  { id: '13', name: '王五五', age: 20 },
])

void personList2

const showDemo6 = ref(true)
</script>

<style scoped>
.practice-page {
  padding: 20px;
}

.person {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}

button {
  padding: 8px 16px;
  cursor: pointer;
}
</style>
