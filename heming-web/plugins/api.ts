import { useUserStore } from '~/stores/user'
import ky from 'ky'

export default defineNuxtPlugin((nuxtApp) => {
    const app = nuxtApp.vueApp;
    const userStore = useUserStore(app.$pinia)
    const router = useRouter()

    const apiFetch = ky.extend({
        hooks: {
            beforeRequest: [
                request => {
                    request.headers.set('Authorization', 'Bearer ' + userStore.token);
                }
            ],
            beforeError: [
                error => {
                    const {response} = error;
                    console.log(response.status);
                    if (response.status === 401) {
                        router.push('/login');
                    }
                    return error;
                }
            ]
        }
    })

    app.config.globalProperties.$api = apiFetch;
    nuxtApp.provide("api", apiFetch);
});