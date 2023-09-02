<script setup lang="ts">
const router = useRouter()
</script>

<template>
  <v-toolbar>
    <v-toolbar
      color="primary"
      :dark="true"
    >
      <v-toolbar-title font-ark>嗯学英语</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon="mdi-view-dashboard" @click="router.push('/admin')" />
      <DarkToggle />
    </v-toolbar>
  </v-toolbar>
</template>

<style scoped>

</style>