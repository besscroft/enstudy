<script setup lang="ts">
import { VSkeletonLoader } from 'vuetify/labs/VSkeletonLoader'

const nuxtApp = useNuxtApp()
const wordInfo = ref({})
const loading = ref(false)
const phrases = ref([])
const exam = ref([])

const data = reactive({
  form: {},
  actionsParam: {
    wordJsonId: '',
    bookId: '',
    wordRank: 0,
    headWord: '',
    state: 0,
  },
})

const getWord = async () => {
  loading.value = true
  const json = await nuxtApp.$api.get('/@api/book/getWord').json();
  if (json.code === 200) {
    wordInfo.value = json.data
    if (json.data?.content?.word?.content?.phrase?.phrases) {
      phrases.value = json.data.content.word.content.phrase.phrases
    } else {
      phrases.value = []
    }
    if (json.data?.content?.word?.content?.exam) {
      exam.value = json.data.content.word.content.exam
    } else {
      exam.value = []
    }
  } else {
    console.log(json.message)
  }
  loading.value = false
}

const handleActions = async (state: number) => {
  loading.value = true
  data.actionsParam.state = state
  data.actionsParam.wordJsonId = wordInfo.value.id
  data.actionsParam.bookId = wordInfo.value.bookId
  data.actionsParam.wordRank = wordInfo.value.wordRank
  data.actionsParam.headWord = wordInfo.value.headWord
  const json = await nuxtApp.$api.post('/@api/book/actions', {
    json: data.actionsParam
  }).json();
  if (json.code === 200) {
    await getWord()
  } else {
    console.log(json.message)
  }
  loading.value = false
}

const handleAudio = (type: number) => {
  if (type === 1) {
    const audio = document.getElementById('ukAudio')
    audio.play()
  } else {
    const audio = document.getElementById('usAudio')
    audio.play()
  }
}

onBeforeMount(async () => {
  await getWord()
})

onMounted(() => {
  loading.value = false
})

definePageMeta({
  layout: 'learn',
  pageTransition: false,
})
</script>

<template>
  <div p-2>
    <v-no-ssr>
      <v-skeleton-loader
        mx-auto w-full sm:w-160
        :loading="loading"
        type="table-heading, list-item-two-line, button, button"
      >
        <v-card
        variant="flat"
        :loading="loading"
        >
          <template v-slot:title>
            <div space-x-2>
              {{ wordInfo.headWord }}
              <UBadge color="black" cursor-pointer @click="handleAudio(2)"><span dark:text-black>美[{{ wordInfo.content?.word?.content?.usphone || '' }}]</span></UBadge>
              <UBadge cursor-pointer @click="handleAudio(1)"><span dark:text-black>英[{{ wordInfo.content?.word?.content?.ukphone  || '' }}]</span></UBadge>
            </div>
          </template>

          <v-card-text>
            <v-list lines="one">
              <v-list-item
                v-for="item in wordInfo.content?.word?.content?.trans"
                :key="item"
                :title="item.pos + '. ' + item.tranCn"
                :subtitle="item.tranOther"
              ></v-list-item>
            </v-list>
          </v-card-text>

          <v-card-actions>
            <v-btn
              color="green-accent-3"
              variant="text"
              @click="handleActions(1)"
            >
              学会了
            </v-btn>
            <v-btn
              color="deep-orange-accent-3"
              variant="text"
              @click="handleActions(0)"
            >
              不认识
            </v-btn>

            <v-spacer></v-spacer>

            <v-btn>
              反馈(开发中)
            </v-btn>
          </v-card-actions>
          <div>
            <v-list lines="one" v-show="Array.isArray(phrases) && phrases.length > 0">
              <v-list-item
                v-for="item in phrases.slice(0, 3)"
                :key="item"
                :title="item.pContent + ' ' + item.pCn"
              ></v-list-item>
            </v-list>
            <v-spacer v-show="Array.isArray(exam) && exam.length > 0"></v-spacer>
            <div v-show="Array.isArray(exam) && exam.length > 0" px-4 py-2 space-y-2>
              <div v-for="item in exam" :key="item">
                <UAlert
                  :description="item.question"
                  :avatar="{ src: 'https://besscroft.com/uploads/avatar.jpeg' }"
                />
              </div>
            </div>
          </div>
        </v-card>
      </v-skeleton-loader>
    </v-no-ssr>
  </div>
  <audio id="ukAudio" v-show="false" controls :src="`https://dict.youdao.com/dictvoice?audio=${wordInfo.headWord}&type=1`" />
  <audio id="usAudio" v-show="false" controls :src="`https://dict.youdao.com/dictvoice?audio=${wordInfo.headWord}&type=2`" />
</template>
