<script setup>
import { ref, onMounted } from 'vue'
import { queryAllApi, addApi,queryByIdApi,updateApi,deleteByIdApi} from '@/api/dept'
import { ElMessage,ElMessageBox } from 'element-plus'
onMounted(() => {
  search()
})
const deptList = ref([])
const search = async () => {
  const result = await queryAllApi();
  if (result.code) {
    deptList.value = result.data
  }
}

const dialogFormVisible = ref(false)
const dept = ref({ name: '' })
const formTitle = ref('')
const save = async () => {
  // 表单校验
  if (!deptFormRef.value) return;
  deptFormRef.value.validate(async(valid) => { //valid表示是否校验通过
    if (valid) {
      let result;
      if(dept.value.id){
        // 修改
        result = await updateApi(dept.value);
      }else{
        // 新增
        result = await addApi(dept.value);
      }

      if (result.code) {
        // 成功信息，关闭对话框，列表再次查询
        ElMessage({
          message: '执行成功',
          type: 'success',
        })
        dialogFormVisible.value = false
        search()
      } else {
        ElMessage.error('执行失败')
      }
    } else {
      // 表单校验未通过
      ElMessage.error('格式错误')
    }
  })

}


// 点击新增部门 - 表单
const addDept = () => {
  dialogFormVisible.value = true
  formTitle.value = 'Add Department'
  // 重置表单
  if (deptFormRef.value) {
    deptFormRef.value.resetFields()
  }
}
// 点击编辑部门 - 表单
const editDept = async (id) => {
  const result = await queryByIdApi(id)
  if(result.code){
    // 表单标题
    formTitle.value = 'Edit Department'
    // 显示表单
    dialogFormVisible.value = true;
    // 回显赋值
    dept.value = result.data;
  }
    // 重置表单
    if (deptFormRef.value) {
    deptFormRef.value.resetFields()
  }
}

// 表单校验
const rules = ref({
  name: [
    // 必填项，失去焦点开始校验，长度在2-10
    { required: true, message: 'Please input department name', trigger: 'blur' },
    { min: 2, max: 10, message: 'length should be 2-10 characters', trigger: 'blur' }
  ]
})

// 点击删除
const deleteDept = (id) => {
  // 弹出确认框
  ElMessageBox.confirm('Are you sure to delete this data?', '提示', {
    confirmButtonText: 'Confirm',
    cancelButtonText: 'Cancel',
    type: 'warning'
  }).then(async () => {
    // 删除
    const result = await deleteByIdApi(id)
    if (result.code) {
      ElMessage({
        message: 'Delete Success',
        type: 'success',
      })
      search()
    } else {
      ElMessage.error('Delete Failed')
    }
  })
}

const deptFormRef = ref()
</script>

<template>
  <h1 style="color: blue;">Department Administration</h1>
  <div class="container">
    <el-button type="primary" @click="addDept">+ Add Department</el-button>
  </div>
  <div class="container">
    <el-table :data="deptList" border style="width: 100%">
      <el-table-column type="index" width="100" label="Index" align="center" />
      <el-table-column prop="name" label="Department" width="260" align="center" />
      <el-table-column prop="updateTime" label="Update Time" width="300" align="center" />
      <el-table-column label="Operation" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" plain @click="editDept(scope.row.id)"><el-icon>
              <Edit />
            </el-icon><Edit /></el-button>
          <el-button type="danger" size="small" plain @click="deleteDept(scope.row.id)"><el-icon>
              <DeleteFilled />
            </el-icon><Delete/></el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <!-- dialog对话框 -->
  <el-dialog v-model="dialogFormVisible" :title="formTitle" width="500">
    <el-form :model="dept" :rules="rules" ref="deptFormRef">
      <el-form-item prop="name" size="small" label="Department Name" label-width="80px">
        <el-input v-model="dept.name" placeholder="Input department name" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save">Confirm</el-button>
      </div>
    </template>
  </el-dialog>

</template>

<style scoped>
.container {
  margin: 10px 0;
}
</style>
