<script setup lang="ts">
import { useTheme } from 'vuetify'

const theme = useTheme()
const color = useColorMode()
const router = useRouter()

useHead({
  meta: [{
    id: 'theme-color',
    name: 'theme-color',
    content: () => color.value === 'dark' ? '#222222' : '#ffffff',
  }],
})

function toggleDark() {
  color.preference = color.value === 'dark' ? 'light' : 'dark'
  theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark'
}
</script>

<template>
  <v-no-ssr>
    <button v-if="router.currentRoute.value.path === '/'" class="!outline-none" @click="toggleDark">
      <div class="i-carbon-sun dark:i-carbon-moon" />
    </button>
    <v-btn v-else class="!outline-none" @click="toggleDark" icon="mdi-theme-light-dark">
    </v-btn>
  </v-no-ssr>
</template>
