<template>
  <div>
    <h1>v-for遍历数组</h1>

    <div v-for="(item, index) in arr" :key="index">{{index}}-{{item}}</div>

    <div v-for="(item, key, index) in obj" :key="index">{{index}}-{{key}}-{{item}}</div>

    <ul :style="{textAlign: 'left'}">
      <li v-for="item in Student" :key="item.id">{{item.name}}</li>
    </ul>
  </div>
</template>
<script>
export default {
  name: "demo4",
  data() {
    return {
      arr: [1,2,3,4,5],
      obj: {
        name: "张三",
        sex: "男",
        age: 20
      },
      Student: [
        {
          id: 1,
          name: 'Apill'
        },
        {
          id: 2,
          name: 'Bob'
        },
        {
          id: 3,
          name: 'Clack'
        },
        {
          id: 4,
          name: 'David'
        },
        {
          id: 5,
          name: 'Eirc'
        },
      ]
    }
  }
}
</script>

<style>

</style>