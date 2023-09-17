<script setup lang="ts">
import { Word } from '~/types/interfaces/word'

const router = useRouter()
const nuxtApp = useNuxtApp()
const loading = ref<boolean>(false)
const toast = useToast()

const updateWordRuleForm = reactive<Word.UpdateWordRequestData>({
  /** id */
  id: undefined,
  /** 单词数据 */
  wordJson: undefined,
  /** 单词序号 */
  wordRank: undefined,
  /** 单词 */
  headWord: undefined,
  /** 词典 id */
  bookId: undefined,
})

const submitHandler = async () => {
  loading.value = true
  try {
    updateWordRuleForm.wordJson = JSON.parse(updateWordRuleForm.wordJson)
    const json = await nuxtApp.$api.put('/@api/word/updateWord', {
      json: updateWordRuleForm,
    }).json();
    if (json.code === 200) {
      toast.add({ title: '更新成功!', timeout: 1000, ui: { width: 'w-full sm:w-96' }})
      router.push('/admin/word')
    } else {
      toast.add({ title: json.message, timeout: 1000, ui: { width: 'w-full sm:w-96' }})
    }
  } catch (e) {
    toast.add({ title: e.message, timeout: 1000, ui: { width: 'w-full sm:w-96' }})
    loading.value = false
  }
  loading.value = false
}

onBeforeMount(async () => {
  const id = Number(router.currentRoute.value.params.id)
  const json = await nuxtApp.$api.get(`/@api/word/get/${id}`).json();
  if (json.code === 200) {
    updateWordRuleForm.id = json.data.id
    updateWordRuleForm.wordJson = JSON.stringify(json.data.wordJson)
    updateWordRuleForm.wordRank = json.data.wordRank
    updateWordRuleForm.headWord = json.data.headWord
    updateWordRuleForm.bookId = json.data.bookId
  } else {
    console.log(json.message)
  }
})

definePageMeta({
  layout: 'admin',
})
</script>

<template>
  <div px2 w-full md:w-160 mxa>
    <VForm @submit.prevent="() => { submitHandler() }">
      <VRow>
        <VCol cols="12">
          <VRow no-gutters>
            <VCol cols="12">
              <label for="firstNameHorizontalIcons">单词序号</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextField
                id="firstNameHorizontalIcons"
                v-model="updateWordRuleForm.wordRank"
                prepend-inner-icon="mdi-counter"
                placeholder="单词序号"
                persistent-placeholder
              />
            </VCol>
          </VRow>
        </VCol>

        <VCol cols="12">
          <VRow no-gutters>
            <VCol cols="12">
              <label for="emailHorizontalIcons">单词</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextField
                id="emailHorizontalIcons"
                v-model="updateWordRuleForm.headWord"
                prepend-inner-icon="mdi-alphabetical"
                placeholder="单词"
                persistent-placeholder
              />
            </VCol>
          </VRow>
        </VCol>

        <VCol cols="12">
          <VRow no-gutters>
            <VCol cols="12">
              <label for="mobileHorizontalIcons">词典 id</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextField
                id="mobileHorizontalIcons"
                v-model="updateWordRuleForm.bookId"
                prepend-inner-icon="mdi-identifier"
                placeholder="词典 id"
                persistent-placeholder
              />
            </VCol>
          </VRow>
        </VCol>

        <VCol cols="12">
          <VRow no-gutters>
            <VCol cols="12">
              <label for="mobileHorizontalIcons">单词数据</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextarea
                id="mobileHorizontalIcons"
                v-model="updateWordRuleForm.wordJson"
                prepend-inner-icon="mdi-alphabetical"
                placeholder="单词数据"
                persistent-placeholder
              />
            </VCol>
          </VRow>
        </VCol>

        <!-- 👉 submit and reset button -->
        <VCol
          offset-md="3"
          cols="12"
          md="9"
          class="d-flex gap-4"
        >
          <VBtn type="submit" :loading="loading">
            提交
          </VBtn>
          <VBtn
            color="secondary"
            type="reset"
            variant="tonal"
          >
            重置
          </VBtn>
        </VCol>
      </VRow>
    </VForm>
  </div>
</template>
