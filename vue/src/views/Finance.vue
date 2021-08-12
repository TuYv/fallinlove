<template>
  <div>
    <el-container>
      <el-header>记账 Demo</el-header>
      <el-main>
        <span>总金额：{{ allAmount }}</span>
        <el-table :data="monthList" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <div
                v-for="detail in props.row.monthAmountDetailList"
                :key="detail.id"
              >
                {{ detail.time }} {{ detail.reason }}
                <template v-if="detail.amountType === '1'">收入</template>
                <template v-else>支出</template>
                {{ detail.amount }}
              </div>
            </template>
          </el-table-column>
          <el-table-column label="月份" prop="time"></el-table-column>
          <el-table-column label="收入" prop="income"></el-table-column>
          <el-table-column label="支出" prop="spend"></el-table-column>
        </el-table>
        <div style="margin-top: 20px">
          <el-radio-group v-model="tag" size="small" @change="changeTag()">
            <el-radio-button
              :label="tags.tagName"
              v-for="tags in tagList"
              :key="tags.id"
            ></el-radio-button>
          </el-radio-group>
        </div>
        <el-date-picker
          v-model="time"
          type="date"
          placeholder="选择日期"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
        <el-input-number v-model="money" label="请输入金额" />
        <el-input
          v-model="newTag"
          placeholder="请输入标签"
          @blur="changeNewTag()"
        />
        <el-input v-model="reason" placeholder="请输入备注" />
        <el-button type="success" round @click="income('1')">收入</el-button>
        <el-button type="danger" round @click="income('0')">支出</el-button>

        <div style="width: 500px; height: 500px" ref="monthAmount"></div>
        <div style="width: 500px; height: 500px" ref="charts"></div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import moment from "moment";
const echarts = require("echarts");
export default {
  name: "finance",
  data() {
    return {
      result: {},
      money: 0,
      allAmount: 0,
      monthList: [],
      tagList: [],
      reason: "",
      tag: "",
      newTag: "",
      time: new Date(),
    };
  },
  created() {
    this.getFinance();
    this.getTagAmount();
  },
  methods: {
    getTagAmount() {
      this.$http.get("/finance/billing/monthTag").then((response) => {
        let array = [];
        response.data.forEach((item) => {
          array.push({
            name: item.tagName,
            value: item.amount,
          });
        });
        console.log("转换后", array);
        let myChart = echarts.init(this.$refs.charts);
        // 绘制图表
        myChart.setOption({
          tooltip: {
            trigger: "item",
          },
          legend: {
            top: "5%",
            left: "center",
          },
          series: [
            {
              name: "花费",
              type: "pie",
              radius: ["40%", "70%"],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: "#fff",
                borderWidth: 2,
              },
              label: {
                show: false,
                position: "center",
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: "40",
                  fontWeight: "bold",
                },
              },
              labelLine: {
                show: false,
              },
              data: array,
            },
          ],
        });
        myChart.on('click', function (params) {
    // 控制台打印数据的名称
    console.log(params.name);
    });
      });
    },
    changeNewTag() {
      this.tag = "";
    },
    changeTag() {
      this.newTag = "";
    },
    getFinance() {
      let localUser = JSON.parse(localStorage.getItem("user"));
      this.$http
        .get("/finance/billing/" + localUser.accountId)
        .then((response) => {
          console.log(response.data);
          this.result = response.data;
          this.allAmount = this.result.totalAmount;
          this.monthList = this.result.monthAmountModelList;
          console.log(this.result.tags);
          this.tagList = this.result.tags;

          let monthArray = [];
          let incomeArray = [];
          let outArray = [];
          let lastArray = [];
          console.log("收支表log", this.monthList);
          this.monthList.forEach((item) => {
            monthArray.push(item.time);
            incomeArray.push(item.income);
            outArray.push(item.spend);
            lastArray.push((item.income - item.spend).toFixed(2));
          });
          let monthAmount = echarts.init(this.$refs.monthAmount);
          monthAmount.setOption({
            title: {
              text: "收支图",
            },
            tooltip: {
              trigger: "axis",
            },
            legend: {
              data: ["收入", "支出", "盈余"],
            },
            grid: {
              left: "3%",
              right: "4%",
              bottom: "3%",
              containLabel: true,
            },
            toolbox: {
              feature: {
                saveAsImage: {},
              },
            },
            xAxis: {
              type: "category",
              boundaryGap: false,
              data: monthArray,
            },
            yAxis: {
              type: "value",
            },
            series: [
              {
                name: "收入",
                type: "line",
                data: incomeArray,
              },
              {
                name: "支出",
                type: "line",
                data: outArray,
              },
              {
                name: "盈余",
                type: "line",
                data: lastArray,
              },
            ],
          });
        });
    },

    income(type) {
      if (this.money === 0) {
        alert("请输入正确的金额!!!");
      } else {
        var insertFin = {};
        insertFin.id = this.result.id;
        insertFin.totalAmount =
          parseFloat(this.allAmount) + parseFloat(this.money);
        insertFin.amountType = type;
        this.time = moment(this.time).format("YYYY-MM-DD HH:mm:ss");
        insertFin.year = this.time.substring(0, 4);
        insertFin.month = this.time.substring(5, 7);
        insertFin.time = this.time;
        console.log("time = " + this.time);
        insertFin.reason = this.reason;
        if (this.newTag != "") {
          console.log("newTag = " + this.newTag);
          insertFin.tagName = this.newTag;
        } else {
          console.log("tag = " + this.tag);
          insertFin.tagName = this.tag;
        }
        insertFin.amount = parseFloat(this.money);
        console.log(insertFin);
        this.$http
          .post("/finance/billing/insert", insertFin)
          .then((response) => {
            this.insertFin = {};
            this.newTag = "";
            this.tag = "";
            this.getFinance();
          });
      }
    },
  },
};
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