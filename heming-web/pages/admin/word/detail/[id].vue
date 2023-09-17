<script setup lang="ts">
const router = useRouter()
const nuxtApp = useNuxtApp()
const info = ref({})
const data = reactive({
  wordJsonId: '',
  bookId: '',
  wordRank: 0,
  headWord: '',
  state: 0,
})
const toast = useToast()

onMounted(async () => {
  const id = Number(router.currentRoute.value.params.id)
  try {
    const json = await nuxtApp.$api.get(`/@api/word/get/${id}`).json();
    if (json.code === 200) {
      info.value = json.data
    } else {
      console.log(json.message)
      toast.add({ title: json.message, timeout: 1000, ui: { width: 'w-full sm:w-96' }})
    }
  } catch (e) {
    toast.add({ title: e.message, timeout: 1000, ui: { width: 'w-full sm:w-96' }})
  }
})

const handleAudio = (type: number) => {
  if (type === 1) {
    const audio = document.getElementById('ukAudio')
    audio.play()
  } else {
    const audio = document.getElementById('usAudio')
    audio.play()
  }
}

definePageMeta({
  layout: 'admin',
})
</script>

<template>
  <div>
    <VCard title="单词详情">
      <template v-slot:prepend>
        <v-btn density="compact" icon="mdi-arrow-left" title="返回" @click="router.push('/admin/word')"></v-btn>
      </template>
      <VCardText class="d-flex">
        <div space-x-2>
          {{ info.headWord }}
          <UBadge color="black" cursor-pointer @click="handleAudio(2)"><span dark:text-black>美[{{ info?.wordJson?.content?.word?.content?.usphone || '' }}]</span></UBadge>
          <UBadge cursor-pointer @click="handleAudio(1)"><span dark:text-black>英[{{ info?.wordJson?.content?.word?.content?.ukphone  || '' }}]</span></UBadge>
        </div>
      </VCardText>
      <VDivider />

      <VCardText>
        <v-table>
          <thead>
          <tr>
            <th class="text-left">
              属性
            </th>
            <th class="text-left">
              值
            </th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>单词数据 id</td>
            <td>{{ info.wordJsonId }}</td>
          </tr>
          <tr>
            <td>单词序号</td>
            <td>{{ info.wordRank }}</td>
          </tr>
          <tr>
            <td>单词</td>
            <td>{{ info.headWord }}</td>
          </tr>
          <tr>
            <td>词典 id</td>
            <td>{{ info.bookId }}</td>
          </tr>
          <tr>
            <td>单词数据</td>
            <td>{{ JSON.stringify(info.wordJson) }}</td>
          </tr>
          </tbody>
        </v-table>
      </VCardText>
    </VCard>
    <audio id="ukAudio" v-show="false" controls :src="`https://dict.youdao.com/dictvoice?audio=${info.headWord}&type=1`" />
    <audio id="usAudio" v-show="false" controls :src="`https://dict.youdao.com/dictvoice?audio=${info.headWord}&type=2`" />
  </div>
</template>
