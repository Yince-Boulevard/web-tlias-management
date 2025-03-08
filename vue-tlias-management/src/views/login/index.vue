<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { loginApi } from '@/api/login'
import { ElMessage } from 'element-plus'
const router = useRouter()
const loginForm = ref({ username: '', password: '' })
// const login = async () => {
//   // 表单校验
//   loginFormRef.value.validate(async(valid) => {
//     if (valid) {
//       const result = await loginApi(loginForm.value);
//       if (result.code) {// 成功
//         ElMessage.success('Success')
//         // 存储当前登录员工信息
//         localStorage.setItem('loginUser', JSON.stringify(result.data))
//         // 3. 跳转到首页
//         router.push('/index')
//       } else {
//         ElMessage.error(result.msg)
//       }
//     }else{
//       ElMessage.error('Format Error')
//     }
//   })
// }

const login = async () => {
  const result = await loginApi(loginForm.value);
  if(result.code){
    ElMessage.success('Success')
    localStorage.setItem('loginUser',JSON.stringify(result.data))
    router.push('/index')
  }else{
    ElMessage.error(result.msg)
  }
}

// 重置
const clear = () => {
  loginForm.value = { username: '', password: '' }
  // 表单校验也重置
  if (loginFormRef.value) {
    loginFormRef.value.resetFields()
  }
}

const loginFormRef = ref()

// 登录表单校验
const rules = {
  username: [
    { required: true, message: 'Username Required', trigger: 'blur' },
    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: 'Password Required', trigger: 'blur' },
    { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
  ]
}
</script>

<template>
  <div id="container">
    <div class="login-form">
      <el-form :rules="rules" :model="loginForm" ref="loginFormRef" label-width="80px">
        <el-row size="small" :gutter="20">
          <el-col :span="24">
            <p class="title">Tlias Intelligent Learning System</p>
          </el-col>
        </el-row>
        <el-form-item label="Username" prop="username">
          <el-input v-model="loginForm.username" placeholder="Input Username"></el-input>
        </el-form-item>

        <el-form-item label="Password" prop="password">
          <el-input type="password" v-model="loginForm.password" placeholder="Input Password"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button class="button" type="primary" @click="login">Log In</el-button>
          <el-button class="button" type="info" @click="clear">Reset</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style scoped>
#container {
  padding: 10%;
  height: 470px;
  background-image: url('../../assets/bg1.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}

.login-form {
  max-width: 500px;
  padding: 40px;
  margin: 0 auto;
  border: 1px solid #e0e0e0;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  background-color: white;
  opacity: 0.8;
}

.title {
  font-size: 30px;
  font-family: '楷体';
  text-align: center;
  margin-bottom: 30px;
  font-weight: bold;
}

.button {
  margin-top: 30px;
  width: 120px;
}
</style>