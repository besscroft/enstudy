<script setup lang="ts">
import { breakpointsTailwind, useBreakpoints } from '@vueuse/core'

const router = useRouter()
const breakpoints = useBreakpoints(breakpointsTailwind)
const smAndLarger = breakpoints.greaterOrEqual('sm')
const choiceLoading = ref<boolean>(false)
const tab = ref(null)

const handleChoiceLoading = (val: boolean) => {
  choiceLoading.value = val
}
</script>

<template>
  <v-app>
    <div v-if="router.currentRoute.value.path === '/learn'">
      <Header />
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
