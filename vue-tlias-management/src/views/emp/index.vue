<script setup>
import { ref, watch, onMounted } from 'vue'
import { queryPageApi, addApi, queryInfoApi, updateApi, deleteApi } from '@/api/emp'
import { Male, Search, Female, Plus } from '@element-plus/icons-vue'
// 引入消息提示框
import { ElMessage,ElMessageBox } from 'element-plus'
let searchEmp = ref({
  name: '', gender: '', date: [], begin: '', end: ''
})

const token = ref('');

// clear query form
const onReset = () => {
  searchEmp.value = { name: '', gender: '', date: [], begin: '', end: '' }
  search()
}


// watch listening ---------------------------
// 监听searchEmp的date,当date数组长度为2时(发生变化)，将数组中的值分别赋值给begin和end
watch(() => searchEmp.value.date, (newVal, oldVal) => {
  if (newVal.length == 2) {
    searchEmp.value.begin = newVal[0];
    searchEmp.value.end = newVal[1];
  } else {
    searchEmp.value.begin = '';
    searchEmp.value.end = '';
  }
})
// 狗子函数
onMounted(() => {
  search() // 查询员工列表
  queryAllDepts() // 查询所有部门数据封装给下拉框
  getToken() // 获取token
})

// 获取token
const getToken = () => {
  const loginUser = JSON.parse(localStorage.getItem('loginUser'))
  if(loginUser && loginUser.token){
    token.value = loginUser.token
  }
}

// 员工列表数据
const empList = ref([])

// 分页相关的配置
const currentPage = ref(1) // 当前页码
const pageSize = ref(10) // 每页显示的条数
const total = ref(0) // 总条数
const size = ref('default')
const background = ref(true)
// const disabled = ref(false) // 禁用

// 每页展示记录数变化
const handleSizeChange = (val) => {
  console.log(`${val} items per page`)
  search()
}
// 页码变化时触发
const handleCurrentChange = (val) => {
  console.log(`current page: ${val}`)
  search()
}

// 查询
const search = async () => {
  const result = await queryPageApi(searchEmp.value.name,
    searchEmp.value.gender,
    searchEmp.value.begin,
    searchEmp.value.end,
    currentPage.value,
    pageSize.value);
  if (result.code) {
    empList.value = result.data.rows
    total.value = result.data.total
  }
}
// 表单标题和可见性
const dialogTitle = ref('')
const dialogFormVisible = ref(false)
// 点击新增员工按钮>替换表单标题>显示表单>重置表单内容和校验
const addEmp = () => {
  dialogTitle.value = 'Add New Employee'
  dialogFormVisible.value = true
  employee.value = { username: '', name: '', gender: '', phone: '', job: '', salary: '', deptId: '', entryDate: '', image: '', exprList: [] }
  // 重置表单校验
  if (empFormRef.value) {
    empFormRef.value.resetFields()
  }
}
// 员工数据模型
const employee = ref({
  username: '',
  name: '',
  gender: '',
  phone: '',
  job: '',
  salary: '',
  deptId: '',
  entryDate: '',
  image: '',
  exprList: []
})

// 职位数据字典
const jobOptions = ref([
  { value: '1', label: 'Manager' },
  { value: '2', label: 'Lecturer' },
  { value: '3', label: 'Supervisor' },
  { value: '4', label: 'Researcher' },
  { value: '5', label: 'Consultant' },
  { value: '6', label: 'Others' },
])


// 查询所有部门数据封装给下拉框
import { queryAllApi } from '@/api/dept'
const deptOptions = ref([])
const queryAllDepts = async () => {
  const result = await queryAllApi()
  if (result.code) {
    deptOptions.value = result.data
  }
}

// 头像上传成功触发>给头像赋值
const handleAvatarSuccess = (response) => {
  // 给头像赋值
  employee.value.image = response.data
}

// 头像上传之前触发>验证格式和大小
const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
    ElMessage.error('Avatar picture must be JPG/PNG format!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }
  return true
}

// 点击修改员工>替换表单标题>显示表单>重置表单
const editEmp = async (id) => {

  const result = await queryInfoApi(id);
  if (result.code) {
    employee.value = result.data
    dialogFormVisible.value = true
    dialogTitle.value = 'Edit Employee'
    if (employee.value.exprList && employee.value.exprList.length > 0) {
      employee.value.exprList.forEach((expr) => {
        expr.exprDate = [expr.begin, expr.end]
      })
    }
    search()
  }
}

const exprList = [
  { begin: '', end: '', company: '', position: '', exprDate: [] }
]

// 点击添加工作经历>employee中的exprList一个对象
const addWorkExperience = () => {
  employee.value.exprList.push({ begin: '', end: '', company: '', position: '', exprDate: [] }) // 从末尾添加一个括号里的类型
}
// 监听exprList的exprDate，封装给begin和end
watch(() => employee.value.exprList, (newVal, oldVal) => {
  if (employee.value.exprList && employee.value.exprList.length > 0) {
    employee.value.exprList.forEach((expr) => {
      expr.begin = expr.exprDate[0]
      expr.end = expr.exprDate[1]
    })
  }
}, { deep: true })

