<script setup lang="ts">
import { Login } from '../types'
import { useUserStore } from '../stores/user'
import ky from 'ky'

const user = useUserStore()
const router = useRouter()

const loginForm = reactive<Login.ReqLoginForm>({
  username: 'view',
  password: '666666',
})

const handleSubmitClick = async () => {
  const json = await ky.post('/@api/user/login', {
    json: {username: loginForm.username, password: loginForm.password}
  }).json();
  if (json.code === 200) {
    user.setToken(json.data.tokenValue)
    user.setTokenName(json.data.tokenName)
    // 等待一秒
    setTimeout(() => {
      router.push('/learn')
    }, 500)
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
</script>

<template>
  <section gradient-form h-full bg-gray-50 dark:bg-neutral-700>
    <div h-full p-10>
      <div g-6 flex h-full flex-wrap items-center justify-center text-neutral-800 dark:text-neutral-200>
        <div w-full>
          <div block rounded-lg bg-white shadow-lg dark:bg-neutral-800>
            <div g-0 lg:flex lg:flex-wrap>
              <div px-4 md:px-0 class="lg:w-6/12">
                <div md:mx-6 md:p-12>
                  <!--Logo-->
                  <div text-center>
                    <p text-2xl font-ark m:text-3xl md:text-4xl>嗯学英语</p>
                    <h4 mb-12 mt-1 pb-1 text-xl font-ark>
                      学英语，就嗯学！
                    </h4>
                  </div>

                  <form>
                    <div relative mb-4 data-te-input-wrapper-init>
                      <p font-ark>用户名</p>
                      <input
                          type="text"
                          class="border-2 peer block min-h-[auto] w-full rounded border-0 bg-transparent px-3 py-[0.32rem] leading-[1.6] outline-none transition-all duration-200 ease-linear focus:placeholder:opacity-100 data-[te-input-state-active]:placeholder:opacity-100 motion-reduce:transition-none dark:placeholder:text-neutral-200 [&:not([data-te-input-placeholder-active])]:placeholder:opacity-0"
                          id="exampleFormControlInput1"
                          v-model="loginForm.username" />
                    </div>
                    <div relative mb-4 data-te-input-wrapper-init>
                      <p font-ark>密码</p>
                      <input
                          type="password"
                          class="border-2 peer block min-h-[auto] w-full rounded border-0 bg-transparent px-3 py-[0.32rem] leading-[1.6] outline-none transition-all duration-200 ease-linear focus:placeholder:opacity-100 data-[te-input-state-active]:placeholder:opacity-100 motion-reduce:transition-none dark:placeholder:text-neutral-200 [&:not([data-te-input-placeholder-active])]:placeholder:opacity-0"
                          id="exampleFormControlInput11"
                          v-model="loginForm.password" />
                    </div>
                    <div mb-12 pb-1 pt-1 text-center>
                      <button
                          class="font-ark mb-3 inline-block w-full rounded px-6 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_rgba(0,0,0,0.2)] transition duration-150 ease-in-out hover:shadow-[0_8px_9px_-4px_rgba(0,0,0,0.1),0_4px_18px_0_rgba(0,0,0,0.2)] focus:shadow-[0_8px_9px_-4px_rgba(0,0,0,0.1),0_4px_18px_0_rgba(0,0,0,0.2)] focus:outline-none focus:ring-0 active:shadow-[0_8px_9px_-4px_rgba(0,0,0,0.1),0_4px_18px_0_rgba(0,0,0,0.2)]"
                          type="button"
                          data-te-ripple-init
                          data-te-ripple-color="light"
                          @click="handleSubmitClick"
                          style="
                        background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
                      ">
                        登录
                      </button>
                      <a font-ark href="#!">忘记密码？</a>
                    </div>
                    <div flex items-center justify-between pb-6>
                      <p mb-0 mr-2 font-ark>还没有帐号？</p>
                      <button
                          type="button"
                          class="font-ark inline-block rounded border-2 border-danger px-6 pb-[6px] pt-2 text-xs font-medium uppercase leading-normal text-danger transition duration-150 ease-in-out hover:border-danger-600 hover:bg-neutral-500 hover:bg-opacity-10 hover:text-danger-600 focus:border-danger-600 focus:text-danger-600 focus:outline-none focus:ring-0 active:border-danger-700 active:text-danger-700 dark:hover:bg-neutral-100 dark:hover:bg-opacity-10"
                          data-te-ripple-init
                          data-te-ripple-color="light">
                        立即注册
                      </button>
                    </div>
                  </form>
                </div>
              </div>

              <div
                  flex items-center rounded-b-lg lg:rounded-r-lg lg:rounded-bl-none
                  class="lg:w-6/12"
                  style="background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593)">
                <div px-4 py-6 text-white md:mx-6 md:p-12>
                  <h4 mb-6 text-xl font-ark m:text-3xl md:text-4xl>
                    HeMing Fast
                  </h4>
                  <p text-sm font-ark>
                    鹤鸣工作室出品，一款基于 SpringBoot 3 和 Nuxt3 的脚手架。本项目基于 HeMing Fast 构建！
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>

</style>
