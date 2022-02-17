<template>
  <div class="header">
    <div class="header-logo"></div>
    <el-dropdown>
      <div class="header-set">{{ user.nickName }}</div>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>查看详情</el-dropdown-item>
          <el-dropdown-item @click="handleLogout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { storageUtils } from '@/utils/common'
import { ElMessageBox } from 'element-plus'

const router = useRouter()

const user = storageUtils.get('user', true)

function handleLogout() {
  ElMessageBox.confirm('是否确认退出', '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    storageUtils.remove('user')
    router.push('/login')
  })
}
</script>

<style lang="scss" scoped>
.header {
  width: 100%;
  height: 55px;
  box-sizing: border-box;
  padding: 0 50px;
  background-color: #70adb5;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;

  div {
    height: 100%;
    line-height: 55px;
    cursor: pointer;
  }
}
</style>
