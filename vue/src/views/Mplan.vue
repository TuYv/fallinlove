<template>
  <div class="home">
    <div class="right">
      <el-date-picker v-model="time"
                      type="date"
                      placeholder="选择日期"
                      format="yyyy 年 MM 月 dd 日"
                      value-format="yyyy-MM-dd"
                      style="width:80%">
      </el-date-picker>

      <el-input v-model="newTag"
                placeholder="请输入标签"
                @blur="changeNewTag()"
                style="width:80%; padding-top:4%" />

      <el-input v-model="reason"
                placeholder="请输入备注"
                style="width:80%; padding-top:4%" />
      <div style="width:100%">
        <el-input-number v-model="money"
                         placeholder="请输入金额"
                         style="width:80%; margin-top:4%;background: #C4C4C4;" />
      </div>
      <el-button type="success"
                 circle
                 @click="income('1')"
                 style="float: left;margin-left:25%;margin-top:8%">收</el-button>

      <el-button type="danger"
                 circle
                 @click="income('0')"
                 style="float: right;margin-right:25%;margin-top:8%">支</el-button>

      <div style="margin-top: 80px">
        <div style="width: 100%; height: 280px;margin:auto;padding: auto; "
           ref="charts"></div>
      </div>
    </div>

    <div class="left">
      <div v-if="monthPlanList.length > 0">
        <div v-for="(monthPlan,index) in monthPlanList"
             :key="index">
          <el-progress :percentage="parseFloat(((monthPlan.usedAmount/monthPlan.planAmount)*100).toFixed(2))"
                       :text-inside="true"
                       :stroke-width="25"
                       color="green"
                       style="width:80%; margin:5%" />
          {{monthPlan.planType}} :: {{monthPlan.planAmount}} :: {{monthPlan.usedAmount}}
        </div>
      </div>
    </div>
    <div class="root">
      <span>{{word.hitokoto}}</span>
      <el-divider content-position="right">{{word.from}}</el-divider>
    </div>
  </div>
</template>

