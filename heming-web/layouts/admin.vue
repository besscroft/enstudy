<script setup lang="ts">
import Navbar from '~/components/admin/Navbar'
import Menu from '~/components/admin/Menu'
</script>

<template>
  <ClientOnly>
    <Navbar />
    <Menu />
  </ClientOnly>
  <slot />
</template>

<style scoped>

</style>