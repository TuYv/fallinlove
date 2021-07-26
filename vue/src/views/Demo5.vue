<template>
  <div>
    <el-container>
      <el-header>记账 Demo</el-header>
      <el-main>
        <span>总金额：{{allAmount}}</span>
      <el-table :data="monthList" style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <div v-for="detail in props.row.monthAmountDetailList" :key="detail.id">
             {{detail.time}} {{detail.reason}}
             <template v-if="detail.amountType === '1'">收入</template>
             <template v-else>支出</template>
             {{detail.amount}} 
            </div>
          </template>
        </el-table-column>
        <el-table-column label="月份" prop="time"></el-table-column>
        <el-table-column label="收入" prop="income"></el-table-column>
        <el-table-column label="支出" prop="spend"></el-table-column>
      </el-table>
      <el-input-number v-model="money" label="请输入金额" />
    <el-input v-model="reason" placeholder="请输入备注" />
    <el-button type="success" round @click="income('1')">收入</el-button>
    <el-button type="danger" round @click="income('0')">支出</el-button>
      </el-main>
    </el-container>
      
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
import moment from 'moment'
export default {
  name: "demo5",
  data() {
    return {
      result: {},
      money: 0,
      allAmount: 0,
      monthList: [],
      number: 1,
      reason: "",
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
    // let user = JSON.parse(localStorage.getItem('user'));
    // if(!user) {
    //   this.$router.push('/login');
    // }
    this.getFinance()
  },
  methods: {
    getFinance() {
     let localUser = JSON.parse(localStorage.getItem('user'));
      this.$http.get("/finance/billing/" + localUser.accountId).then(response => {
        console.log(response.data)
        this.result = response.data
        this.allAmount = this.result.totalAmount
        this.monthList = this.result.monthAmountModelList

      })
    },

    income(type) {
      if (this.money === 0){
        alert("请输入正确的金额!!!")
      } else {
        let date = new Date();
        var insertFin = {}
        insertFin.id = this.result.id
        insertFin.totalAmount = parseFloat(this.allAmount) + parseFloat(this.money)
        insertFin.amountType = "1"
        insertFin.year = date.getFullYear() 
        insertFin.month = date.getMonth() + 1
        if (insertFin.month < 10) {
          insertFin.month = '0' + insertFin.month
        }
        insertFin.time = moment().format('YYYY-MM-DD HH:mm:ss')
        insertFin.reason = this.reason
        insertFin.amount = parseFloat(this.money)
        console.log(insertFin)
        this.$http.post("/finance/billing/insert", insertFin)

      }
    },
    spend() {
        let date = new Date();
        var insertFin = {}
        insertFin.id = this.result.id
        insertFin.totalAmount = parseFloat(this.allAmount) + parseFloat(this.money)
        insertFin.amountType = "0"
        insertFin.year = date.getFullYear() 
        insertFin.month = date.getMonth() + 1
        if (insertFin.month < 10) {
          insertFin.month = '0' + insertFin.month
        }
        insertFin.time = moment().format('YYYY-MM-DD HH:mm:ss')
        insertFin.reason = "测试阶段"
        insertFin.amount = parseFloat(this.money)
        console.log(insertFin)
        this.$http.post("/finance/billing/insert", insertFin)
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
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>