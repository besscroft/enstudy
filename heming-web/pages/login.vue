<script setup lang="ts">
import { Login } from '../types'
import { useUserStore } from '../stores/user'
import ky from 'ky'

const user = useUserStore()
const router = useRouter()
const toast = useToast()
const nuxtApp = useNuxtApp()

const loginForm = reactive<Login.ReqLoginForm>({
  username: 'view',
  password: '666666',
})

const handUserInfo = async () => {
  const json = await nuxtApp.$api.get('/@api/user/info').json();
  if (json.code === 200) {
    user.setUserName(json.data.userName)
    user.setAvatar(json.data.avatar)
    user.setRoleCode(json.data.role)
    user.setEmail(json.data.email)
    setTimeout(() => {
      router.push('/learn')
    }, 888)
  } else {
    console.log(json.message)
  }
}

const handleSubmitClick = async () => {
  const json = await ky.post('/@api/user/login', {
    json: {username: loginForm.username, password: loginForm.password}
  }).json();
  if (json.code === 200) {
    user.setToken(json.data.tokenValue)
    user.setTokenName(json.data.tokenName)
    toast.add({ title: '登录成功!', timeout: 1000, ui: { width: 'w-full sm:w-96' }})
    await handUserInfo()
  } else {
    console.log(json.message)
  }
}

const keyDown = (e) => {
  if (e.keyCode === 13 || e.keyCode === 100) {
    handleSubmitClick()
  }
}

onMounted(() => {
  window.addEventListener('keydown', keyDown)
})

onUnmounted(() => {
  window.removeEventListener('keydown', keyDown, false)
})

definePageMeta({
  layout: 'default',
})
</script>

<template>
  <div class="bg-white dark:bg-gray-900">
    <div class="flex justify-center h-screen">
      <div class="hidden bg-cover lg:block lg:w-2/3" style="background-image: url(https://images.unsplash.com/photo-1616763355603-9755a640a287?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80)">
        <div class="flex items-center h-full px-20 bg-gray-900 bg-opacity-40">
          <div>
            <h2 class="text-4xl font-ark text-white">嗯学英语</h2>

            <p class="max-w-xl font-ark mt-3 text-gray-300">学英语，就嗯学！</p>

            <p text-sm font-ark text-gray-300 mt-4>
              鹤鸣工作室出品，一款基于 SpringBoot 3 和 Nuxt3 的脚手架。本项目基于 HeMing Fast 构建！
            </p>
          </div>
        </div>
      </div>

      <div class="flex items-center w-full max-w-md px-6 mx-auto lg:w-2/6">
        <div class="flex-1">
          <div class="text-center">
            <h2 class="text-4xl font-ark text-center text-gray-700 dark:text-white">嗯学英语</h2>

            <p class="mt-3 font-ark text-gray-500 dark:text-gray-300">登录你的帐号</p>
          </div>

          <div class="mt-8">
            <div>
              <label for="account" class="font-ark block mb-2 text-sm text-gray-600 dark:text-gray-200">帐号</label>
              <input type="account" v-model="loginForm.username" name="account" id="account" class="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-400 bg-white border border-gray-200 rounded-md dark:placeholder-gray-600 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-700 focus:border-blue-400 dark:focus:border-blue-400 focus:ring-blue-400 focus:outline-none focus:ring focus:ring-opacity-40" />
            </div>

            <div class="mt-6">
              <div class="flex justify-between mb-2">
                <label for="password" class="font-ark text-sm text-gray-600 dark:text-gray-200">密码</label>
                <a href="#" class="font-ark text-sm text-gray-400 focus:text-blue-500 hover:text-blue-500 hover:underline">忘记密码？</a>
              </div>

              <input type="password" v-model="loginForm.password" name="password" id="password" class="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-400 bg-white border border-gray-200 rounded-md dark:placeholder-gray-600 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-700 focus:border-blue-400 dark:focus:border-blue-400 focus:ring-blue-400 focus:outline-none focus:ring focus:ring-opacity-40" />
            </div>

            <div class="mt-6">
              <button
                  @click="handleSubmitClick"
                  class="font-ark w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-blue-500 rounded-md hover:bg-blue-400 focus:outline-none focus:bg-blue-400 focus:ring focus:ring-blue-300 focus:ring-opacity-50">
                登录
              </button>
            </div>

            <p class="font-ark mt-6 text-sm text-center text-gray-400">还没有帐号？<a href="#" class="text-blue-500 focus:outline-none focus:underline hover:underline">立即注册</a>.</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
