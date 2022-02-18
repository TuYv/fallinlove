<template>
  <template v-for="menu in menuList" :key="menu.path">
    <el-sub-menu
      v-if="menu.menu && menu.children && menu.children.length"
      :index="menu.path"
    >
      <template #title>
        <el-icon>
          <component :is="menu.meta?.icon" />
        </el-icon>
        <span>{{ menu.meta?.title }}</span>
      </template>
      <MenuItem :menuList="menu.children"></MenuItem>
    </el-sub-menu>

    <el-menu-item v-if="menu.menu && !menu.children" :index="menu.path">
      <el-icon>
        <component :is="menu.meta?.icon" />
      </el-icon>
      <template #title>{{ menu.meta?.title }}</template>
    </el-menu-item>
  </template>
</template>

<script>
export default {
  name: 'MenuItem',
}
</script>

<script setup>
defineProps({
  menuList: Array,
})
</script>

<style lang="scss" scoped></style>
