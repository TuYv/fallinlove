<template>
  <div>
    <h1>记账 Demo</h1>
    <ul>
      <li>总金额：{{allAmount}}</li>
        <li v-for="item in monthList" :key="item.id">
          {{item.time}} 收入 ： {{item.income}}
                        支出 ： {{item.spend}}
            <div v-for="detail in item.monthAmountDetailList" :key="detail.id">
             {{detail.time}} {{detail.reason}}
             <div v-if="detail.amountType === '1'">支出</div>
             <div v-else>收入</div>
             {{detail.amount}} 
            </div>
        </li>
    </ul>
    <input type="number" v-model="money" placeholder="请输入金额">
    <button @click="income">收入</button>
    <button @click="spend">支出</button>
    <h1>v-on 事件绑定</h1>
    <div>{{number}}</div>
    <button v-on:click="add" >加1</button>
    <button @click="reduce" >减1</button>
    <button @click="reduce(2)" >减2</button>

    <hr>

    <div v-if="isShow">isShow为true</div>
    <div v-else>isShow为false</div>
    <button @click="toggle">切换isShow</button>

    <hr>

    <ul>
      <li v-for="(item) in Student" :key="item.id">{{item}}</li>
    </ul>
    <button @click="addStu">添加学生</button>
    <button @click="removeStu">删除学生</button>
    <button @click="editStu">修改学生</button>

    <hr>

    <div v-for="(v, k, i) in obj" :key="k">
      {{i}} - {{k}} - {{v}}
    </div>
     
    <button @click="addAttr">添加属性</button>
    <button @click="removeAttr">删除属性</button>

  </div>
</template>
<script>
export default {
  name: "demo5",
  data() {
    return {
      money: 0,
      allAmount: 0,
      monthList: [],
      number: 1,
      isShow: true,
      Student: [
        {
          id: 1,
          name: 'Apill'
        },
      ],
      obj: {
        name: "Musk",
        age: 20,
        sex: "男"
      }
    }
  },
  created() {
      this.$http.get("/finance/index/1").then(response => {
        var data = response.data
        this.allAmount = data.totalAmount
        this.monthList = data.monthAmountModelList

      })
  },
  methods: {
    income() {
      if (this.money === 0){
        alert("请输入正确的金额!!!")
      } else {
        let date = new Date();
        var insertFin = {}
        insertFin.id = this.allAmount.id
        insertFin.totalAmount = this.allAmount.totalAmount + this.money
        insertFin.amountType = "0"
        insertFin.year = date.getFullYear
        insertFin.month = date.getMonth
        insertFin.time = date.getFullYear + '-' + date.getMonth + '-' + date.getDay + ' ' + date.getHours + ' ' + date.getHours + ' ' + date.getMinutes
        insertFin.reason = "测试阶段"
        insertFin.money = this.money
        console.log(insertFin)
        this.$http.post("/finance/insert", insertFin)

      }
    },
    spend() {
      var insertFin = {}
      insertFin.id = this.allAmount.id
      insertFin
      this.$http.post()
    },
    add () {
      this.number++;
    },
    reduce(val) {
      if(typeof val === 'number') {
        this.number-= val
      } else {
        this.number--
      }
    },
    toggle() {
      this.isShow = !this.isShow
    },
    addStu() {
      //shift 从列表头添加   push 从列表尾添加
      this.Student.push({
        id: this.Student.length+1,
        title: "Musk"
      })
    },
    removeStu() {
      //同上 unshift/pop
      this.Student.pop()
    },
    editStu() {
      //  vue 不能直接替换修改列表里面的对象
      // this.Student[0] = {
      //   id: Date.now(),
      //   name: "Musk"
      // }

      //可以这样实现 @set(要替换的数组，替换的对象下标， 替换的对象)
      // this.$set(this.Student, 0, {
      //   id: Date.now(),
      //   name: "Musk"
      // })

      // 也可以这样  splice(替换的下标，替换的数量， 替换的对象)
      this.Student.splice(0, 2, {
        id: Date.now(),
        name: "Musk"
      })
    },
    addAttr() {
      // 也不行
      //this.obj.hobby= "twitter"

      this.$set(this.obj, 'hobby', 'twitter')
      this.obj = {
        ...this.obj,
        hobby: 'twitter'
      }
    },
    removeAttr()  {
      let _obj = {...this.obj};
      delete _obj.age;
      this.obj = {..._obj}
    }
  }
}
</script>

<style>

</style>