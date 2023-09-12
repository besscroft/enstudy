<script setup lang="ts">
import { User } from '~/types/interfaces/user'

const router = useRouter()
const nuxtApp = useNuxtApp()
const loading = ref<boolean>(false)
const toast = useToast()

const items = ref([
  { title: '超级管理员', value: 'platform-super-admin' },
  { title: '平台管理员', value: 'platform-admin' },
  { title: '平台运维员', value: 'platform-self-provisioner' },
  { title: '平台观察员', value: 'platform-view' },
  { title: '游客', value: 'platform-visitor' },
])

const updateUserRuleForm = reactive<User.UpdateUserRequestData>({
  /** 用户id */
  id: undefined,
  /** 头像 */
  avatar: undefined,
  /** 角色 */
  role: undefined,
  /** 邮箱 */
  email: undefined,
  /** 昵称 */
  name: undefined,
  /** 手机号 */
  telephone: undefined,
  /** 备注 */
  remark: undefined,
})

const submitHandler = async () => {
  loading.value = true
  try {
    const json = await nuxtApp.$api.put('/@api/user/update', {
      json: updateUserRuleForm,
    }).json();
    if (json.code === 200) {
      toast.add({ title: '更新成功!', timeout: 1000, ui: { width: 'w-full sm:w-96' }})
    } else {
      toast.add({ title: json.message, timeout: 1000, ui: { width: 'w-full sm:w-96' }})
    }
  } catch (e) {
    toast.add({ title: e.message, timeout: 1000, ui: { width: 'w-full sm:w-96' }})
    loading.value = false
  }
  loading.value = false
}

onMounted(async () => {
  const id = Number(router.currentRoute.value.params.id)
  const json = await nuxtApp.$api.get(`/@api/user/getUser/${id}`).json();
  if (json.code === 200) {
    updateUserRuleForm.id = json.data.id
    updateUserRuleForm.avatar = json.data.avatar
    updateUserRuleForm.role = json.data.role
    updateUserRuleForm.email = json.data.email
    updateUserRuleForm.name = json.data.name
    updateUserRuleForm.telephone = json.data.telephone
    updateUserRuleForm.remark = json.data.remark
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
              <label for="firstNameHorizontalIcons">头像</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextField
                id="firstNameHorizontalIcons"
                v-model="updateUserRuleForm.avatar"
                prepend-inner-icon="mdi-account-box-outline"
                placeholder="头像"
                persistent-placeholder
              />
            </VCol>
          </VRow>
        </VCol>

        <VCol cols="12">
          <VRow no-gutters>
            <VCol cols="12">
              <label for="emailHorizontalIcons">邮箱</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextField
                id="emailHorizontalIcons"
                v-model="updateUserRuleForm.email"
                prepend-inner-icon="mdi-email-outline"
                placeholder="邮箱"
                persistent-placeholder
              />
            </VCol>
          </VRow>
        </VCol>

        <VCol cols="12">
          <VRow no-gutters>
            <VCol cols="12">
              <label for="mobileHorizontalIcons">角色</label>
            </VCol>

            <VCol cols="12" md="9">
              <v-select
                label="选择角色"
                :items="items"
                v-model="updateUserRuleForm.role"
                item-title="title"
                item-value="value"
              ></v-select>
            </VCol>
          </VRow>
        </VCol>

        <VCol cols="12">
          <VRow no-gutters>
            <VCol cols="12">
              <label for="mobileHorizontalIcons">昵称</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextField
                id="mobileHorizontalIcons"
                v-model="updateUserRuleForm.name"
                prepend-inner-icon="mdi-form-textbox"
                placeholder="昵称"
                persistent-placeholder
              />
            </VCol>
          </VRow>
        </VCol>

        <VCol cols="12">
          <VRow no-gutters>
            <VCol cols="12">
              <label for="mobileHorizontalIcons">手机号</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextField
                id="mobileHorizontalIcons"
                v-model="updateUserRuleForm.telephone"
                prepend-inner-icon="mdi-cellphone"
                placeholder="手机号"
                persistent-placeholder
              />
            </VCol>
          </VRow>
        </VCol>

        <VCol cols="12">
          <VRow no-gutters>
            <VCol cols="12">
              <label for="mobileHorizontalIcons">备注</label>
            </VCol>

            <VCol cols="12" md="9">
              <VTextField
                id="mobileHorizontalIcons"
                v-model="updateUserRuleForm.remark"
                prepend-inner-icon="mdi-information-variant-circle-outline"
                placeholder="备注"
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

<style scoped>

</style>