<script>
import moment from 'moment'
import baseProgress from '../components/base-progress.vue'
const echarts = require('echarts')
export default {
  components: { baseProgress },
  name: 'finance',
  data() {
    return {
      result: {},
      money: 0,
      allAmount: 0,
      monthList: [],
      tagList: [],
      planList: [],
      monthPlanList: [],
      reason: '',
      tag: '',
      newTag: '',
      planName: '',
      purposes: 0,
      monthPlanType: '',
      monthPlanAmount: 0,
      saveMoney: 0,
      insertPlanType: '',
      time: new Date(),
      word: [],
      timer: null,

      myChart: {}
    }
  },
  created() {
    document.title = '极简记账'
    this.getFinance()
    this.getTagAmount()
    this.getPlan()
    this.getMonthPlan()
    this.timer = setInterval(() => {
      this.getTheWord()
    }, 10000)
  },
  beforeDestroy() {
    clearInterval(this.timer)
  },
  mounted() {
    document.addEventListener('visibilitychange', () => {
      if (document.hidden) {
        // 隐藏
        document.title = '等待中...'
        clearInterval(this.timer)
      } else {
        // 显示
        document.title = '极简记账'
        // this.timer = setInterval(() => {
        //   this.getTheWord()
        // }, 10000)
      }
    })

    window.onresize = () => {
      this.myChart.resize()
    }
  },
  methods: {
    getTheWord() {
      this.$http
        .get('https://v1.hitokoto.cn?c=d&c=h&c=i&c=k')
        .then((response) => {
          this.word = response
          console.log(response)
        })
    },
    addAmount() {
      if (this.saveMoney === undefined || this.saveMoney === 0) {
        alert('0元无法存入')
      } else if (this.saveMoney > this.allAmount) {
        alert('余额不足，无法完成存入')
      } else {
        let localUser = JSON.parse(localStorage.getItem('user'))
        this.$http
          .get('/finance/plan/amount/add', {
            params: {
              accountId: localUser.accountId,
              planId: this.planList[0].planId,
              amount: this.saveMoney,
            },
          })
          .then(() => {
            this.getPlan()
          })
      }
    },
    insertMonthPlan() {
      if (this.monthPlanType === '' || this.monthPlanAmount === 0) {
        alert('预算类型和金额不能为空')
      } else {
        let localUser = JSON.parse(localStorage.getItem('user'))
        var newMonthPlan = {}
        newMonthPlan.accountId = localUser.accountId
        newMonthPlan.planType = this.monthPlanType
        newMonthPlan.planAmount = this.monthPlanAmount
        this.$http
          .post('/finance/plan/monthPlan/insert', newMonthPlan)
          .then((response) => {
            this.newMonthPlan = {}
            this.monthPlanType = ''
            this.monthPlanAmount = 0
            this.getMonthPlan()
          })
      }
    },
    getMonthPlan() {
      let localUser = JSON.parse(localStorage.getItem('user'))
      this.$http
        .get('/finance/plan/monthPlan/query', {
          params: {
            accountId: localUser.accountId,
          },
        })
        .then((response) => {
          console.log(response)
          this.monthPlanList = response.data
        })
    },
    insertPlan() {
      if (this.planName === '' || this.purposes === 0) {
        alert('计划名和目标金额不能为空')
      } else {
        let localUser = JSON.parse(localStorage.getItem('user'))
        var newPlan = {}
        newPlan.accountId = localUser.accountId
        newPlan.planName = this.planName
        newPlan.purposes = this.purposes
        this.$http.post('/finance/plan/insert', newPlan).then((response) => {
          this.newPlan = {}
          this.planName = ''
          this.purposes = 0
          this.getPlan()
        })
      }
    },
    getPlan() {
      let localUser = JSON.parse(localStorage.getItem('user'))
      this.$http
        .get('/finance/plan/query', {
          params: {
            accountId: localUser.accountId,
          },
        })
        .then((response) => {
          console.log(response)
          this.planList = response.data
        })
    },
    getTagAmount() {
      let localUser = JSON.parse(localStorage.getItem('user'))
      this.$http
        .get('/finance/billing/monthTag', {
          params: {
            accountId: localUser.accountId,
          },
        })
        .then((response) => {
          let array = []
          response.data.forEach((item) => {
            array.push({
              name: item.tagName,
              value: item.amount,
            })
          })
          console.log('转换后', array)
          this.myChart = echarts.init(this.$refs.charts)
          // 绘制图表
          this.myChart.setOption({
            tooltip: {
              trigger: 'item',
            },
            legend: {
              top: '70%',
              left: 'center',
            },
            series: [
              {
                name: '花费',
                type: 'pie',
                radius: ['20%', '50%'],
                center: ['50%', '35%'],
                avoidLabelOverlap: false,
                itemStyle: {
                  borderRadius: 10,
                  borderColor: 'd3cece',
                  borderWidth: 0,
                },
                label: {
                  show: false,
                  position: 'center',
                },
                emphasis: {
                  label: {
                    show: true,
                    fontSize: '40',
                    fontWeight: 'bold',
                  },
                },
                labelLine: {
                  show: false,
                },
                data: array,
              },
            ],
          })
          this.myChart.on('click', function (params) {
            // 控制台打印数据的名称
            console.log(params.name)
          })
        })
    },
    changeNewTag() {
      this.tag = ''
    },
    changeTag() {
      this.newTag = ''
    },
    getFinance() {
      let localUser = JSON.parse(localStorage.getItem('user'))
      this.$http
        .get('/finance/billing/' + localUser.accountId)
        .then((response) => {
          console.log(response.data)
          this.result = response.data
          this.allAmount = this.result.totalAmount
          this.monthList = this.result.monthAmountModelList
          console.log(this.result.tags)
          this.tagList = this.result.tags

          let monthArray = []
          let incomeArray = []
          let outArray = []
          let lastArray = []
          console.log('收支表log', this.monthList)
          this.monthList.forEach((item) => {
            monthArray.push(item.time)
            incomeArray.push(item.income)
            outArray.push(item.spend)
            lastArray.push((item.income - item.spend).toFixed(2))
          })
          let monthAmount = echarts.init(this.$refs.monthAmount)
          monthAmount.setOption({
            title: {
              text: '收支图',
            },
            tooltip: {
              trigger: 'axis',
            },
            legend: {
              data: ['收入', '支出', '盈余'],
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true,
            },
            toolbox: {
              feature: {
                saveAsImage: {},
              },
            },
            xAxis: {
              type: 'category',
              boundaryGap: false,
              data: monthArray,
            },
            yAxis: {
              type: 'value',
            },
            series: [
              {
                name: '收入',
                type: 'line',
                data: incomeArray,
              },
              {
                name: '支出',
                type: 'line',
                data: outArray,
              },
              {
                name: '盈余',
                type: 'line',
                data: lastArray,
              },
            ],
          })
        })
    },

    income(type) {
      if (this.money === 0) {
        alert('请输入正确的金额!!!')
      } else {
        var insertFin = {}
        insertFin.id = this.result.id
        insertFin.totalAmount =
          parseFloat(this.allAmount) + parseFloat(this.money)
        insertFin.amountType = type
        this.time = moment(this.time).format('YYYY-MM-DD HH:mm:ss')
        insertFin.year = this.time.substring(0, 4)
        insertFin.month = this.time.substring(5, 7)
        insertFin.time = this.time
        console.log('time = ' + this.time)
        insertFin.reason = this.reason
        if (this.newTag != '') {
          console.log('newTag = ' + this.newTag)
          insertFin.tagName = this.newTag
        } else {
          console.log('tag = ' + this.tag)
          insertFin.tagName = this.tag
        }
        insertFin.amount = parseFloat(this.money)
        console.log(this.insertPlanType)
        if (type === '0') {
          insertFin.monthPlanId = this.insertPlanType
        }
        console.log(insertFin)
        this.$http
          .post('/finance/billing/insert', insertFin)
          .then((response) => {
            this.insertFin = {}
            this.newTag = ''
            this.tag = ''
            this.money = 0
            this.insertPlanType = 0
            this.reason = ''
            this.getFinance()
          })
      }
    },
  },
}
</script>

<style scoped>
.home {
  padding-left: 10%;
  padding-right: 10%;
  width: 100%;
  height: 100%;
  background: #ffffff;
}
.home .right {
  padding-top: 4%;
  float: left;
  width: 40%;
  height: 95%;
  background: #ffffff;
}
.home .left {
  float: left;
  width: 50%;
  height: 95%;
  background: #ffffff;
}
.home .root {
  float: left;
  position: absolute;
  bottom: 0%;
  width: 50%;
  height: 5%;
  background: #ffffff;
}
</style>