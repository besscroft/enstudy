import { acceptHMRUpdate, defineStore } from 'pinia'
import type { UserStore } from '../types/user'

export const useUserStore = defineStore('user', {
  state: (): UserStore => ({
    // token
    token: '',
    // token 前缀
    tokenName: '',
    // 用户名称
    userName: '',
    // 头像
    avatar: '',
    // i18n 语言
    language: '',
    // 暗黑模式
    theme: '',
    // 网站标题
    title: '',
    // 备案信息
    beian: '',
    // 角色 Code
    roleCode: '',
  }),
  actions: {
    /** 设置用户名称 */
    setUserName(name: string) {
      this.userName = name
    },
    /** 设置 token */
    setToken(token: string) {
      this.token = token
    },
    /** 设置 token 前缀 */
    setTokenName(tokenName: string) {
      this.tokenName = tokenName
    },
    /** 设置头像 */
    setAvatar(avatar: string) {
      this.avatar = avatar
    },
    /** 设置 i18n 语言 */
    setLanguage(language: string) {
      this.language = language
    },
    /** 设置暗黑模式 */
    setTheme(theme: string) {
      this.theme = theme
    },
    /** 设置网站标题 */
    setTitle(title: string) {
      this.title = title
    },
    /** 设置备案信息 */
    setBeian(beian: string) {
      this.beian = beian
    },
    /** 设置角色 Code */
    setRoleCode(roleCode: string) {
      this.roleCode = roleCode
    },
  },
})

if (import.meta.hot)
  import.meta.hot.accept(acceptHMRUpdate(useUserStore, import.meta.hot))