// 点击删除工作经历>employee中的exprList删除一个对象
const deleteWorkExperience = (index) => {
  employee.value.exprList.splice(index, 1) //从index位置删除1个
}

// 表单引用
const empFormRef = ref()

// 点击confirm 按钮>表单校验>保存员工>重新查询
const save = async () => {
  if (!empFormRef.value) return; // 表单校验
  empFormRef.value.validate(async (valid) => { // valid表示是否校验通过
    if (valid) {
      let result;
      if (employee.value.id) {
        result = await updateApi(employee.value); //  有id 修改
      } else { result = await addApi(employee.value); } // 没有id保存员工
      if (result.code) {
        ElMessage({
          message: '执行成功',
          type: 'success',
        })
        search()
      } else {
        ElMessage.error('执行失败')
      }
      dialogFormVisible.value = false
    } else {
      ElMessage.error('格式错误')
    }
  })
}
// 提示信息

// 表单校验规则 用户名、姓名、性别、手机号
const rules = ref({
  username: [
    { required: true, message: 'Please input username', trigger: 'blur' },
    { min: 2, max: 20, message: 'Length should be 2 to 20', trigger: 'blur' },
  ],
  name: [
    { required: true, message: 'Please input name', trigger: 'blur' },
    { min: 2, max: 20, message: 'Length should be 2 to 20', trigger: 'blur' },
  ],
  gender: [
    { required: true, message: 'Please select gender', trigger: 'change' }
  ],
  phone: [
    { required: true, message: 'Please input phone', trigger: 'blur' },
    // \d{9}: 9个数字
    // ^1: 以1开头
    // [3-9]: 3到9的一个
    // $: 以..结束
    { pattern: /^1[3-9]\d{9}$/, message: 'Please input a valid value', trigger: 'blur' },
  ]
})

// 点击删除员工
const deleteById = (id) => {
  ElMessageBox.confirm('Are you sure to delete this employee?', 'Warning', {
    confirmButtonText: 'Confirm',
    cancelButtonText: 'Cancel',
    type: 'warning',
  }).then(async () => {
    const result = await deleteApi(id)
    if (result.code) {
      ElMessage({
        message: 'Delete Success',
        type: 'success',
      })
      search()
    }else{
      ElMessage.error('Delete Failed')
    }
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: 'Delete Cancelled',
    });
  });
}

// 复选框
const selectedIds = ref([])
const handleSelectionChange = (selection) => {
  selectedIds.value = selection.map(item => item.id)
}

// 批量删除 不需要传入参数，因为是在上面定义并且通过函数改变的
const deleteByIds = () => {
  ElMessageBox.confirm('Are you sure to delete these employee?', 'Warning', {
    confirmButtonText: 'Confirm',
    cancelButtonText: 'Cancel',
    type: 'warning',
  }).then(async () => {
    if(selectedIds.value && selectedIds.value.length > 0){
      const result = await deleteApi(selectedIds.value)
      if (result.code) {
        ElMessage({
          message: 'Delete Success',
          type: 'success',
        })
        search()
      }else{
        ElMessage.error('Delete Failed')
      }
    }else{
      ElMessage.info('Please select employee')
    }
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: 'Delete Cancelled',
    });
  });
}
</script>

