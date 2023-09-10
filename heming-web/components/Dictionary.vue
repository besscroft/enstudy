<script setup lang="ts">
const nuxtApp = useNuxtApp()

const userDict = ref({})
const items = ref([])
const emit = defineEmits(['handleChoiceLoading'])
const propsData = defineProps({
  choiceLoading: {
    type: Boolean,
    required: true,
  },
})

const getUserDict = async () => {
  const json = await nuxtApp.$api.get('/@api/dict/getUserDict').json();
  if (json.code === 200) {
    userDict.value = json.data
  } else {
    console.log(json.message)
  }
}

const handleMenu = async () => {
  if (items.value.length === 0) {
    const json = await nuxtApp.$api.get('/@api/dict/getDict').json();
    if (json.code === 200) {
      items.value = json.data
    } else {
      console.log(json.message)
    }
  }
}

const clickItem = async (item: string) => {
  emit('handleChoiceLoading', true)
  try {
    const json = await nuxtApp.$api.post('/@api/dict/choice', {
      json: {
        bookId: item,
      }
    }).json();
    if (json.code === 200) {
      await getUserDict()
    } else {
      console.log(json.message)
    }
  } catch (e) {
    emit('handleChoiceLoading', false)
  }
  emit('handleChoiceLoading', false)
}

onBeforeMount(async () => {
  await getUserDict()
})

onMounted(async () => {
  setTimeout(() => {
    getUserDict()
  }, 1000 * 60)
})

onUnmounted(() => {
  items.value = []
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

    <v-card-text flex items-center>
      <p w-full>词典: {{ userDict.bookName || '未选择' }}</p>
      <v-menu>
        <template v-slot:activator="{ props }">
          <v-btn
            color="primary"
            v-bind="props"
            @click="handleMenu"
            :loading="propsData.choiceLoading"
          >
            选择
          </v-btn>
        </template>
        <v-list>
          <v-list-item
            v-for="(item, index) in items"
            :key="index"
            :value="item.id"
            @click="() => clickItem(item.id)"
          >
            <v-list-item-title>{{ item.bookName }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-card-text>
  </v-card>
</template>

<style scoped>

</style>