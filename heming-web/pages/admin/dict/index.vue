<script setup lang="ts">
import {NAvatar, NButton, useMessage} from 'naive-ui'
import type { DataTableColumns } from 'naive-ui'

const pagination = ref(false)
const message = useMessage()
const nuxtApp = useNuxtApp()

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

const dataList = ref([])

onBeforeMount(async () => {
  const json = await nuxtApp.$api.get('/@api/bookDict/dictPage', {
    searchParams: {pageNum: 1, pageSize: 10}
  }).json();
  if (json.code === 200) {
    dataList.value = json.data.list
  } else {
    console.log(json.message)
  }
})

definePageMeta({
  layout: 'admin',
})
</script>

<template>
  <n-data-table
      :columns="columns"
      :data="dataList"
      :pagination="pagination"
      :bordered="false"
  />
</template>

<style scoped>

</style>