<template>
  <h1 style="color: blue;">Employee Administration</h1>
  <div class="container">
    <el-form :inline="true" :model="searchEmp" class="demo-form-inline">
      <el-form-item label="Name:">
        <el-input v-model="searchEmp.name" placeholder="Input" clearable />
      </el-form-item>
      <el-form-item label="Gender:">
        <el-select v-model="searchEmp.gender" placeholder="Select" clearable>
          <el-option label="Male" value="1" />
          <el-option label="Female" value="2" />
        </el-select>
      </el-form-item>
      <el-form-item label="Entry Date:">
        <el-date-picker v-model="searchEmp.date" type="daterange" range-separator="To" start-placeholder="Start"
          end-placeholder="End" :size="size" value-format="YYYY-MM-DD" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" :icon="Search" plain @click="search" />
        <el-button type="info" size="small" @click="onReset">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
  <!-- 新增员工，批量删除 -->
   {{ selectedIds }}
  <div class="container">
    <el-button type="primary" size="small" plain @click="addEmp">+ Add Employee</el-button>
    <el-button type="danger" size="small" @click="deleteByIds">- Batch Remove</el-button>
  </div>

  <!-- 数据表格 -->
  <div class="container">
    <el-table :data="empList" border style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="name" label="Name" align="center" />
      <el-table-column prop="gender" label="Gender" width="80" align="center">
        <template #default="scope">
          <el-icon v-if="scope.row.gender == 1" color="blue">
            <Male />
          </el-icon>
          <el-icon v-else color="red">
            <Female />
          </el-icon>
          <!-- {{ scope.row.gender==1 ? 'Male': 'Female' }} -->
        </template>
      </el-table-column>
      <el-table-column prop="image" label="Avtar" width="120" align="center">
        <template #default="scope">
          <el-avatar shape="circle" size="large" :src="scope.row.image" />
        </template>
      </el-table-column>
      <el-table-column prop="deptName" label="Branch" align="center" />
      <el-table-column prop="job" label="Job" width="200" align="center">
        <template #default="scope">
          <el-tag v-if="scope.row.job == 1" type="primary" effect="plain">Manager</el-tag>
          <el-tag v-else-if="scope.row.job == 2" type="primary" effect="plain">Lecturer</el-tag>
          <el-tag v-else-if="scope.row.job == 3" type="primary" effect="plain">Supervisor</el-tag>
          <el-tag v-else-if="scope.row.job == 4" type="primary" effect="plain">Researcher</el-tag>
          <el-tag v-else-if="scope.row.job == 5" type="primary" effect="plain">Consultant</el-tag>
          <el-tag v-else type="primary" effect="plain">Others</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="entryDate" label="Entry Date" width="110" align="center" />
      <el-table-column prop="updateTime" label="Update Time" width="170" align="center" />
      <el-table-column label="Operation" width="120" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" icon="Edit" @click="editEmp(scope.row.id)" plain />
          <el-button type="danger" size="small" icon="Delete" @click="deleteById(scope.row.id)" plain />
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- 分页条 -->
  <div class="container">
    <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize"
      :page-sizes="[5, 10, 20, 30, 50, 100]" :size="size" :disabled="disabled" :background="background"
      layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
      @current-change="handleCurrentChange" />
  </div>
  <!-- 新增员工 -- Dialog -- Form -->
  <el-dialog v-model="dialogFormVisible" :title="dialogTitle" width="780">
    <el-form ref="empFormRef" size="small" label-position="default" :rules="rules" :model="employee" label-width="80px">
      <!-- 基本信息 -->
      <!-- 第一行 用户名和姓名 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="Username:" prop="username">
            <el-input v-model="employee.username" placeholder="Input username (2-20 characters)" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="Name:" prop="name">
            <el-input v-model="employee.name" placeholder="Input name (2-20 characters)" />
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第二行 性别和手机号 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="Gender:" prop="gender">
            <el-radio-group v-model="employee.gender">
              <el-radio :label="1"><el-icon>
                  <Male />
                </el-icon> Man</el-radio>
              <el-radio :label="2"><el-icon>
                  <Female />
                </el-icon> Woman</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="Phone:" prop="phone">
            <el-input v-model="employee.phone" placeholder="Input telephone" />
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第三行 职位和薪资 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="Position:">
            <el-select v-model="employee.job" placeholder="Input Position" style="width: 100%;">
              <el-option v-for="job in jobOptions" :key="job.value" :label="job.label" :value="job.value" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Salary:">
            <el-input v-model="employee.salary" placeholder="Input salary"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第四行:部门和入职时间 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="Branch:">
            <el-select v-model="employee.deptId" placeholder="Input Branch" style="width: 100%;">
              <el-option v-for="item in deptOptions" :key="item.id" :label="item.name" :value="item.id" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Entry Date:" label-position="top" size="small">
            <el-date-picker v-model="employee.entryDate" type="date" placeholder="Input Entry Date"
              style="width: 100%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第五行：上传头像 -->
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="Avtor:">
            <el-upload class="avatar-uploader" action="/api/upload" :show-file-list="false"
              :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" 
              :headers="{'token': token}">
              <img v-if="employee.image" :src="employee.image" class="avatar">
              <el-icon v-else class="avatar-uploader-icon">
                <Plus />
              </el-icon>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 工作经历 -->
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="Experience:">
            <el-button type="success" icon="Plus" size="small" @click="addWorkExperience">Add Work
              Experience</el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- <el-row :gutter="12"  v-for="(expr, index) in employee.value.exprList"> -->
      <el-row :gutter="1" v-for="(expr, index) in employee.exprList">
        <el-col :span="9">
          <el-form-item size="small" label="Time:">
            <el-date-picker v-model="expr.exprDate" size="small" type="daterange" style="width: 100%;"
              range-separator="To" start-placeholder="Start" end-placeholder="End" :size="size"
              value-format="YYYY-MM-DD" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item size="small" label="Company:">
            <el-input v-model="expr.company" placeholder="Input company"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="Position">
            <el-input v-model="expr.job" placeholder="Input position"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="1">
          <el-button type="danger" icon="Delete" size="small" plain @click="deleteWorkExperience(index)"></el-button>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save">
          Confirm
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>
.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}

.container {
  margin: 10px 0;
}

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;

}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 64px;
  height: 64px;
  text-align: center;
  border: 1px solid #d9d9d9;
}
</style>