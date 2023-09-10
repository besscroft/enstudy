<script setup lang="ts">
const choiceLoading = ref<boolean>(false)

const handleChoiceLoading = (val: boolean) => {
  choiceLoading.value = val
}
</script>

<template>
  <Header />
  <div class="p-2 grid grid-cols-1 gap-4 lg:grid-cols-3 lg:gap-8">
    <Today />
    <Dictionary :choiceLoading="choiceLoading" @handleChoiceLoading="handleChoiceLoading" />
    <Work />
  </div>
  <div p-2 w-full v-if="!choiceLoading">
    <slot />
  </div>
</template>

<style scoped>

</style>