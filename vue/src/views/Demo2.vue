<template>
  <div>
    <el-row>
  <el-col :span="24"><div class="grid-content bg-purple-dark">111111</div></el-col>
</el-row>
<el-row>
  <el-col :span="12"><div class="grid-content bg-purple">22222</div></el-col>
  <el-col :span="12"><div class="grid-content bg-purple-light">3333</div></el-col>
</el-row>
<el-row>
  <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
  <el-col :span="8"><div class="grid-content bg-purple-light"></div></el-col>
  <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
</el-row>
<el-row>
  <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
  <el-col :span="6"><div class="grid-content bg-purple-light"></div></el-col>
  <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
  <el-col :span="6"><div class="grid-content bg-purple-light"></div></el-col>
</el-row>
<el-row>
  <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
  <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
  <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
  <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
  <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
  <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
  <div class="column col-8"><div class="grid-content bg-purple-light">11111</div></div>
  <div class="column col-2"><div class="grid-content bg-purple-light">11111</div></div>

  <div class="colunm">
    <div class="col-auto">12</div>
    <div class="col-auto">1</div>
  </div>
</el-row>

    <h1>绑定class和style</h1>
    <div :class="classA ">绑定class</div>
    <div :class="{current: isCurrent, focus: isFocus}">通过对象绑定class</div>
    <div :style="styleObj">绑定style</div>
    <div :style="{color: 'red'}">绑定style</div>
  </div>
</template>
<script>
export default {
  name: "demo2",
  data () {
    return {
      classA: "current",
      isCurrent: false,
      isFocus: true,
      styleObj: {
        color: 'blue',
        font: '18'
      }
    }
  }
}
</script>

<style>
.el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>