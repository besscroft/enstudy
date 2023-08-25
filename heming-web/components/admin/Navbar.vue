<script setup lang="ts">
import {
  Collapse,
  Dropdown,
  initTE,
} from 'tw-elements'

const router = useRouter()

onMounted(() => {
  initTE({ Collapse, Dropdown })
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
      <button
          block border-0 bg-transparent px-2 text-neutral-500 hover:no-underline hover:shadow-none focus:no-underline focus:shadow-none focus:outline-none focus:ring-0 dark:text-neutral-200 lg:hidden
          type="button"
          data-te-collapse-init
          data-te-target="#navbarSupportedContent1"
          aria-controls="navbarSupportedContent1"
          aria-expanded="false"
          aria-label="Toggle navigation">
        <!-- Hamburger icon -->
        <span class="[&>svg]:w-7">
        <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="currentColor"
            h-7 w-7
        >
          <path
              fill-rule="evenodd"
              d="M3 6.75A.75.75 0 013.75 6h16.5a.75.75 0 010 1.5H3.75A.75.75 0 013 6.75zM3 12a.75.75 0 01.75-.75h16.5a.75.75 0 010 1.5H3.75A.75.75 0 013 12zm0 5.25a.75.75 0 01.75-.75h16.5a.75.75 0 010 1.5H3.75a.75.75 0 01-.75-.75z"
              clip-rule="evenodd" />
        </svg>
      </span>
      </button>

      <!-- Collapsible navigation container -->
      <div
          class="!visible hidden flex-grow basis-[100%] items-center lg:!flex lg:basis-auto"
          id="navbarSupportedContent1"
          data-te-collapse-item>
        <!-- Logo -->
        <p font-ark @click="router.push('/')">嗯学英语</p>
        <!-- Left navigation links -->
        <ul list-style-none mr-auto flex flex-col pl-0 lg:flex-row data-te-navbar-nav-ref>
          <li mb-4 lg:mb-0 lg:pr-2 data-te-nav-item-ref>
            <a
                class="cursor-pointer text-neutral-500 transition duration-200 hover:text-neutral-700 hover:ease-in-out focus:text-neutral-700 disabled:text-black/30 motion-reduce:transition-none dark:text-neutral-200 dark:hover:text-neutral-300 dark:focus:text-neutral-300 lg:px-2 [&.active]:text-black/90 dark:[&.active]:text-zinc-400"
                data-te-nav-link-ref
                @click="router.push('/admin')"
            >控制台</a
            >
          </li>
          <li mb-4 lg:mb-0 lg:pr-2 data-te-nav-item-ref>
            <a
                class="cursor-pointer text-neutral-500 transition duration-200 hover:text-neutral-700 hover:ease-in-out focus:text-neutral-700 disabled:text-black/30 motion-reduce:transition-none dark:text-neutral-200 dark:hover:text-neutral-300 dark:focus:text-neutral-300 lg:px-2 [&.active]:text-black/90 dark:[&.active]:text-neutral-400"
                @click="router.push('/learn')"
                data-te-nav-link-ref
            >开始嗯学</a
            >
          </li>
        </ul>
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
      </div>
    </div>
  </nav>
</template>

<style scoped>

</style>