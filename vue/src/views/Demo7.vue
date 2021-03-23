<template>
  <div>
    <h1>计算属性和监听属性</h1>
    <div>
      {{firstName + "·" + lastName}}
    </div>
    <div>
      computed计算属性简化表达式<br>  {{fullName}}
    </div>
    <button @click="modifyFullName">修改姓名</button>
    <button @click="switchLanguage()">{{language}}</button>
  </div>
</template>
<script>
export default {
  name: "demo7",
  data() {
    return {
      firstName: "埃隆",
      lastName: "马斯克",
      language: "英文"
    }
  },
  computed: {
    fullName: {
      get() {
        return this.firstName +"·" + this.lastName
      },
      set(newVal) {
        let arr = newVal.split("·")
        this.firstName = arr[0]
        this.lastName = arr[1]
      }
    }
  },
  methods: {
    modifyFullName () {
      if(this.fullName === "埃隆·马斯克") {
        this.fullName = "云·马"
      } else if (this.fullName === "云·马") {
        this.fullName = "埃隆·马斯克"
      }
      this.fullName = "云·马"
    },
    switchLanguage (){
      if(this.language === "英文") {
        this.language = "Chinese"
      } else if (this.language === "Chinese") {
        this.language = "英文"
      }
    }
  },
  watch: {
    language(newVal) {
      if(newVal === "英文") {
        this.fullName = "埃隆·马斯克"
      } else if(newVal === "Chinese") {
        this.fullName = "Elon·Musk"
      }
    }
  }
}
</script>

<style>

</style>