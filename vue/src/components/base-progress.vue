<template>
  <div class="base-progress">
            目标 {{innerList[0].purposes}} : 
    <div class="progress-bar">
      
      <div
        class="progress-inner"
        v-for="(item, index) in innerList"
        :key="index"
        :style="{
          width: item.percentage + '%',
          background: item.bgColor,
        }"
      >
        <span v-if="showNumber">{{ item.percentage| toFixed(0) }}%</span>
        <span v-if="showName">{{item.nickName}}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'base-progress',
  props: {
    // 总数
    count: {
      type: Number,
      default: 0
    },
    // 值
    list: {
      type: Array,
      default: () => []
    },
    // 是否显示百分比
    showNumber: {
      type: Boolean,
      default: false
    },
    // 是否显示姓名
    showName: {
      type: Boolean,
      default: false
    },
    // 颜色数组
    colorList: {
      type: Array,
      default: () => ['#a8d8ea', '#fcbad3']
    }
  },
  computed: {
    innerList () {
      if (this.list.length) {
        const tempList = this.list.map((item, index) => {
          return {
            ...item,
            bgColor: this.colorList[index],
            percentage: item.saved / this.count * 100
          }
        })
        return tempList
      }
      return []
    }
  }
}
</script>

<style scoped>
.base-progress {
  margin-left: 200px;
  display: flex;
  align-items: center;
}
.progress-bar {
  width: 500px;
  height: 20px;
  border-radius: 10px;
  background-color: #ddd;
  display: flex;
  align-items: center;
  flex-shrink: 0;
}
.progress-inner {
  height: 100%;
  line-height: 20px;
  text-align: center;
  font-size: 12px;
  color: #fff
}
.progress-inner:first-child {
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}
.progress-inner:last-child {
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}
.progress-num {
  margin-left: 10px;
}
</style>
