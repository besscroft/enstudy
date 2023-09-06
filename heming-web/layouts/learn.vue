<script setup lang="ts">
</script>

<template>
  <Header />
  <div class="p-2 grid grid-cols-1 gap-4 lg:grid-cols-3 lg:gap-8">
    <Today />
    <Dictionary />
    <Work />
  </div>
  <div p-2 w-full>
    <slot />
  </div>
</template>

<style scoped>

</style>