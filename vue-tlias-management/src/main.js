import { createApp } from 'vue'
// 引入Element Plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
// 引入中文
import zhCn from 'element-plus/es/locale/lang/zh-cn'
// 注册Element Plus图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 引入并使用路由
import router from 'vue-router'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.use(ElementPlus,{
    locale: zhCn,
})
app.use(router)    
app.mount('#app')