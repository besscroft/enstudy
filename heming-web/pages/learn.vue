<script setup lang="ts">
const nuxtApp = useNuxtApp()

const wordInfo = ref({})
const phraseShow = ref(false)
const examShow = ref(false)
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
    }
    if (json.data?.content?.word?.content?.exam) {
      exam.value = json.data.content.word.content.exam
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

definePageMeta({
  layout: 'learn',
})
</script>

<template>
  <div>
    <v-no-ssr>
      <div flex flex-col justify-center>
        <span v-if="loading" mx-auto class="loading loading-dots loading-md"></span>
        <v-card
          v-else
          mx-auto w-full md:w-160
        >
          <template v-slot:title>
            <div space-x-2>
              {{ wordInfo.headWord }}
              <span class="badge" cursor-pointer @click="handleAudio(2)">
            美[{{ wordInfo.content.word.content.usphone }}]
          </span>
              <span class="badge" cursor-pointer @click="handleAudio(1)">
            英[{{ wordInfo.content.word.content.ukphone }}]
          </span>
            </div>
          </template>

          <v-card-text>
            <v-list lines="one">
              <v-list-item
                v-for="item in wordInfo.content.word.content.trans"
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

            <v-btn
              v-show="phrases"
              @click="phraseShow = !phraseShow"
            >
              短语
            </v-btn>
            <v-btn
              v-show="exam"
              @click="examShow = !examShow"
            >
              练习
            </v-btn>
          </v-card-actions>

          <v-expand-transition v-show="phrases">
            <div v-show="phraseShow">
              <v-divider></v-divider>

              <v-card-text>
                <v-list lines="one">
                  <v-list-item
                    v-for="item in phrases.slice(0, 3)"
                    :key="item"
                    :title="item.pContent + ' ' + item.pCn"
                  ></v-list-item>
                </v-list>
              </v-card-text>
            </div>
          </v-expand-transition>
          <v-expand-transition v-show="exam">
            <div v-show="examShow" px-4 py-2 space-y-2>
              <div v-for="item in exam" :key="item">
                <div class="chat chat-start">
                  <div class="chat-bubble">{{ item.question }}</div>
                </div>
              </div>
            </div>
          </v-expand-transition>
        </v-card>
      </div>
    </v-no-ssr>
  </div>
  <audio id="ukAudio" v-show="false" controls :src="`https://dict.youdao.com/dictvoice?audio=${wordInfo.headWord}&type=1`" />
  <audio id="usAudio" v-show="false" controls :src="`https://dict.youdao.com/dictvoice?audio=${wordInfo.headWord}&type=2`" />
</template>

<style scoped>

</style>