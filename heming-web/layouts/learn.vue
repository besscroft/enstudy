<script setup lang="ts">
import { breakpointsTailwind, useBreakpoints } from '@vueuse/core'

const router = useRouter()
const breakpoints = useBreakpoints(breakpointsTailwind)
const smAndLarger = breakpoints.greaterOrEqual('sm')
const choiceLoading = ref<boolean>(false)
const tab = ref(null)
const banner = ref<boolean>(true)

const handleChoiceLoading = (val: boolean) => {
  choiceLoading.value = val
}

onBeforeMount(() => {
  if (Math.random() > 0.3) {
    banner.value = false
  }
})
</script>

<template>
  <v-app>
    <div v-if="router.currentRoute.value.path === '/learn'">
      <Header />
      <v-banner v-if="banner" lines="one" icon="mdi-github">
        <template v-slot:text>
          您的 Star 对我们很重要！
        </template>

        <template v-slot:actions>
          <a href="https://github.com/besscroft/enstudy" target="_blank">
            <v-btn>去 Star</v-btn>
          </a>

          <v-btn @click="banner = false">
            关闭
          </v-btn>
        </template>
      </v-banner>
      <div v-if="smAndLarger" p2 grid sm:px16 md:px20 lg:24 grid-cols-1 gap-4 lg:grid-cols-3 lg:gap-8>
        <Today />
        <Dictionary :choiceLoading="choiceLoading" @handleChoiceLoading="handleChoiceLoading" />
        <Work />
      </div>
      <div sm:hidden p2>
        <v-card variant="flat">
          <v-tabs
            v-model="tab"
            color="deep-purple-accent-4"
            align-tabs="center"
          >
            <v-tab :value="1">今日概览</v-tab>
            <v-tab :value="2">单词进度</v-tab>
            <v-tab :value="3">错题本</v-tab>
          </v-tabs>
          <v-window v-model="tab">
            <v-window-item :value="1">
              <Today />
            </v-window-item>
            <v-window-item :value="2">
              <Dictionary :choiceLoading="choiceLoading" @handleChoiceLoading="handleChoiceLoading" />
            </v-window-item>
            <v-window-item :value="3">
              <Work />
            </v-window-item>
          </v-window>
        </v-card>
      </div>
    </div>
    <div w-full v-if="!choiceLoading">
      <slot />
    </div>
  </v-app>
</template>
