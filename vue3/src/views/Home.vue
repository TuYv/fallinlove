<template>
  <div>
    <Header />
    <el-container>
      <el-aside>
        <SideBar />
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import Header from './layout/Header.vue'
import SideBar from './layout/SideBar.vue'
</script>

<style lang="scss" scoped></style>
