import { createRouter, createMemoryHistory } from 'vue-router'
import IndexView from '@/views/index/index.vue'
import ClazzView from '@/views/clazz/index.vue'
import DeptView from '@/views/dept/index.vue'
import EmpView from '@/views/emp/index.vue'
import StuView from '@/views/stu/index.vue'
import LoginView from '@/views/login/index.vue'
import StuReportView from '@/views/report/stu/index.vue'
import EmpReportView from '@/views/report/emp/index.vue'
import LayoutView from '@/views/layout/index.vue'
import LogView from '@/views/log/index.vue'

const router = createRouter({
    history: createMemoryHistory(import.meta.env.BASE_URL),
    routes: [
        {path: '/index',name: 'index',component: IndexView},
        {path: '/clazz',name: 'clazz',component: ClazzView},
        {path: '/dept',name: 'dept',component: DeptView},
        {path: '/emp',name: 'emp',component: EmpView},
        {path: '/stu',name: 'stu',component: StuView},
        {path: '/login',name: 'login',component: LoginView},
        {path: '/report/stu',name: 'stuReport',component: StuReportView},
        {path: '/report/emp',name: 'empReport',component: EmpReportView},
        {path: '/stu',name: 'stu',component: StuView},
        {path: '/log',name: 'log',component: LogView},
        {path: '/',name: 'layout',component: LayoutView}
    ]
})

export default router