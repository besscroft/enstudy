<script setup lang="ts">
import { appName } from '~/constants'
import { darkTheme } from 'naive-ui'

const color = useColorMode()

useHead({
  title: appName,
})
</script>

<template>
  <VitePwaManifest />
  <n-config-provider :theme="color.value === 'dark' ? darkTheme : undefined">
    <n-message-provider>
      <NuxtLayout>
        <NuxtLoadingIndicator />
        <NuxtPage />
      </NuxtLayout>
    </n-message-provider>
  </n-config-provider>
</template>

<style>
html, body , #__nuxt{
  height: 100vh;
  margin: 0;
  padding: 0;
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
</style>
