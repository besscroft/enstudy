<script setup lang="ts">
</script>

<template>
  <Header />
  <div class="p-2 grid grid-cols-1 gap-2 lg:grid-cols-4 lg:gap-4">
    <Today />
    <Dictionary />
    <Work />
    <Other />
  </div>
  <div p-2 w-full>
    <slot />
  </div>
</template>

<style scoped>

</style>