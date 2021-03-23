
<template>
  <div>
    <h1>过滤器</h1>
    <div>{{content | uppercase}}</div>
  </div>
</template>
<script>
export default {
  name: "demo10",
  data() {
    return { 
      content: "dfhkjsdkfasdkl"
    }
  },
  filters: {
    // 改成放main.js的全局变量
    // uppercase (value) {
    //   return value.toUpperCase()
    // }
  }
}
</script>

<style>

</style>