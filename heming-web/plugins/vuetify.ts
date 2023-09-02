import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import 'vuetify/styles'

export default defineNuxtPlugin((nuxtApp) => {
    const vuetify = createVuetify({
        ssr: true,
        icons: {
            defaultSet: 'mdi',
        },
        components,
        directives,
    })

    nuxtApp.vueApp.use(vuetify)
})