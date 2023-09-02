<script setup lang="ts">
import {
  VDataTableServer,
} from 'vuetify/labs/VDataTable'

const nuxtApp = useNuxtApp()
const loading = ref(false)
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
const pageOptions = ref([
  {value: 10, title: '10'},
  {value: 25, title: '25'},
  {value: 50, title: '50'},
  {value: 100, title: '100'},
])

const headers = ref([
  { title: '词典id', align: 'start', sortable: false, key: 'bookId' },
  { title: '词典名称', key: 'bookName', sortable: false },
  { title: '词典词数', key: 'bookSize', sortable: false },
  { title: 'Actions', key: 'actions', sortable: false },
])
const useDictPage = async () => {
  loading.value = true
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
  loading.value = false
}

definePageMeta({
  layout: 'admin',
})
</script>

<template>
  <v-data-table-server
    fixed-header
    height="680px"
    :headers="headers"
    :items="dataList"
    :items-length="pageInfo.total"
    :loading="loading"
    items-per-page-text="选择"
    :items-per-page-options="pageOptions"
    @update:options="(current) => { pageInfo.pageNum = current.page; pageInfo.pageSize = current.itemsPerPage; useDictPage() }"
    class="elevation-1"
  >
    <template v-slot:item.actions="{ item }">
      <v-icon
        cursor-pointer
        size="small"
      >
        mdi-pencil
      </v-icon>
      <v-icon
        cursor-pointer
        size="small"
      >
        mdi-delete
      </v-icon>
    </template>
  </v-data-table-server>
</template>

<style scoped>

</style>