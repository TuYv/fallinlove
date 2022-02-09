<template>
  <div class="base-progress">
    <div class="progress-bar">
      <div
        class="progress-inner"
        :style="{
          width: percentage,
          background: `linear-gradient(to right, ${firstColor}, ${secondColor})`,
        }"
      />
    </div>
    <div v-if="showNumber" class="progress-num">{{ percentage }}</div>
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
    value: {
      type: Number,
      default: 0
    },
    // 是否显示百分比
    showNumber: {
      type: Boolean,
      default: false
    },
    // 第一种颜色
    firstColor: {
      type: String,
      default: 'red'
    },
    // 第二种颜色
    secondColor: {
      type: String,
      default: 'yellow'
    }
  },
  computed: {
    percentage () {
      return (this.value / this.count) * 100 + '%'
    }
  }
}
</script>

<style scoped>
.base-progress {
  margin-left: 100px;
  display: flex;
  align-items: center;
}
.progress-bar {
  width: 300px;
  height: 20px;
  border-radius: 10px;
  background-color: #ddd;
}
.progress-inner {
  height: 100%;
  border-radius: 10px;
}
.progress-num {
  margin-left: 10px;
}
</style>
