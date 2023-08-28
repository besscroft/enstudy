<script setup lang="ts">
import Navbar from '~/components/admin/Navbar'
import Menu from '~/components/admin/Menu'
</script>

<template>
  <ClientOnly>
    <Navbar />
    <Menu />
  </ClientOnly>
  <div p-4>
    <slot />
  </div>
</template>

<style scoped>

</style>