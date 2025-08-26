import Vue from 'vue'
//引入elementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import '@/assets/global.css'//引入样式

import '@/assets/manager.css'//引入样式

import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

//引入elementUI
Vue.use(ElementUI);

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
