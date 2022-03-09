import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './static/css/style.min.css'
import http from './api/config'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import "nes.css/css/nes.min.css";

Vue.use(ElementUI)
Vue.prototype.$http = http
Vue.config.productionTip = false

Vue.filter('uppercase', (value) => {
  return value.toUpperCase()
})
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
