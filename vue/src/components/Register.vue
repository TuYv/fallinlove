<template>

  <div class="login clearfix">
    <div class="login-wrap">
      <el-row type="flex" justify="center">
        <el-form ref="loginForm" :model="user" status-icon label-width="80px">
          <h3>注  册</h3>
          <br>
          <el-form-item prop="username" label="用户名">
            <el-input v-model="user.userName" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item prop="nickName" label="昵称">
            <el-input v-model="user.nickName" placeholder="请输入昵称"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="设置密码">
            <el-input v-model="user.password" show-password placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon @click="doRegister()">注册账号</el-button>
          </el-form-item>
        </el-form>
      </el-row>
    </div>
  </div>
</template>
<script>
export default {
    name:"register",
    data() {
        return {
            user: {
                userName:"",
                password:"",
                nickName:""
            }
        }
    },
    methods: {
    doRegister() {
      if (!this.user.userName) {
        this.$message.error("请输入用户名！");
        return;
      } else if (!this.user.nickName) {
        this.$message.error("请输入昵称！");
        return;
      } else if (!this.user.password) {
          this.$message.error("请输入密码！");
        } else {
            this.$http.post("/max/user/register",this.user).then(res => { 
              if (res.returnStatus === 'SUCCEED') {
                this.$router.push({ path: "/login" });
              } else {
                alert(res.errorMessage);
              }
            });
        }
      }
    }

}
</script>
<style scoped>
.login {
  width: 100%;
  height: 740px;
  background: url("../assets/images/bg1.jpeg") no-repeat;
  background-size: cover;
  overflow: hidden;
}
.login-wrap {
  /* background: url("../assets/images/login_bg.png") no-repeat;
  background-size: cover; */
  width: 400px;
  height: 350px;
  margin: 215px auto;
  overflow: hidden;
  padding-top: 10px;
  line-height: 20px;
}
 
h3 {
  color: #0babeab8;
  font-size: 24px;
}
hr {
  background-color: #444;
  margin: 20px auto;
}
 
.el-button {
  width: 80%;
  margin-left: -50px;
}

</style>