<script setup lang="ts">
import { useUserStore } from '~/stores/user'

const router = useRouter()
const user = useUserStore()
</script>

<template>
  <v-toolbar>
    <v-toolbar
      color="green-accent-1"
      :dark="true"
    >
      <v-toolbar-title font-ark>嗯学英语</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        v-if="user.roleCode === 'platform-super-admin' || user.roleCode === 'platform-admin' || user.roleCode === 'platform-self-provisioner' || user.roleCode === 'platform-view'"
        icon="mdi-view-dashboard"
        @click="router.push('/admin')"
      />
      <VDarkToggle />
    </v-toolbar>
  </v-toolbar>
</template>

<style scoped>

</style>