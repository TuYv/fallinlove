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