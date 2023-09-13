<script setup lang="ts">
const router = useRouter()

definePageMeta({
  layout: 'default',
  pageTransition: false,
})
</script>

<template>
  <div flex flex-col justify-center items-center h-full>
    <div font-ark text-5xl text-yellow-400>嗯学英语</div>
    <div font-ark text-slate-400>学英语，就嗯学！</div>
    <div font-ark mt-24 text-xl text-lime-400 cursor-pointer @click="router.push('/learn')">开始</div>
    <div flex space-x-2 mt-24>
      <DarkToggle />
      <ClientOnly>
        <button flex items-center justify-center w-20 h-8 of-hidden rounded-3xl border-dashed border border-gray-400>
          <a href="https://github.com/besscroft/enstudy" target="_blank" flex justify-center items-center>
            <small class="text-[#d23669]">Star</small>
            <span i-carbon-logo-github class="ml-2 !text-[22px]" />
          </a>
        </button>
      </ClientOnly>
    </div>
  </div>
</template>

<style scoped>
button {
  transition: all 0.25s;
  div {
    position: absolute;
    transition: transform 0.35s;
  }
  div:nth-child(2) {
    transform: translateY(35px);
  }
}
button:hover {
  border-style: solid;
  border-color: #a0a0a0;
  transition: all 0.25s;
}
</style>
