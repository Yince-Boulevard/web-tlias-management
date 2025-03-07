<script setup>

</script>

<template>
  <div class="common-layout">
    <el-container>
      <!-- Header 区域 -->
      <el-header class="header">
        <span class="title">Tlias Intelligent Learning System</span>
        <span class="right_tool">
          <a href="">
            <el-icon>
              <EditPen />
            </el-icon> Update Password &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
          </a>
          <a href="">
            <el-icon>
              <SwitchButton />
            </el-icon> Log out
          </a>
        </span>
      </el-header>

      <el-container>
        <!-- 左侧菜单 -->
        <el-aside width="200px" class="aside">
          <el-menu router>
            <el-menu-item index="/index"><el-icon><Promotion /></el-icon>Home Page</el-menu-item>
            <el-sub-menu index="/clazz">
              <template #title>
                <el-icon><Menu /></el-icon>Stu & Class Manage
              </template>
              <el-menu-item index="/clazz"><el-icon><HomeFilled /></el-icon>Class</el-menu-item>
              <el-menu-item index="/stu"><el-icon><UserFilled /></el-icon>Student</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="/manage">
              <template #title>
                <el-icon><Tools /></el-icon>System Manage
              </template>
              <el-menu-item index="/dept"><el-icon><HelpFilled /></el-icon>Department</el-menu-item>
              <el-menu-item index="/emp"><el-icon><Avatar /></el-icon>Employee</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="/report">
              <template #title>
                <el-icon><Histogram /></el-icon>Data Statistics
              </template>
              <el-menu-item index="/empReport"><el-icon><InfoFilled /></el-icon>Emp Info</el-menu-item>
              <el-menu-item index="/stuReport"><el-icon><Share /></el-icon>Stu Info</el-menu-item>
              <el-menu-item index="/log"><el-icon><Document /></el-icon>Operate Log</el-menu-item>
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

<style scoped>
.header {
  background-image: linear-gradient(to right, #00547d, #007fa4, #00aaa0, #00d072, #a8eb12);
}

.title {
  color: white;
  font-size: 40px;
  font-family: 楷体;
  line-height: 60px;
  font-weight: bolder;
}

.right_tool {
  float: right;
  line-height: 60px;
}

a {
  color: white;
  text-decoration: none;
}

.aside {
  width: 220px;
  border-right: 1px solid #ccc;
  height: 730px;
}
</style>
