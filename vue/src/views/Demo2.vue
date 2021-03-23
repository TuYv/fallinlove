<template>
  <div>
    <h1>绑定class和style</h1>
    <div :class="classA ">绑定class</div>
    <div :class="{current: isCurrent, focus: isFocus}">通过对象绑定class</div>
    <div :style="styleObj">绑定style</div>
    <div :style="{color: 'red'}">绑定style</div>
  </div>
</template>
<script>
export default {
  name: "demo2",
  data () {
    return {
      classA: "current",
      isCurrent: false,
      isFocus: true,
      styleObj: {
        color: 'blue',
        font: '18'
      }
    }
  }
}
</script>

<style>

</style>