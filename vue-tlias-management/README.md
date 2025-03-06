# Day 01 - 2025-3-6 19:02:12
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