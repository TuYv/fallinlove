<template>
  <div class="page-login">
    <div class="login-box" :style="{ 'margin-top': boxMarginTop }">
      <div class="box-title">
        {{ formTypeName }}
      </div>

      <!-- 登录/注册 -->
      <el-form :model="user" label-width="80px">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="user.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item v-if="formType === 'register'" label="昵称" prop="nickName">
          <el-input v-model="user.nickName" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="密码" prop="password" id="password" @keydown.enter="handleSubmit">
          <el-input v-model="user.password" show-password placeholder="请输入密码" />
        </el-form-item>
      </el-form>

      <div v-if="formType === 'login'" class="box-operate">
        <div>找回密码</div>
        <div @click="handleType('register')">注册账号</div>
      </div>
      <div v-else class="box-operate">
        <div @click="handleType('login')">已有账号？直接登录</div>
      </div>

      <div class="box-btn">
        <el-button :loading="btnLoading" type="primary" @click="handleSubmit">
          {{ formTypeName }}
        </el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { storageUtils } from '@/utils/common'
import userApi from '@/api/user'

const router = useRouter()

const user = reactive({
  userName: '',
  nickName: '',
  password: '',
})
const formType = ref('login')
const btnLoading = ref(false)
const formTypeName = computed(() => {
  return formType.value === 'login' ? '登 录' : '注 册'
})
const boxMarginTop = computed(() => {
  return formType.value === 'login' ? '220px' : '200px'
})

// 表单类型 - 登录/注册
function handleType(val) {
  formType.value = val
}

// 登录/注册
async function handleSubmit() {
  if (!user.userName) {
    return ElMessage.error('请输入用户名')
  }
  if (formType.value === 'register' && !user.nickName) {
    return ElMessage.error('请输入昵称')
  }
  if (!user.password) {
    return ElMessage.error('请输入密码')
  }

  btnLoading.value = true

  try {
    const { data } = formType.value === 'login'
        ? await userApi.login(user)
        : await userApi.register(user)
    btnLoading.value = false
    if (data.returnStatus === 'SUCCEED') {
      if (formType.value === 'login') {
        storageUtils.set('user', data.data)
        router.push('/')
      } else {
        ElMessage.success('注册成功')
        formType.value = 'login'
      }
    }
  } catch (error) {
    btnLoading.value = false
  }
}
</script>

<style lang="scss" scoped>
:deep(label) {
  margin: 0 !important;
}
:deep(.el-form-item--default .el-form-item__label) {
  line-height: 38px;
}
.el-form-item {
  margin-bottom: 35px;
}
.page-login {
  width: 100%;
  height: 100vh;
  background: url('@/assets/images/bg1.jpeg') no-repeat;
  background-size: cover;
  overflow: hidden;
  color: #fff;

  .login-box {
    width: 400px;
    margin: auto;
    .box-title {
      color: #ffffffb8;
      font-size: 24px;
      text-align: center;
      margin-bottom: 40px;
    }
    .box-operate {
      margin-top: 10px;
      display: flex;
      align-items: center;
      justify-content: center;
      color: #aaa;
      font-size: 15px;
      height: 18px;
      div {
        height: 18px;
        line-height: 18px;
        box-sizing: border-box;
        padding: 0 8px;
        cursor: pointer;
        & + div {
          border-left: 1.5px solid #333;
        }
        &:hover {
          color: #409eff !important;
        }
      }
    }

    .box-btn {
      width: 240px;
      margin: 15px auto;
      .el-button {
        width: 100%;
      }
    }
  }
}
</style>
