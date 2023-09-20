<script setup lang="ts">
import { useTheme } from 'vuetify'

const theme = useTheme()
const color = useColorMode()

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
    <v-btn class="!outline-none" @click="toggleDark" icon="mdi-theme-light-dark"  title="切换主题" />
  </v-no-ssr>
</template>
