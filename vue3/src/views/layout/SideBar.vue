<template>
  <div>
    navbar
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()
console.log(router.options.routes);

</script>

<style lang="scss" scoped>

</style>
