<template>
  <el-menu
    router
    class="side-bar-menu"
    :collapse="isCollapse"
    :default-active="route.path"
  >
    <div class="side-bar-icon" @click="handleCollapse">
      <el-icon :size="22" color="#999"><fold /></el-icon>
    </div>
    <MeanItem :menuList="meanList" />
  </el-menu>
</template>

<script setup>
import { ref } from 'vue-demi'
import { useRoute, useRouter } from 'vue-router'
import MeanItem from './components/MeanItem.vue'

const router = useRouter()
const route = useRoute()

const meanList = router.options.routes

const isCollapse = ref(false)
function handleCollapse() {
  isCollapse.value = !isCollapse.value
}
</script>

<style lang="scss" scoped>
.side-bar-icon {
  position: absolute;
  top: 10px;
  cursor: pointer;
  right: -20px;
}

.side-bar-menu {
  height: calc(100vh - 55px);
  position: relative;
  &:not(.el-menu--collapse) {
    width: 200px;
  }
}
</style>
