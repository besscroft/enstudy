<script setup lang="ts">
import { appName } from '~/constants'

useHead({
  title: appName,
})
</script>

<template>
  <VitePwaManifest />
  <NuxtLayout>
    <NuxtLoadingIndicator />
    <NuxtPage />
  </NuxtLayout>

  <UNotifications />
</template>

<style>
html, body , #__nuxt{
  height: 100vh;
  margin: 0;
  padding: 0;
  overflow-y: inherit;
  font-family: LXGWNeoXiHei;
}

html.dark {
  background: #222;
  color: white;
}

@font-face {
  font-family: 'LXGWNeoXiHei';
  src: url('public/fonts/LXGWNeoXiHei.ttf');
  font-weight: normal;
  font-style: normal;
  font-display: swap;
}

@font-face {
  font-family: 'ark';
  src: url('public/fonts/ark-pixel-12px-proportional-zh_cn.ttf');
  font-weight: normal;
  font-style: normal;
  font-display: swap;
}

.page-enter-active,
.page-leave-active {
  transition: all 0.4s;
}
.page-enter-from,
.page-leave-to {
  opacity: 0;
  filter: blur(1rem);
}
</style>
