<template>
    <div class="login" clearfix>
        <div class="login-wrap">
            <el-row type="flow" justify="center">
              <el-col ref="loginForm" :model="user" :rules="rules" status-icon label-width="80px">
                  <h3>登陆</h3>
                  <hr>
                  <el-form-item label="用户名" prop="username">
                      <el-input v-model="user.username" placeholder="请输入用户名" prefix-icon />
                  </el-form-item>
                  <el-form-item label="密码" prop="password" id="password">
                      <el-input v-model="user.password" show-password placeholder="请输入密码" prefix-icon />
                  </el-form-item>
                  <router-link to="/">找回密码</router-link>
                  <router-link to="/register">注册账号</router-link>
              </el-col>
            </el-row>
        </div>
    </div>
</template>
<script>
export default {
    name: "login",
    date() {
        return {
            user: {
                username: "",
                password: ""
            }
        };
    },
    created() {},
    methods: {
        doLogin() {
            if(!this.user.username) {
                this.$message.error("请输入用户名!")
            }else if(!this.user.password) {
                this.$message.error("请输入密码!")
            }else {
                this.$http.get("/max/user/login", this.user).then(res => {
                    console.log(res)
                })
            }
        }
    }
}
</script>
<style scoped>
.login {
  width: 100%;
  height: 740px;
  background: url("../assets/images/bg1.png") no-repeat;
  background-size: cover;
  overflow: hidden;
}
.login-wrap {
  background: url("../assets/images/login_bg.png") no-repeat;
  background-size: cover;
  width: 400px;
  height: 300px;
  margin: 215px auto;
  overflow: hidden;
  padding-top: 10px;
  line-height: 40px;
}
#password {
  margin-bottom: 5px;
}
h3 {
  color: #0babeab8;
  font-size: 24px;
}
hr {
  background-color: #444;
  margin: 20px auto;
}
a {
  text-decoration: none;
  color: #aaa;
  font-size: 15px;
}
a:hover {
  color: coral;
}
.el-button {
  width: 80%;
  margin-left: -50px;
}
</style>