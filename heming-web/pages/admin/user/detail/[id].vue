<script setup lang="ts">
const router = useRouter()
const nuxtApp = useNuxtApp()
const info = ref({})
const toast = useToast()

onMounted(async () => {
  const id = Number(router.currentRoute.value.params.id)
  try {
    const json = await nuxtApp.$api.get(`/@api/user/getUser/${id}`).json();
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

definePageMeta({
  layout: 'admin',
})
</script>

<template>
  <div>
    <VCard title="用户信息">
      <template v-slot:prepend>
        <v-btn density="compact" icon="mdi-arrow-left" title="返回" @click="router.push('/admin/user')"></v-btn>
      </template>
      <VCardText class="d-flex">
        <!-- 👉 Avatar -->
        <VAvatar
          rounded="lg"
          size="100"
          class="me-6"
          :image="info.avatar"
        />
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
            <td>账号</td>
            <td>{{ info.username }}</td>
          </tr>
          <tr>
            <td>角色</td>
            <td>
              <v-chip v-if="info.role === 'platform-super-admin'" class="ma-2" color="secondary" variant="elevated">超级管理员</v-chip>
              <v-chip v-if="info.role === 'platform-admin'" class="ma-2" color="secondary" variant="elevated">平台管理员</v-chip>
              <v-chip v-if="info.role === 'platform-self-provisioner'" class="ma-2" color="secondary" variant="elevated">平台运维员</v-chip>
              <v-chip v-if="info.role === 'platform-view'" class="ma-2" color="secondary" variant="elevated">平台观察员</v-chip>
              <v-chip v-if="info.role === 'platform-visitor'" class="ma-2" color="secondary" variant="elevated">游客</v-chip>
            </td>
          </tr>
          <tr>
            <td>邮箱</td>
            <td>{{ info.email }}</td>
          </tr>
          <tr>
            <td>昵称</td>
            <td>{{ info.name }}</td>
          </tr>
          <tr>
            <td>手机</td>
            <td>{{ info.telephone }}</td>
          </tr>
          <tr>
            <td>备注</td>
            <td>{{ info.remark }}</td>
          </tr>
          <tr>
            <td>最后登录时间</td>
            <td>{{ info.loginTime }}</td>
          </tr>
          <tr>
            <td>帐号启用状态</td>
            <td>
              <v-chip
                v-if="info.status === 1"
                class="ma-2"
                color="secondary"
                variant="elevated"
              >
                启用
              </v-chip>
              <v-chip
                v-else
                class="ma-2"
                color="red"
                text-color="white"
                variant="elevated"
              >
                禁用
              </v-chip>
            </td>
          </tr>
          </tbody>
        </v-table>
      </VCardText>
    </VCard>
  </div>
</template>
