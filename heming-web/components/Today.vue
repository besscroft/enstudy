<script setup lang="ts">
const nuxtApp = useNuxtApp()

const today = ref({})

const getToday = async () => {
  const json = await nuxtApp.$api.get('/@api/dict/getToday').json();
  if (json.code === 200) {
    today.value = json.data
  } else {
    console.log(json.message)
  }
}

onBeforeMount(async () => {
  await getToday()
})
</script>

<template>
  <v-card
    mx-auto w-full
    prepend-icon="mdi-chair-school"
  >
    <template v-slot:title>
      今日概览
    </template>

    <template v-slot:subtitle>
      学习单词 {{ today.studied || 0 }} 词
    </template>

    <v-card-text>
      The successful warrior is the average man, with laser-like focus.
    </v-card-text>
  </v-card>
</template>

<style scoped>

</style>