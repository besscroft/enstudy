<script setup lang="ts">
const nuxtApp = useNuxtApp()

const wrongBook = ref({})

const getWrongBook = async () => {
  const json = await nuxtApp.$api.get('/@api/dict/getWrongBook').json();
  if (json.code === 200) {
    wrongBook.value = json.data
  } else {
    console.log(json.message)
  }
}

onBeforeMount(async () => {
  await getWrongBook()
})
</script>

<template>
  <v-card
    mx-auto w-full
    prepend-icon="mdi-file-document-alert-outline"
  >
    <template v-slot:title>
      错题本
    </template>

    <v-card-text>
      {{ wrongBook.count || 0 }} 题
    </v-card-text>
  </v-card>
</template>

<style scoped>

</style>