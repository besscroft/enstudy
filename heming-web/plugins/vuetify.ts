import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import 'vuetify/styles'
import { md3 } from 'vuetify/blueprints'

const theme: VuetifyOptions['theme'] = {
    defaultTheme: 'light',
    themes: {
        light: {
            dark: false,
            colors: {
                'primary': '#6750A4',
                'secondary': '#625B71',
                'on-secondary': '#FFFFFF',
                'success': '#56CA00',
                'info': '#16B1FF',
                'warning': '#FFB400',
                'error': '#FF4C51',
                'on-primary': '#FFFFFF',
                'on-success': '#FFFFFF',
                'on-warning': '#FFFFFF',
                'background': '#F4F5FA',
                'on-background': '#3A3541',
                'on-surface': '#3A3541',
                'grey-50': '#FAFAFA',
                'grey-100': '#F0F2F8',
                'grey-200': '#EEEEEE',
                'grey-300': '#E0E0E0',
                'grey-400': '#BDBDBD',
                'grey-500': '#9E9E9E',
                'grey-600': '#757575',
                'grey-700': '#616161',
                'grey-800': '#424242',
                'grey-900': '#212121',
                'perfect-scrollbar-thumb': '#DBDADE',
                'skin-bordered-background': '#fff',
                'skin-bordered-surface': '#fff',
            },
        },
        dark: {
            dark: true,
            colors: {
                'primary': '#D0BCFF',
                'secondary': '#CCC2DC',
                'on-secondary': '#332D41',
                'success': '#56CA00',
                'info': '#16B1FF',
                'warning': '#FFB400',
                'error': '#FF4C51',
                'on-primary': '#FFFFFF',
                'on-success': '#FFFFFF',
                'on-warning': '#FFFFFF',
                'background': '#28243D',
                'on-background': '#E7E3FC',
                'surface': '#312D4B',
                'on-surface': '#E7E3FC',
                'grey-50': '#2A2E42',
                'grey-100': '#474360',
                'grey-200': '#4A5072',
                'grey-300': '#5E6692',
                'grey-400': '#7983BB',
                'grey-500': '#8692D0',
                'grey-600': '#AAB3DE',
                'grey-700': '#B6BEE3',
                'grey-800': '#CFD3EC',
                'grey-900': '#E7E9F6',
                'perfect-scrollbar-thumb': '#4A5072',
                'skin-bordered-background': '#312d4b',
                'skin-bordered-surface': '#312d4b',
            },
        },
    },
}

export default defineNuxtPlugin((nuxtApp) => {
    const vuetify = createVuetify({
        ssr: true,
        icons: {
            defaultSet: 'mdi',
        },
        blueprint: md3,
        components,
        directives,
        theme,
    })

    nuxtApp.vueApp.use(vuetify)
})