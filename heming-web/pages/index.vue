<script setup lang="ts">
const router = useRouter()

definePageMeta({
  layout: 'layout',
})
</script>

<template>
  <div flex flex-col justify-center items-center h-full>
    <div font-ark text-5xl text-yellow-400>嗯学英语</div>
    <div font-ark text-slate-400>学英语，就嗯学！</div>
    <div flex flex-col space-x-2 mt-24>
      <div class="chat chat-start">
        <div class="chat-image avatar">
          <div class="w-10 rounded-full">
            <img src="https://besscroft.com/uploads/avatar.jpeg" />
          </div>
        </div>
        <div class="chat-header">
          云淑
          <time class="text-xs opacity-50">12:45</time>
        </div>
        <div class="chat-bubble">You were the Chosen One!</div>
        <div class="chat-footer opacity-50">
          已读
        </div>
      </div>
      <div class="chat chat-end">
        <div class="chat-image avatar">
          <div class="w-10 rounded-full">
            <img src="https://img-static.mihoyo.com/communityweb/avatar/avatar10020.png" />
          </div>
        </div>
        <div class="chat-header">
          爱学习的史莱姆
          <time class="text-xs opacity-50">12:46</time>
        </div>
        <div class="chat-bubble">I can do it!</div>
        <div class="chat-footer opacity-50">
          发送于 12:46
        </div>
      </div>
    </div>
    <div font-ark mt-24 text-xl text-lime-400 cursor-pointer @click="router.push('/learn')">开始</div>
    <div flex space-x-2 mt-24>
      <DarkToggle />
      <a i-carbon-logo-github href="https://github.com/besscroft" target="_blank" />
    </div>
  </div>
</template>
