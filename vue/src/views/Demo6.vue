<template>
  <div>
    <h1>事件修饰符</h1>

    <div @click.prevent="fatherClick">
      父级
      <div @click.self="subClick1">子级会与引用父级1
        <div @click="ssubClick">self修饰符只有自己点击才有效</div>
      </div>
      <!-- 阻止冒泡修饰符 -->
      <div @click.stop="subClick2"> 子级会与引用父级2</div>
    </div>

    <hr>
    <!-- 阻止默认事件 -->
    <div @click.right.prevent="rightClick">禁止点击右键</div>
    <input type="text" @keydown.ctrl.prevent="ctrlEvent">
    <input type="text" @keydown.enter.prevent="enterEvent">

  </div>
</template>
<script>
export default {
  name: "demo6",
  data() {
    return {
    }
  },
  methods: {
    fatherClick() {
      console.log("父级调用")
    },
    subClick1() {
      console.log("子级1调用")
    },
    subClick2() {
      console.log("子级2调用")
    },
    ssubClick() {
      console.log("子级1的子级调用")
    },
    rightClick() {
      console.log("阻止右键")
    },
    ctrlEvent() {
      console.log("阻止ctrl键")
    },
    enterEvent() {
      console.log("阻止enter键")
    }
  }
}
</script>

<style>

</style>