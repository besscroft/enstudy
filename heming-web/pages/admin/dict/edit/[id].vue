<script setup lang="ts">
import { Dict } from '~/types/interfaces/dict'

const router = useRouter()
const nuxtApp = useNuxtApp()
const loading = ref<boolean>(false)
const toast = useToast()

const updateDictRuleForm = reactive<Dict.UpdateDictRequestData>({
  /** id */
  id: undefined,
  /** 词典 id */
  bookId: undefined,
  /** 词典名称 */
  bookName: undefined,
  /** 词典词数 */
  bookSize: undefined,
})

const submitHandler = async () => {
  loading.value = true
  try {
    const json = await nuxtApp.$api.put('/@api/bookDict/updateDict', {
      json: updateDictRuleForm,
    }).json();
    if (json.code === 200) {
      toast.add({ title: '更新成功!', timeout: 1000, ui: { width: 'w-full sm:w-96' }})
      router.push('/admin/dict')
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
  const json = await nuxtApp.$api.get(`/@api/bookDict/get/${id}`).json();
  if (json.code === 200) {
    updateDictRuleForm.id = json.data.id
    updateDictRuleForm.bookId = json.data.bookId
    updateDictRuleForm.bookName = json.data.bookName
    updateDictRuleForm.bookSize = json.data.bookSize
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
              <label for="firstNameHorizontalIcons">词典 id</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextField
                id="firstNameHorizontalIcons"
                v-model="updateDictRuleForm.bookId"
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
              <label for="emailHorizontalIcons">词典名称</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextField
                id="emailHorizontalIcons"
                v-model="updateDictRuleForm.bookName"
                prepend-inner-icon="mdi-form-textbox"
                placeholder="词典名称"
                persistent-placeholder
              />
            </VCol>
          </VRow>
        </VCol>

        <VCol cols="12">
          <VRow no-gutters>
            <VCol cols="12">
              <label for="mobileHorizontalIcons">词典词数</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextField
                id="mobileHorizontalIcons"
                v-model="updateDictRuleForm.bookSize"
                prepend-inner-icon="mdi-counter"
                placeholder="词典词数"
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
