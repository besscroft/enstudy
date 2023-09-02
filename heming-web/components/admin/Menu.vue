<script setup lang="ts">
import { useUserStore } from '~/stores/user'

const router = useRouter()
const user = useUserStore()
const activeItem = ref<string | null>(null)
const open = ref([''])

const handleOpen = (item: string) => {
  if (item === '/admin/dict' || item === '/admin/word' || item === '/admin/feedback') {
    open.value = ['Business']
  } else if (item === '/admin/user') {
    open.value = ['System']
  }
}

watch(router.currentRoute, (val) => {
  activeItem.value = val.path
  handleOpen(val.path)
})

onMounted(() => {
  activeItem.value = router.currentRoute.value.path
  handleOpen(router.currentRoute.value.path)
})
</script>

<template>
  <div flex flex-col h-full data-te-sidenav-menu-ref>
    <div flex items-center justify-center px-2 py-3>
      <v-avatar color="info" size="x-large">
        <v-img :src="user.avatar" alt="用户头像"></v-img>
      </v-avatar>
    </div>
    <div h-full>
      <v-list
        density="compact"
        v-model:opened="open"
        nav
      >
        <v-list-item
          prepend-icon="mdi-view-dashboard"
          title="控制台"
          value="admin"
          :active="activeItem === '/admin'"
          @click="router.push('/admin')"
        ></v-list-item>
        <v-list-group value="System">
          <template v-slot:activator="{ props }">
            <v-list-item
              v-bind="props"
              prepend-icon="mdi-account-cog"
              title="系统管理"
            ></v-list-item>
          </template>
          <v-list-item
            prepend-icon="mdi-account-multiple-outline"
            title="用户管理"
            value="user"
            :active="activeItem === '/admin/user'"
            @click="router.push('/admin/user')"
          ></v-list-item>
        </v-list-group>
        <v-list-group value="Business">
          <template v-slot:activator="{ props }">
            <v-list-item
              v-bind="props"
              prepend-icon="mdi-puzzle-outline"
              title="数据维护"
            ></v-list-item>
          </template>
          <v-list-item
            prepend-icon="mdi-book-alphabet"
            title="词典管理"
            value="dict"
            :active="activeItem === '/admin/dict'"
            @click="router.push('/admin/dict')"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-alphabetical-variant"
            title="单词维护"
            value="word"
            :active="activeItem === '/admin/word'"
            @click="router.push('/admin/word')"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-message-alert-outline"
            title="反馈管理"
            value="feedback"
            :active="activeItem === '/admin/feedback'"
            @click="router.push('/admin/feedback')"
          ></v-list-item>
        </v-list-group>
      </v-list>
    </div>
    <div items-end p-2>
      菜单底部
    </div>
  </div>
</template>

<style scoped>

</style>