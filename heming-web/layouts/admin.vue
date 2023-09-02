<script setup lang="ts">
import Menu from '~/components/admin/Menu'

const router = useRouter()
const drawer = ref(null)
</script>

<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer">
      <Menu />
    </v-navigation-drawer>

    <v-app-bar>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-app-bar-title cursor-pointer @click="router.push('/')">嗯学英语</v-app-bar-title>

      <DarkToggle mr-4 />
    </v-app-bar>

    <v-main>
      <div p-2>
        <slot />
      </div>
    </v-main>
  </v-app>
</template>

<style scoped>

</style>