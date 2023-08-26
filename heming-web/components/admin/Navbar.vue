<script setup lang="ts">
import {
  Collapse,
  Dropdown,
  Ripple,
  initTE,
} from 'tw-elements'

const router = useRouter()

onMounted(() => {
  initTE({ Collapse, Dropdown, Ripple })
})
</script>

<template>
  <!-- Main navigation container -->
  <nav
      flex-no-wrap relative flex w-full items-center justify-between py-2 shadow-md lg:flex-wrap lg:justify-start lg:py-4 dark:bg-neutral-600
      class="bg-[#FBFBFB] shadow-black/5 dark:shadow-black/10"
      data-te-navbar-ref>
    <div class="flex w-full flex-wrap items-center justify-between px-3">
      <!-- Hamburger button for mobile view -->

      <!-- Collapsible navigation container -->
      <div
          class="flex items-center">
        <button
            type="button"
            data-te-ripple-init
            data-te-ripple-color="light"
            data-te-sidenav-toggle-ref
            data-te-target="#sidenav-5"
            aria-controls="#sidenav-5"
            aria-haspopup="true"
            class="inline-block rounded-full bg-primary p-1 uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M10.5 6h9.75M10.5 6a1.5 1.5 0 11-3 0m3 0a1.5 1.5 0 10-3 0M3.75 6H7.5m3 12h9.75m-9.75 0a1.5 1.5 0 01-3 0m3 0a1.5 1.5 0 00-3 0m-3.75 0H7.5m9-6h3.75m-3.75 0a1.5 1.5 0 01-3 0m3 0a1.5 1.5 0 00-3 0m-9.75 0h9.75" />
          </svg>
        </button>
        <p ml-4 cursor-pointer font-ark @click="router.push('/learn')">嗯学英语</p>
      </div>

      <!-- Right elements -->
      <div relative flex items-center>

        <!-- Container with two dropdown menus -->
        <div relative data-te-dropdown-ref>
          <!-- First dropdown trigger -->
          <a
              class="hidden-arrow mr-4 flex items-center text-neutral-600 transition duration-200 hover:text-neutral-700 hover:ease-in-out focus:text-neutral-700 disabled:text-black/30 motion-reduce:transition-none dark:text-neutral-200 dark:hover:text-neutral-300 dark:focus:text-neutral-300 [&.active]:text-black/90 dark:[&.active]:text-neutral-400"
              id="dropdownMenuButton1"
              role="button"
              data-te-dropdown-toggle-ref
              aria-expanded="false">
            <!-- Dropdown trigger icon -->
            <span class="[&>svg]:w-5">
            <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                fill="currentColor"
                h-5 w-5>
              <path
                  fill-rule="evenodd"
                  d="M5.25 9a6.75 6.75 0 0113.5 0v.75c0 2.123.8 4.057 2.118 5.52a.75.75 0 01-.297 1.206c-1.544.57-3.16.99-4.831 1.243a3.75 3.75 0 11-7.48 0 24.585 24.585 0 01-4.831-1.244.75.75 0 01-.298-1.205A8.217 8.217 0 005.25 9.75V9zm4.502 8.9a2.25 2.25 0 104.496 0 25.057 25.057 0 01-4.496 0z"
                  clip-rule="evenodd" />
            </svg>
          </span>
            <!-- Notification counter -->
            <span
                class="absolute -mt-4 ml-2.5 rounded-full bg-danger px-[0.35em] py-[0.15em] text-[0.6rem] font-bold leading-none text-white"
            >3</span
            >
          </a>
          <!-- First dropdown menu -->
          <ul
              class="absolute left-auto right-0 z-[1000] float-left m-0 mt-1 hidden min-w-max list-none overflow-hidden rounded-lg border-none bg-white bg-clip-padding text-left text-base shadow-lg dark:bg-neutral-700 [&[data-te-dropdown-show]]:block"
              aria-labelledby="dropdownMenuButton1"
              data-te-dropdown-menu-ref>
            <!-- First dropdown menu items -->
            <li v-for="(index) in 3" :key="index">
              <a
                  class="block w-full whitespace-nowrap bg-transparent px-4 py-2 text-sm font-normal text-neutral-700 hover:bg-neutral-100 active:text-neutral-800 active:no-underline disabled:pointer-events-none disabled:bg-transparent disabled:text-neutral-400 dark:text-neutral-200 dark:hover:bg-white/30"
                  href="#"
                  data-te-dropdown-item-ref
              >Action {{ index }}</a>
            </li>
          </ul>
        </div>

        <!-- Second dropdown container -->
        <div relative data-te-dropdown-ref>
          <!-- Second dropdown trigger -->
          <a
              hidden-arrow flex items-center whitespace-nowrap transition duration-150 ease-in-out motion-reduce:transition-none
              id="dropdownMenuButton2"
              role="button"
              data-te-dropdown-toggle-ref
              aria-expanded="false">
            <!-- User avatar -->
            <img
                src="https://besscroft.com/uploads/avatar.jpeg"
                rounded-full
                style="height: 25px; width: 25px"
                alt="avatar"
                loading="lazy" />
          </a>
          <!-- Second dropdown menu -->
          <ul
              class="absolute left-auto right-0 z-[1000] float-left m-0 mt-1 hidden min-w-max list-none overflow-hidden rounded-lg border-none bg-white bg-clip-padding text-left text-base shadow-lg dark:bg-neutral-700 [&[data-te-dropdown-show]]:block"
              aria-labelledby="dropdownMenuButton2"
              data-te-dropdown-menu-ref>
            <!-- Second dropdown menu items -->
            <li v-for="(index) in 3" :key="index">
              <a
                  class="block w-full whitespace-nowrap bg-transparent px-4 py-2 text-sm font-normal text-neutral-700 hover:bg-neutral-100 active:text-neutral-800 active:no-underline disabled:pointer-events-none disabled:bg-transparent disabled:text-neutral-400 dark:text-neutral-200 dark:hover:bg-white/30"
                  href="#"
                  data-te-dropdown-item-ref
              >Action {{ index }}</a>
            </li>
          </ul>
        </div>

        <DarkToggle ml-2 />
      </div>
    </div>
  </nav>
</template>

<style scoped>

</style>