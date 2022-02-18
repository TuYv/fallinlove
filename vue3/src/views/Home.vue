<template>
  <div>
    <Header />
    <el-container>
      <el-aside>
        <SideBar />
      </el-aside>
      <el-main>
        <div class="page-main">
          <router-view></router-view>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import Header from './layout/Header.vue'
import SideBar from './layout/SideBar.vue'
</script>

<style lang="scss" scoped>
:deep(.el-aside) {
  --el-aside-width: auto;
  padding-right: 22px;
}
:deep(.el-main) {
  --el-main-padding: 15px 10px;
}

.page-main {
  background-color: #fff;
  border-radius: 8px;
  padding: 10px;
  box-sizing: border-box;
  max-height: calc(100vh - 85px);
  overflow-y: auto;
}
</style>
