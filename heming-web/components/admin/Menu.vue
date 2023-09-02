<script setup lang="ts">
const router = useRouter()
const activeItem = ref<string | null>(null)

watch(router.currentRoute, (val) => {
  activeItem.value = val.path
})

onMounted(() => {
  activeItem.value = router.currentRoute.value.path
})
</script>

<template>
  <div flex flex-col h-full data-te-sidenav-menu-ref>
    <div flex items-center justify-center px-2 py-3>
      <p font-ark text-xl pl-4 text-yellow-400 cursor-pointer @click="router.push('/')">嗯学英语</p>
    </div>
    <div p-2 h-full>
      <ul class="space-y-1">
        <li>
          <p
              cursor-pointer block rounded-lg px-4 py-2 text-sm font-ark hover:bg-gray-100 hover:text-gray-700
              :class="activeItem === '/admin' ? 'bg-gray-100 text-gray-700' : 'text-gray-500'"
              @click="router.push('/admin')"
          >
            控制台
          </p>
        </li>

        <li>
          <details
              class="group [&_summary::-webkit-details-marker]:hidden"
              :open="activeItem === '/admin/user'"
          >
            <summary
                flex cursor-pointer items-center justify-between rounded-lg px-4 py-2 hover:bg-gray-100 hover:text-gray-700
                :class="activeItem === '/admin/user' ? 'bg-gray-100 text-gray-700' : 'text-gray-500'"
            >
              <span text-sm font-ark> 系统管理 </span>

              <span shrink-0 transition duration-300 group-open:-rotate-180>
                  <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-5 w-5"
                      viewBox="0 0 20 20"
                      fill="currentColor"
                  >
                    <path
                        fill-rule="evenodd"
                        d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
                        clip-rule="evenodd"
                    />
                  </svg>
                </span>
            </summary>

            <ul mt-2 space-y-1 px-4>
              <li>
                <p
                    @click="router.push('/admin/user')"
                    :class="activeItem === '/admin/user' ? 'bg-gray-100 text-gray-700' : 'text-gray-500'"
                    cursor-pointer block rounded-lg px-4 py-2 text-sm font-ark hover:bg-gray-100 hover:text-gray-700
                >
                  用户管理
                </p>
              </li>
            </ul>
          </details>
        </li>

        <li>
          <details
              class="group [&_summary::-webkit-details-marker]:hidden"
              :open="activeItem === '/admin/dict' || activeItem === '/admin/word' || activeItem === '/admin/feedback'"
          >
            <summary
                flex cursor-pointer items-center justify-between rounded-lg px-4 py-2 hover:bg-gray-100 hover:text-gray-700
                :class="activeItem === '/admin/dict' || activeItem === '/admin/word' || activeItem === '/admin/feedback' ? 'bg-gray-100 text-gray-700' : 'text-gray-500'"
            >
              <span text-sm font-ark> 运营管理 </span>

              <span shrink-0 transition duration-300 group-open:-rotate-180>
                  <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-5 w-5"
                      viewBox="0 0 20 20"
                      fill="currentColor"
                  >
                    <path
                        fill-rule="evenodd"
                        d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
                        clip-rule="evenodd"
                    />
                  </svg>
                </span>
            </summary>

            <ul mt-2 space-y-1 px-4>
              <li>
                <p
                    @click="router.push('/admin/dict')"
                    :class="activeItem === '/admin/dict' ? 'bg-gray-100 text-gray-700' : 'text-gray-500'"
                    cursor-pointer block rounded-lg px-4 py-2 text-sm font-ark hover:bg-gray-100 hover:text-gray-700
                >
                  词典管理
                </p>
              </li>
              <li>
                <p
                    @click="router.push('/admin/word')"
                    :class="activeItem === '/admin/word' ? 'bg-gray-100 text-gray-700' : 'text-gray-500'"
                    cursor-pointer block rounded-lg px-4 py-2 text-sm font-ark hover:bg-gray-100 hover:text-gray-700
                >
                  单词维护
                </p>
              </li>
              <li>
                <p
                    @click="router.push('/admin/feedback')"
                    :class="activeItem === '/admin/feedback' ? 'bg-gray-100 text-gray-700' : 'text-gray-500'"
                    cursor-pointer block rounded-lg px-4 py-2 text-sm font-ark hover:bg-gray-100 hover:text-gray-700
                >
                  反馈管理
                </p>
              </li>
            </ul>
          </details>
        </li>
      </ul>
    </div>
    <div items-end p-2>
      菜单底部
    </div>
  </div>
</template>

<style scoped>

</style>