<script setup lang="ts">
const router = useRouter()

// 面包屑路由
const routes = ref<Array<any>>([
  {
    href: '/admin',
    title: '控制台',
  },
])

/** 路由变化 */
const handleRouterChange = async (uri: any) => {
  routes.value = []
  routes.value.push({
    href: '/admin',
    title: '控制台',
  })
  if (uri === '/admin/user') {
    routes.value.push({
      href: '/admin/user',
      title: '用户管理',
    })
  }
  if (uri === '/admin/dict') {
    routes.value.push({
      href: '/admin/dict',
      title: '词典管理',
    })
  }
  if (uri === '/admin/word') {
    routes.value.push({
      href: '/admin/word',
      title: '单词维护',
    })
  }
  if (uri === '/admin/feedback') {
    routes.value.push({
      href: '/admin/feedback',
      title: '反馈管理',
    })
  }
}

const handleClick = async (path: string) => {
  await handleRouterChange(path)
  router.replace(path)
}

/** 路由监听处理 */
watch(() => {
  return router.currentRoute.value.path
}, async (path) => {
  if (path.startsWith('/admin')) {
    await handleRouterChange(path)
  }
})

onBeforeMount(async () => {
  const path = router.currentRoute.value.path
  await handleRouterChange(path)
})
</script>

<template>
  <div flex flex-nowrap items-center gap-1 text-sm text-gray-600>
    <span v-for="(item, index) in routes" :key="item" flex items-center>
      <a style="white-space: nowrap" cursor-pointer transition hover:text-gray-700 @click="handleClick(item.href)"> {{ item.title }} </a>
      <svg
          v-if="index !== routes.length - 1"
          xmlns="http://www.w3.org/2000/svg"
          class="h-4 w-4"
          viewBox="0 0 20 20"
          fill="currentColor"
      >
        <path
            fill-rule="evenodd"
            d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
            clip-rule="evenodd"
        />
      </svg>
    </span>
  </div>
</template>

<style scoped>

</style>