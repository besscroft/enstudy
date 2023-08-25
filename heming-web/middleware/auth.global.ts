import { useUserStore } from '../stores/user'

export default defineNuxtRouteMiddleware((to, from) => {
    const user = useUserStore()
    // if (process.client) {
    //     console.log('user', user.token)
    // }
    if (to.path === '/login')
        return
    // if (to.path !== '/' && process.client && !user.token) {
    //     return navigateTo('/login')
    // }
})
