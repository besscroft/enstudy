<script setup lang="ts">
const nuxtApp = useNuxtApp()

const consoleInfo = ref({})

const getConsoleInfo = async () => {
  const json = await nuxtApp.$api.get('/@api/console/getInfo').json();
  if (json.code === 200) {
    consoleInfo.value = json.data
  } else {
    console.log(json.message)
  }
}

onBeforeMount(async () => {
  await getConsoleInfo()
})

definePageMeta({
  layout: 'admin',
})
</script>

<template>
  <div m-4>
    <div class="mx-auto max-w-screen-xl px-4 py-12 sm:px-6 md:py-16 lg:px-8">
      <div class="mx-auto max-w-3xl text-center">
        <h2 class="text-3xl font-ark text-gray-900 sm:text-4xl dark:text-white">
          嗯学英语管理平台
        </h2>

        <p class="mt-4 text-gray-500 sm:text-xl">
          学英语，就嗯学！
        </p>
      </div>

      <div class="mt-8 sm:mt-12">
        <dl class="grid grid-cols-1 gap-4 sm:grid-cols-4 sm:gap-8">
          <v-card prepend-icon="mdi-alphabetical" h-36>
            <template v-slot:title>
              <span font-ark>单词总数</span>
            </template>

            <v-card-text>
              <span font-ark text-3xl>{{ consoleInfo.wordCount || 0 }}</span>
            </v-card-text>
          </v-card>

          <v-card prepend-icon="mdi-book-open-page-variant" h-36>
            <template v-slot:title>
              <span font-ark>词典总数</span>
            </template>

            <v-card-text>
              <span font-ark text-3xl>{{ consoleInfo.dictCount || 0 }}</span>
            </v-card-text>
          </v-card>

          <v-card prepend-icon="mdi-account-group" h-36>
            <template v-slot:title>
              <span font-ark>用户总数</span>
            </template>

            <v-card-text>
              <span font-ark text-3xl>{{ consoleInfo.userCount || 0 }}</span>
            </v-card-text>
          </v-card>

          <v-card prepend-icon="mdi-square-rounded-badge-outline" h-36>
            <template v-slot:title>
              <span font-ark>错题总数</span>
            </template>

            <v-card-text>
              <span font-ark text-3xl>{{ consoleInfo.wrongCount || 0 }}</span>
            </v-card-text>
          </v-card>
        </dl>
      </div>
    </div>
  </div>
</template>
