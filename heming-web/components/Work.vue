<script setup lang="ts">
const nuxtApp = useNuxtApp()
const router = useRouter()

const wrongBook = ref({})

const getWrongBook = async () => {
  const json = await nuxtApp.$api.get('/@api/dictApi/getWrongBook').json();
  if (json.code === 200) {
    wrongBook.value = json.data
  } else {
    console.log(json.message)
  }
}

onBeforeMount(async () => {
  await getWrongBook()
})

onMounted(async () => {
  setTimeout(() => {
    getWrongBook()
  }, 1000 * 60)
})
</script>

<template>
  <v-card
    variant="flat"
    mx-auto w-full
    prepend-icon="mdi-file-document-alert-outline"
  >
    <template v-slot:title>
      <span font-ark>错题本</span>
    </template>

    <v-card-text flex items-center>
      <p w-full>{{ wrongBook.count || 0 }} 题</p>
      <v-btn @click="router.push('/practise')">
        复习
      </v-btn>
    </v-card-text>
  </v-card>
</template>
