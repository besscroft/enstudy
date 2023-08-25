<script setup lang="ts">
import { Login } from '../types'
import { useUserStore } from '../stores/user'

const user = useUserStore()
const router = useRouter()

const loginForm = reactive<Login.ReqLoginForm>({
  username: 'enstudy',
  password: '',
})

const handleSubmitClick = async () => {
  await $fetch('/@api/user/login', {
    method: 'POST',
    body: {username: loginForm.username, password: loginForm.password},
    parseResponse: JSON.parse,
  }).then((res: any) => {
    if (res.code === 200) {
      user.setToken(res.data.tokenValue)
      // 等待一秒
      setTimeout(() => {
        router.push('/')
      }, 1000)
    } else {
      console.log(res.message)
    }
  })
}
</script>

<template>
  <section bg-white>
    <div lg:grid lg:min-h-screen lg:grid-cols-12>
      <section relative flex h-32 items-end bg-gray-900 lg:col-span-5 lg:h-full xl:col-span-6>
        <img
            alt="Night"
            src="https://images.unsplash.com/photo-1617195737496-bc30194e3a19?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"
            absolute inset-0 h-full w-full object-cover opacity-80
        />

        <div chidden lg:relative lg:block lg:p-12>

          <h2 mt-6 text-2xl font-bold text-white sm:text-3xl md:text-4xl>
            HeMing Fast
          </h2>

          <p mt-4 leading-relaxed  class="text-white/90">
            HeMing 工作室出品，一款基于 SpringBoot 3 和 Nuxt3 的脚手架
          </p>
        </div>
      </section>

      <main flex flex-col items-center justify-center px-4 py-4 sm:px-12 lg:col-span-7 lg:px-16 lg:py-12 xl:col-span-6>
        <h2 mt-6 text-2xl font-bold sm:text-3xl md:text-4xl>
          HeMing Fast
        </h2>
        <div flex items-center justify-center mt-2>
          <p w-16>用户名</p>
          <input
              type="text"
              v-model="loginForm.username"
              id="username"
              name="username"
              required
              border-2 rounded p-1 border-teal-400
          />
        </div>
        <div flex items-center justify-center mt-2>
          <p w-16>密码</p>
          <input
              type="text"
              v-model="loginForm.password"
              id="password"
              name="password"
              required
              border-2 rounded p-1 border-teal-400
          />
        </div>
        <button
            name="button"
            @click="handleSubmitClick"
            mt-2 border-2 rounded p-1 border-teal-400
        >登录</button>
      </main>
    </div>
  </section>
</template>

<style scoped>

</style>
