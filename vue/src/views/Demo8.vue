<template>
  <div>
    <h1>双向绑定v-model</h1>
    <input type="text" v-model="text">
    <div>{{text}}</div>
    <input type="text" v-model.lazy="text">
    <div>修饰符 lazy: 失去焦点后才会绑定{{text}}</div>
    <input type="text" v-model.number="text">
    <div>修饰符 number: 转成数字{{text}}</div>
    <input type="text" v-model.trim="text">
    <div>修饰符 trim: 去除前后空格{{text}}</div>
    <hr>

    <select v-model="cityCode">
      <option value="">请选择</option>
      <option v-for="city in citys" :value="city.code" :key="city.code">{{city.name}}</option>
    </select>
    <div>{{cityName}}</div>
    <hr>

    <!-- 绑定radio -->
     <input type="radio" value="male" name="sex" v-model="sex"> 男
     <input type="radio" value="female" name="sex" v-model="sex"> 女
     <div>{{sex}}</div>
     <hr>

     <!-- 绑定checkbox -->
     <input type="checkbox" name="" v-model="remember">记住密码 
     <div>{{remember}}</div>
     <input type="checkbox" value="cf" v-model="hobby">吃饭
     <input type="checkbox" value="sj" v-model="hobby">睡觉
     <input type="checkbox" value="ddd" v-model="hobby">打豆豆
     <div>{{hobby.join(' | ')}}</div>
  </div>
</template>
<script>
export default {
  name: "demo8",
  data() {
    return {
      text: "abc",
      citys: [
        {
          name: "北京",
          code: "bj"
        },{
          name: "上海",
          code: "sh"
        },{
          name: "广州",
          code: "gz"
        },{
          name: "深圳",
          code: "sz"
        },
      ],
      cityCode: "",
      sex:"",
      remember: true,
      hobby: []
    }
  },
  computed: {
    cityName() {
      let _city = this.citys.find(city => city.code === this.cityCode)
      return _city ?  _city.name : ""
    }
  },
  methods: {
  }
}
</script>

<style>

</style>