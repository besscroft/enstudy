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
const roleFlag = ref<string>('')
const data = reactive({
  form: {},
  queryParam: {
    pageNum: 1,
    pageSize: 10,
    role: '',
  },
})
const pageOptions = ref([
  {value: 10, title: '10'},
  {value: 25, title: '25'},
  {value: 50, title: '50'},
  {value: 100, title: '100'},
])

const headers = ref([
  { title: '昵称', align: 'start', sortable: false, key: 'name' },
  { title: '账号', key: 'username', sortable: false },
  { title: '邮箱', key: 'email', sortable: false },
  { title: '角色', key: 'role', sortable: false },
  { title: '头像', key: 'avatar', sortable: false },
  { title: 'Actions', key: 'actions', sortable: false },
])

const useUserPage = async (role: string) => {
  loading.value = true
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
    @update:options="(current) => { pageInfo.pageNum = current.page; pageInfo.pageSize = current.itemsPerPage; useUserPage('') }"
    class="elevation-1"
  >
    <template v-slot:item.avatar="{ item }">
      <v-avatar color="primary" size="x-small">
        <v-img
          :src="item.columns.avatar"
          alt="头像"
        ></v-img>
      </v-avatar>
    </template>
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