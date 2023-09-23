<script setup lang="ts">
import {
  VDataTableServer,
} from 'vuetify/labs/VDataTable'

const nuxtApp = useNuxtApp()
const router = useRouter()
const loading = ref(false)
const dialogDelete = ref(false)
const defaultWord = ref({})
const toast = useToast()
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
  { title: '单词序号', align: 'start', sortable: false, key: 'wordRank' },
  { title: '单词', key: 'headWord', sortable: false },
  { title: '词典 id', key: 'bookId', sortable: false },
  { title: '单词数据 id', key: 'wordJsonId', sortable: false },
  { title: 'Actions', key: 'actions', sortable: false },
])
const useWordPage = async () => {
  loading.value = true
  data.queryParam.pageNum = pageInfo.pageNum
  data.queryParam.pageSize = pageInfo.pageSize
  dataList.value = []
  const json = await nuxtApp.$api.get('/@api/word/wordPage', {
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

/** 单词删除 */
const handleWordDelete = async () => {
  const json = await nuxtApp.$api.delete(`/@api/word/delete/${defaultWord.value.id}`).json();
  if (json.code === 200) {
    toast.add({ title: '删除成功!', timeout: 1000, ui: { width: 'w-full sm:w-96' }})
  }
  defaultWord.value = {}
  dialogDelete.value = false
  await useWordPage()
}

definePageMeta({
  layout: 'admin',
})
</script>

<template>
  <div>
    <v-data-table-server
      overflow-y-auto
      fixed-header
      height="650px"
      :headers="headers"
      :items="dataList"
      :items-length="pageInfo.total"
      :loading="loading"
      items-per-page-text="选择"
      :items-per-page-options="pageOptions"
      @update:options="(current) => { pageInfo.pageNum = current.page; pageInfo.pageSize = current.itemsPerPage; useWordPage() }"
      class="elevation-1"
    >
      <template v-slot:top>
        <div flex items-center px-2 h-12>
          <v-btn title="新增" prepend-icon="mdi-format-annotation-plus" @click="router.push('/admin/word/add')">
            新增
          </v-btn>
        </div>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-icon
          title="详情"
          cursor-pointer
          size="small"
          @click="router.push({ path: `/admin/word/detail/${item.raw.id}` })"
        >
          mdi-clipboard-account
        </v-icon>
        <v-icon
          title="编辑"
          cursor-pointer
          size="small"
          @click="router.push({ path: `/admin/word/edit/${item.raw.id}` })"
        >
          mdi-pencil
        </v-icon>
        <v-icon
          title="删除"
          cursor-pointer
          size="small"
          @click="defaultWord = item.raw; dialogDelete = true"
        >
          mdi-delete
        </v-icon>
      </template>
    </v-data-table-server>
    <v-dialog
      v-model="dialogDelete"
      width="auto"
    >
      <v-card>
        <v-card-text>
          确定要删除吗？
        </v-card-text>
        <v-card-actions>
          <v-btn title="确定" color="primary" block @click="handleWordDelete">确定</v-btn>
        </v-card-actions>
        <v-card-actions>
          <v-btn title="取消" color="primary" block @click="dialogDelete = false">取消</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
