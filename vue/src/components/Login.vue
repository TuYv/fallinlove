<template>
    <div class="login" clearfix>
        <div class="login-wrap">
            <el-row type="flow" justify="center">
              <el-form ref="loginForm" :model="user" :rules="rules" status-icon label-width="80px">
                  <h3>登 录</h3>
                  <br>
                  <!-- <hr> -->
                  <el-form-item label="用户名" prop="username" >
                      <el-input v-model="user.username" placeholder="请输入用户名" maxlength="12" prefix-icon />
                  </el-form-item>
                  <el-form-item label="密码" prop="password" id="password">
                      <el-input v-model="user.password" show-password placeholder="请输入密码" prefix-icon />
                  </el-form-item>
                  <router-link to="/">找回密码</router-link>   |   
                  <router-link to="/register">注册账号</router-link>
                  <el-form-item>
                    <el-button type="primary" icon="el-icon-upload" @click="doLogin()">登 录</el-button>
                  </el-form-item>
              </el-form>
            </el-row>
        </div>
    </div>
</template>
<script>
export default {
    name: "login",
    data() {
        return {
            user: {
                username: "",
                password: ""
            },
            rules:{username:['^([A-Z]|[a-z]|[0-9]|[`-=[];,./~!@#$%^*()_+}{:?]){6,20}$']}
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
                this.$http.get("/account/user/login?userName=" + this.user.username + "&password=" + this.user.password).then(res => {
                    console.log(res)
                    if(res.returnStatus === 'SUCCEED') {
                      localStorage.setItem("user", JSON.stringify(res.data));
                      var user = localStorage.getItem("user");
                      console.log('呵呵' + user);
                       this.$router.push({ path: "/" });
                    } else{
                      this.$message.error(res.errorMessage)
                    }
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
  background: url("../assets/images/bg1.jpeg") no-repeat;
  background-size: cover;
  overflow: hidden;
}
.login-wrap {
  /* background: url("../assets/images/login_bg.jpg") no-repeat;
  background-size: cover; */
  width: 400px;
  height: 350px;
  margin: 215px auto;
  overflow: hidden;
  padding-top: 10px;
  line-height: 40px;
}
#password {
  margin-bottom: 5px;
}
h3 {
  color: #ffffffb8;
  font-size: 24px;
}
hr {
  background-color: rgb(255, 255, 255);
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