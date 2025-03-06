<script setup>
// 导入router
import { useRouter } from '@/router/index'
import { ref } from 'vue'
route = useRouter()
useRouter()

</script>

<template>
    <div class="common-layout">
        <el-container>
            <!-- Header 区域 -->
            <el-header>Tlias智能学习辅助系统</el-header>
            <el-container>
                <!-- 左侧区域 -->
                <el-aside width="200px" class="aside">
                    <el-menu router :default-openeds="[]">
                        <!-- 首页菜单 -->
                        <el-menu-item index="/index">
                            <el-icon>
                                <Promotion />
                            </el-icon>首页
                        </el-menu-item>

                        <el-sub-menu router index="/manage">
                            <template #title>
                                <el-icon>
                                    <Menu />
                                </el-icon>班级学员管理
                            </template>
                            <el-menu-item router index="/clazz"><el-icon>
                                    <HomeFilled />
                                </el-icon>班级管理</el-menu-item>
                            <el-menu-item router index="/stu"><el-icon>
                                    <UserFilled />
                                </el-icon>学员管理</el-menu-item>
                        </el-sub-menu>
                        <el-sub-menu index="/manage">
                            <template #title>
                                <el-icon>
                                    <Tools />
                                </el-icon>系统信息管理
                            </template>
                            <el-menu-item router index="/dept"><el-icon>
                                    <HelpFilled />
                                </el-icon>部门管理</el-menu-item>
                            <el-menu-item router index="/emp"><el-icon>
                                    <Avatar />
                                </el-icon>员工管理</el-menu-item>
                        </el-sub-menu>
                        <el-sub-menu index="/report">
                            <template #title>
                                <el-icon>
                                    <Histogram />
                                </el-icon>数据统计管理
                            </template>
                            <el-menu-item router index="1-1">员工信息统计</el-menu-item>
                            <el-menu-item router index="1-2">学员信息统计</el-menu-item>
                        </el-sub-menu>
                    </el-menu>
                </el-aside>

                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<style scoped></style>