<template>
  <div class="about">
    <h1>This is an about page</h1>
     <div style="width:500px;height:500px" ref="charts"></div>
  </div>
</template>
<script>
  const echarts = require('echarts');
  export default{
    data () {
      return {};
    },
    methods: {
      initCharts () {
        let myChart = echarts.init(this.$refs.charts);
        // 绘制图表
        myChart.setOption({
            tooltip: {
        trigger: 'item'
    },
    legend: {
        top: '5%',
        left: 'center'
    },
    series: [
        {
            name: '访问来源',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
            },
            label: {
                show: false,
                position: 'center'
            },
            emphasis: {
                label: {
                    show: true,
                    fontSize: '40',
                    fontWeight: 'bold'
                }
            },
            labelLine: {
                show: false
            },
            data: [
                {value: 1048, name: '搜索引擎'},
                {value: 735, name: '直接访问'},
                {value: 580, name: '邮件营销'},
                {value: 484, name: '联盟广告'},
                {value: 300, name: '视频广告'}
            ]
        }
    ]
        });
      }
    },
    mounted () {
      this.initCharts();
    }
  }
</script>
