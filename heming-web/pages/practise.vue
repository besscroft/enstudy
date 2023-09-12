<script setup lang="ts">
const router = useRouter()

definePageMeta({
  layout: 'learn',
  pageTransition: false,
})
</script>

<template>
  <div>
    <v-app-bar color="primary" w-full>
      <template v-slot:prepend>
        <v-app-bar-nav-icon icon="mdi-arrow-left" @click="router.push('/learn')"></v-app-bar-nav-icon>
      </template>

      <v-app-bar-title>错题训练</v-app-bar-title>
    </v-app-bar>
    <div pt18 px2>
      开发中...
    </div>
  </div>
</template>

<style scoped>

</style>