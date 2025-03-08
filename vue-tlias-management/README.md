# Day 01 = 2025-3-6 19:02:12
## 两种不同的API风格
### 1. 选项式API
```html
export default {
    data(){
        retrun {
            count: 0
        }
    },
    methods: {
        increment: function() {
            this.count++;
        }
    },
    mounted() {
        console.log('Vue mounted ...');
    }
}
```
### 2. 组合式API
```html
// 引入 ref 函数
import {onMounted, ref} from 'vue'

// 声明响应式数据
const count = ref(0);
function increment() {
    count.value++;
}

// 输出响应式数据
onMounted(()=>{
    console.log("count:",count.value)
})
```
### 组合式API
* 要标识`<script setup>`
* 没有选项式API的this对象，constant.value才是响应式数据的值
## 案例 运用axios异步请求
```markdown
import { ref,onMounted } from 'vue';
import axios from 'axios';
const name = ref('');
const gender = ref('');
const job = ref('');
const userList = ref([]);

// 声明函数 发送axios异步请求, 请求服务器端加载数据
const search = async ()=>{
    const result = await axios.get(`https://web-server.itheima.net/emps/list?name=${name.value}&gender=${gender.value}&job=${job.value}`)
    userList.value = result.data.data;
}

// 页面加载时, 自动调用search函数
onMounted(()=>{
    search();
})
```
## Element-Plus 入门
1. 创建vue项目
2. npm install element-plus@2.4.4 --save
3. 在main.js引入样式文件
```JavaScript
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'

const app = createApp(App)


app.use(ElementPlus)    
app.mount('#app')
```
### Element-Plus 常见组件
* 表格组件   (数据显示)
* 分页条组件 ()
* 对话框组件 (定制性更大的场景)
* 表单组件
使用时阅读官方文档，包含组件的属性，数据，布局等

# Day 02 = 2025-3-7 16:32:25
## 整体页面布局
使用Element-Plus的Container布局
## 下拉菜单
使用Element-Plus的Menu组件

## 完成utils,api的封装
### 提取异步请求相关操作
```js
import axios from 'axios'

const request = axios.create({
  baseURL: '/api',
  timeout: 6000,
})

request.interceptors.response.use(
    (response) => {
        return response.data
    }, 
    (error) => {
    return Promise.reject(error)
})

export default request
```

### api.js,提取与前后端数据操作相关操作
```js
import request from "@/utils/request";

// 查询所有部门
export const queryAllApi = () => request.get('/depts');

// 新增部门

// 修改部门

// 删除部门
```

## vite.config.js配置
```js
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      '/api': {
        // target: 'https://apifoxmock.com/m1/5983197-5671563-default',
        target: 'http://localhost:8080',
        secure: false,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
将/api替换为target
```



## 部门管理相关
-- Table
-- Button
-- icon
-- Dialog
-- Elmessage
## 员工管理相关
-- 搜索表单
-- 数据表格
-- 分页条
-- 新增员工对话框（含表单）
-- 员工编辑

### Layout 布局
通过 `row` 和 `col` 组件，自由地组合布局。
每行24各，通过col中的span属性设置列的宽度
### watch监听

# Day 03 = 2025-3-8 20:19:35
## 新增员工
## 编辑员工
## 删除员工
## 批量删除员工
## 表单校验
## 登录页面
## 携带令牌登录
```js
// 添加请求拦截器 - 获取请求头中的token,在请求头中增加token
request.interceptors.request.use(
    (config) => { // 成功回调
        const loginUser = JSON.parse(localStorage.getItem('loginUser'))
        if (loginUser && loginUser.token) {
            config.headers.token = loginUser.token
        }
        return config
    }, 
    (error) => {
    return Promise.reject(error)
})
```

## 打包部署
点击 npm脚本 build，会生成dist文件夹，里面有index.html，以及静态资源。
部署到nginx服务器
其中
```markdown
conf 是nginx配置文件
html 是nginx静态资源文件
logs 是nginx日志文件
temp 是nginx临时文件
nginx 是nginx程序
```

在nginx.conf配置文件中添加如下代码
```config
location / {
    location ^~ /api/ {
        rewrite ^/api/(.*)$ /$1 break;
        proxy_pass http://localhost:8080;
    }
}
```
常用命令
```cmd
nginx.exe -s reload //重新加载配置文件
nginx.exe -s stop //停止nginx 
```