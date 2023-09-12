<script setup lang="ts">
const router = useRouter()
const drawer = ref(null)
</script>

<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer">
      <Menu />
    </v-navigation-drawer>

    <v-app-bar dark>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-app-bar-title>嗯学英语</v-app-bar-title>

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