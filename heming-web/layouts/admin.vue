<script setup lang="ts">
import Navbar from '~/components/admin/Navbar'
import Menu from '~/components/admin/Menu'
</script>

<template>
  <ClientOnly>
    <Navbar id="heming-content" />
    <Menu />
  </ClientOnly>
  <div p-4 id="heming-content">
    <slot />
  </div>
</template>

<style scoped>

</style>