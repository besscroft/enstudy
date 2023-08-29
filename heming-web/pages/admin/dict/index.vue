<script setup lang="ts">
import { NButton, useMessage} from 'naive-ui'
import type { DataTableColumns } from 'naive-ui'

const message = useMessage()
const nuxtApp = useNuxtApp()

const dataList = ref<Array<any>>([])
const pageInfo = reactive({
  total: 0,
  totalPage: 0,
  pageNum: 1,
  pageSize: 10,
})
const data = reactive({
  form: {},
  queryParam: {
    pageNum: 1,
    pageSize: 10,
  },
})

const createColumns = ({ handInfo }: { handInfo: (row: any) => void }): DataTableColumns<any> => {
  return [
    {
      title: '词典id',
      key: 'bookId'
    },
    {
      title: '词典名称',
      key: 'bookName'
    },
    {
      title: '词典词数',
      key: 'bookSize'
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

const useDictPage = async () => {
  data.queryParam.pageNum = pageInfo.pageNum
  data.queryParam.pageSize = pageInfo.pageSize
  dataList.value = []
  const json = await nuxtApp.$api.get('/@api/bookDict/dictPage', {
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
  await useDictPage()
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
  <div flex justify-end items-center pt-1>
    <n-pagination
        v-if="pageInfo.totalPage > 1"
        v-model:page="pageInfo.pageNum"
        v-model:page-size="pageInfo.pageSize"
        :page-count="pageInfo.totalPage"
        :page-slot="5"
        @update:page="(current) => { pageInfo.pageNum = current; useDictPage() }"
    />
    <p ml-2>共 {{ pageInfo.total }} 条</p>
  </div>
</template>

<style scoped>

</style>