<script setup lang="ts">
const router = useRouter()
const drawer = ref(null)
</script>

<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer">
      <Menu />
    </v-navigation-drawer>

    <v-app-bar>
      <v-app-bar-nav-icon @click="drawer = !drawer" title="菜单收缩"></v-app-bar-nav-icon>

      <v-app-bar-title>
        <a font-ark cursor-pointer href="https://github.com/besscroft/enstudy" target="_blank">嗯学英语</a>
      </v-app-bar-title>

      <DarkToggle mr-4 />
    </v-app-bar>

    <v-main>
      <div flex flex-col p-2>
        <BreadCrumb />
        <div mt-2>
          <slot />
        </div>
      </div>
    </v-main>
  </v-app>
</template>

<style scoped>

</style>