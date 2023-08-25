<script setup lang="ts">
const router = useRouter()
</script>

<template>
  <div flex flex-col justify-center items-center h-full>
    <div font-ark text-5xl text-yellow-400>嗯学英语</div>
    <div font-ark text-slate-400>学英语，就嗯学！</div>
    <div font-ark mt-36 text-xl text-lime-400 cursor-pointer @click="router.push('/learn')">开始</div>
  </div>
</template>
