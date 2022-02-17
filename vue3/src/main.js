import { createApp } from 'vue'
import router from './router'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as Icons from '@element-plus/icons-vue'
import 'normalize.css'
import './assets/styles/style.min.scss'

const app = createApp(App)
app .use(router).use(ElementPlus).mount('#app')

for (const iconName in Icons) {
  if (Reflect.has(Icons, iconName)) {
    const item = Icons[iconName]
    app.component(iconName, item)
  }
}
