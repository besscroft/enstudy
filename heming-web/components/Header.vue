<script setup lang="ts">
const router = useRouter()
</script>

<template>
  <div flex items-center justify-between h-16 pr-4>
    <Logo />
    <div flex space-x-4>
      <p cursor-pointer font-ark>头顶菜单栏</p>
      <p cursor-pointer font-ark @click="router.push('/admin')">进入后台</p>
    </div>
  </div>
</template>

<style scoped>

</style>