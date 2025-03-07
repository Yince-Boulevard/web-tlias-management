<script setup>
import { ref, watch, onMounted } from 'vue'
import { queryPageApi } from '@/api/emp'
import { Male, Search, Female,Plus } from '@element-plus/icons-vue'
let searchEmp = ref({
  name: '', gender: '', date: [], begin: '', end: ''
})

const onSubmit = (searchForm) => {
  console.log(searchForm.name)
}
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
  search()
})
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
// 新增员工
const dialogTitle = ref('')
const dialogFormVisible = ref(false)
const addEmp = () => {
  dialogTitle.value = 'Add New Employee'
  dialogFormVisible.value = true
}
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
const jobOptions = ref([
  { value: '1', label: 'Manager' },
  { value: '2', label: 'Lecturer' },
  { value: '3', label: 'Supervisor' },
  { value: '4', label: 'Researcher' },
  { value: '5', label: 'Consultant' },
  { value: '6', label: 'Others' },
])

const deptOptions = ref([
  { value: '1', label: 'Development' },
  { value: '2', label: 'Marketing' },
  { value: '3', label: 'Sales' },
  { value: '4', label: 'Finance' },
  { value: '5', label: 'HR' },
  { value: '6', label: 'Others' },
])
// 修改员工
const editEmp = () => {
  dialogTitle.value = 'Modify An Employee'
  dialogFormVisible.value = true
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
  <div class="container">
    <el-button type="primary" size="small" plain @click="addEmp">+ Add Employee</el-button>
    <el-button type="danger" size="small" @click="onReset">- Batch Remove</el-button>
  </div>

  <!-- 数据表格 -->
  <div class="container">
    <el-table :data="empList" border style="width: 100%">
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
          <el-button type="primary" size="small" icon="Edit" @click="editEmp" plain />
          <el-button type="danger" size="small" icon="Delete" plain />
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- 分页条 -->
  <div class="container">
    <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[5, 10, 20, 30, 50, 100]"
      :size="size" :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper"
      :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
  </div>

  <!-- 新增员工 -- Dialog -- Form -->
  <el-dialog v-model="dialogFormVisible" :title="dialogTitle" width="800">
    <el-form :model="employee" label-width="80px">
      <!-- 基本信息 -->
       <!-- 第一行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="Username:">
              <el-input v-model="employee.username" placeholder="Input username (2-20 characters)"/>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="Name:">
              <el-input v-model="employee.name" placeholder="Input name (2-10 characters)"/>
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 第二行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="Gender:">
              <el-radio-group v-model="employee.gender">
                <el-radio :label="1"><el-icon><Male /></el-icon> Man</el-radio>
                <el-radio :label="2"><el-icon><Female /></el-icon> Woman</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="Phone:">
              <el-input v-model="employee.phone" placeholder="Input telephone" />
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 第三行 -->
         <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="Position:">
              <el-select v-model="employee.job" placeholder="Input Position" style="width: 100%;">
                <el-option v-for="item in jobOptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Salary:">
              <el-input v-model="employee.salary" placeholder="Input salary"></el-input>
            </el-form-item>
          </el-col>
         </el-row>
         <!-- 第四行 -->
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="Branch:">
                <el-select v-model="employee.deptId" placeholder="Input Branch" style="width: 100%;">
                  <el-option v-for="item in deptOptions" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="Entry Date:">
                <el-date-picker v-model="employee.entryDate" type="date" placeholder="Input Entry Date" style="width: 100%;" />
              </el-form-item>
            </el-col>
          </el-row>
          <!-- 第五行 -->
           <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="Avtor">
                <el-input type="file" v-model="employee.image" placeholder="Upload avtar"></el-input>
              </el-form-item>
            </el-col>
           </el-row>
           <!-- 工作经历 -->
            <el-row :gutter="20">
              <el-col :span="24">
                <el-form-item label="Experience:">
                  <el-button type="primary" icon="Plus" size="small" color="green" @click="addWorkExperience">Add Work Experience</el-button>
                </el-form-item>
              </el-col>
            </el-row>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">
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
</style>