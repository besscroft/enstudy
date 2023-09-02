<script setup lang="ts">
const nuxtApp = useNuxtApp()

const dataList = ref<Array<any>>([])
const pageInfo = reactive({
  total: 0,
  totalPage: 0,
  pageNum: 1,
  pageSize: 10,
})
const roleFlag = ref<string>('')
const data = reactive({
  form: {},
  queryParam: {
    pageNum: 1,
    pageSize: 10,
    role: '',
  },
})

const useUserPage = async (role: string) => {
  if (role) {
    data.queryParam.role = role
    roleFlag.value = role.toString()
  } else {
    data.queryParam.role = ''
    roleFlag.value = ''
  }
  data.queryParam.pageNum = pageInfo.pageNum
  data.queryParam.pageSize = pageInfo.pageSize
  dataList.value = []
  const json = await nuxtApp.$api.get('/@api/user/userPage', {
    searchParams: data.queryParam,
  }).json();
  if (json.code === 200) {
    pageInfo.total = json.data.total
    pageInfo.totalPage = json.data.totalPage
    dataList.value = json.data.list
  } else {
    console.log(json.message)
  }
}

onBeforeMount(async () => {
  await useUserPage('')
})

definePageMeta({
  layout: 'admin',
})
</script>

<template>
  用户
</template>

<style scoped>

</style>