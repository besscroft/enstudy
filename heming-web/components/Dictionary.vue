<script setup lang="ts">
const nuxtApp = useNuxtApp()

const userDict = ref({})

const getUserDict = async () => {
  const json = await nuxtApp.$api.get('/@api/dict/getUserDict').json();
  if (json.code === 200) {
    userDict.value = json.data
  } else {
    console.log(json.message)
  }
}

onBeforeMount(async () => {
  await getUserDict()
})
</script>

<template>
  <v-card
    mx-auto w-full
    prepend-icon="mdi-alphabetical"
  >
    <template v-slot:title>
      单词进度
    </template>

    <template v-slot:subtitle>
      {{ userDict.studied || 0 }}/ {{ userDict.bookSize || 0 }} 词
    </template>

    <v-card-text>
      当前词库: {{ userDict.bookName || '未选择' }}
    </v-card-text>
  </v-card>
</template>

<style scoped>

</style>