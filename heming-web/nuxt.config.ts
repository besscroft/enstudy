import { pwa } from './config/pwa'
import { appDescription } from './constants/index'

export default defineNuxtConfig({
  modules: [
    '@vueuse/nuxt',
    '@unocss/nuxt',
    '@pinia/nuxt',
    '@nuxtjs/color-mode',
    '@vite-pwa/nuxt',
    '@pinia-plugin-persistedstate/nuxt',
    '@nuxthq/ui',
  ],

  build: {
    transpile: ["vuetify"],
  },

  pinia: {
    autoImports: [
      'defineStore',
    ]
  },

  piniaPersistedstate: {
    storage: 'localStorage',
    debug: true,
  },

  experimental: {
    // when using generate, payload js assets included in sw precache manifest
    // but missing on offline, disabling extraction it until fixed
    payloadExtraction: false,
    inlineSSRStyles: false,
    renderJsonPayloads: true,
    typedPages: true,
  },

  css: [
    '@unocss/reset/tailwind.css',
    '@mdi/font/css/materialdesignicons.css',
  ],

  colorMode: {
    classSuffix: '',
  },

  nitro: {
    devProxy: {
      "/@api": {
        target: process.env.PROXY_URL,
        prependPath: true,
        changeOrigin: true,
        autoRewrite: true,
      },
    },
    routeRules: {
      '/@api/**': {
        proxy: process.env.PROXY_URL+'/**'
      }
    },
    esbuild: {
      options: {
        target: 'esnext',
      },
    },
    prerender: {
      crawlLinks: true,
      routes: [
        '/',
        '/login',
        '/learn',
        '/admin',
        '/admin/user',
        '/admin/word',
        '/admin/feedback',
        '/admin/dict',
      ],
    },
    preset: 'vercel'
  },

  app: {
    head: {
      viewport: 'width=device-width,initial-scale=1',
      link: [
        { rel: 'icon', href: '/favicon.ico', sizes: 'any' },
        { rel: 'icon', type: 'image/svg+xml', href: '/nuxt.svg' },
        { rel: 'apple-touch-icon', href: '/apple-touch-icon.png' },
      ],
      meta: [
        { name: 'viewport', content: 'width=device-width, initial-scale=1' },
        { name: 'description', content: appDescription },
        { name: 'apple-mobile-web-app-status-bar-style', content: 'black-translucent' },
      ],
    },
  },

  pwa,

  devtools: {
    enabled: true,
  },
})
