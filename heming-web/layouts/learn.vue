<script setup lang="ts">
</script>

<template>
  <Header />
  <div flex flex-wrap-reverse sm:flex-nowrap justify-center>
    <div w-full h-full mx-1 class="sm:w-3/5 lg:w-4/5">
      <slot />
    </div>
    <div flex flex-col m-1 sm:mt-0 w-full sm:min-w-80 sm:w-80 lg:w-100 xl:w-120 space-y-1>
      <Today />
      <Dictionary />
      <Work />
      <Other />
    </div>
  </div>
</template>

<style scoped>

</style>