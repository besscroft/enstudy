<script setup lang="ts">
import { NAvatar, NButton, useMessage } from 'naive-ui'
import type { DataTableColumns } from 'naive-ui'

const message = useMessage()
const nuxtApp = useNuxtApp()

const dataList = ref([])
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
    pageSize: 8,
    role: '',
  },
})

const createColumns = ({ handInfo }: { handInfo: (row: any) => void }): DataTableColumns<any> => {
  return [
    {
      title: '账号',
      key: 'username'
    },
    {
      title: '邮箱',
      key: 'email'
    },
    {
      title: '角色',
      key: 'role'
    },
    {
      title: '头像',
      key: 'avatar',
      render (row) {
        return h(
            NAvatar,
            {
              src: row.avatar,
              size: 'small',
              round: true
            }
        )
      }
    },
    {
      title: 'Action',
      key: 'actions',
      render (row) {
        return h(
            NButton,
            {
              strong: true,
              tertiary: true,
              size: 'small',
              onClick: () => handInfo(row)
            },
            { default: () => '详情' }
        )
      }
    }
  ]
}

const columns = createColumns({
  handInfo (row: any) {
    message.info(`Play ${row.id}`)
  }
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
  <n-data-table
      :columns="columns"
      :data="dataList"
      :bordered="false"
  />
  <n-pagination
      v-if="pageInfo.totalPage > 1"
      v-model:page="pageInfo.pageNum"
      v-model:page-size="pageInfo.pageSize"
      :page-count="pageInfo.totalPage"
      @update:page="(current) => { pageInfo.pageNum = current; useUserPage(roleFlag) }"
  />
</template>

<style scoped>

</style>