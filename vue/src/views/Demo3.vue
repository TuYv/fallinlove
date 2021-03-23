<template>
  <div>
    <h1>v-if判断</h1>
    <div v-if="isShow">isShow为true</div>
    <div v-else-if="isElse">isShow为false时 isElse为true</div>
    <div v-else>所有的都为false时</div>

    <template v-if="isShow">
      <div>template元素是占位符 不会渲染到dom中</div>
      <div>可作用于需要多div做统一配置</div>
    </template>

    <div v-show="isShow">v-show不能绑定template 同时它的false只是隐藏不是像if的删除</div>

  </div>
</template>
<script>
export default {
  name: "demo3",
  data() {
    return {
      isShow: true,
      isElse: true
    }
  },
  mounted() {
    setTimeout(() => {
      this.isShow = false
    }, 5000)
    setTimeout(() => {
      this.isElse = false
    }, 8000)
    
  }
}
</script>

<style>

</style>