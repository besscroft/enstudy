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
        <dl class="grid grid-cols-1 gap-4 sm:grid-cols-3">
          <div class="flex flex-col rounded-lg border border-gray-100 px-4 py-8 text-center">
            <dt class="order-last text-lg font-ark text-gray-500">
              单词总数
            </dt>

            <dd class="text-4xl font-ark text-blue-600 md:text-5xl">{{ consoleInfo.wordCount || 0 }}</dd>
          </div>

          <div class="flex flex-col rounded-lg border border-gray-100 px-4 py-8 text-center">
            <dt class="order-last text-lg font-ark text-gray-500">
              词典总数
            </dt>

            <dd class="text-4xl font-ark text-blue-600 md:text-5xl">{{ consoleInfo.dictCount || 0 }}</dd>
          </div>

          <div class="flex flex-col rounded-lg border border-gray-100 px-4 py-8 text-center">
            <dt class="order-last text-lg font-ark text-gray-500">
              用户总数
            </dt>

            <dd class="text-4xl font-ark text-blue-600 md:text-5xl">{{ consoleInfo.userCount || 0 }}</dd>
          </div>
        </dl>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
