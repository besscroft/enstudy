<script setup lang="ts">
import ky from 'ky'
import { NButton, useMessage } from 'naive-ui'
import type { DataTableColumns } from 'naive-ui'

const pagination = ref(false)
const message = useMessage()

type Song = {
  no: number
  title: string
  length: string
}

const createColumns = ({ play }: { play: (row: Song) => void }): DataTableColumns<Song> => {
  return [
    {
      title: 'No',
      key: 'no'
    },
    {
      title: 'Title',
      key: 'title'
    },
    {
      title: 'Length',
      key: 'length'
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
              onClick: () => play(row)
            },
            { default: () => 'Play' }
        )
      }
    }
  ]
}

const data: Song[] = [
  { no: 3, title: 'Wonderwall', length: '4:18' },
  { no: 4, title: "Don't Look Back in Anger", length: '4:48' },
  { no: 12, title: 'Champagne Supernova', length: '7:27' }
]

const columns = createColumns({
  play (row: Song) {
    message.info(`Play ${row.title}`)
  }
})

const dataList = ref([])

onUnmounted(async () => {
  const json = await ky.post('/@api/user/userPage', {
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
      :data="data"
      :pagination="pagination"
      :bordered="false"
  />
</template>

<style scoped>

</style>