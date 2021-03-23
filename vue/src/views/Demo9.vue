

<template>
  <div class="demo">
    <h1>案例</h1>
    <div style="text-align: left; margin-bottom: 10px;">
      <input class="btn"
              type="button"
              value="新增用户"
              @click="addUser">  
    </div>
    <table class="table">
      <tr>
        <th>姓名</th>
        <th>外号</th>
        <th>技能</th>
        <th>操作</th>
      </tr>
      <tr v-for="(user, index) of userList" :key="user.id" >
        <td>{{user.name}}</td>
        <td>{{user.nickName}}</td>
        <td>
          <span v-for="(skill, skillIndex) in user.skill" :key="skillIndex">{{skill}}</span>
        </td>
        <td>
          <a href="javascript:;" @click="editUser(user,index )">编辑</a> ｜ 
          <a href="javascript:;" @click="deleteUser(index)">删除</a>
        </td>
      </tr>
    </table>
    <div>
      <form v-show="isShow">
        <label>
          <span>姓名:</span>
          <input type="text" v-model="modelData.name">
        </label>
        <label>
          <span>外号:</span>
          <input type="text" v-model="modelData.nickName">
        </label>
        <label>
          <span>技能:</span>
          <textarea v-model="skill"
                    placeholder="多个技能之间用回车隔开"
                    rows="4" />
        </label>
        <div>
          <input type="button" value="提交" @click="submit()">
          <input type="button" value="取消" @click="cancel">
        </div>
      </form>
    </div>
  </div>
</template>
<script>
export default {
  name: "demo9",
  data() {
    return { 
      isShow: false,
      usedIndex: -1,
      userList: [
        {
          id: 1,
          name: "黄药师",
          nickName: "东邪、黄老邪",
          skill: ["弹指神通","碧海潮生曲"]
        },{
          id: 2,
          name: "欧阳锋",
          nickName: "西毒、老毒物",
          skill: ["蛤蟆功","灵蛇杖法"]
        },{
          id: 3,
          name: "段智兴",
          nickName: "南帝、一灯大师",
          skill: ["一阳指","先天功"]
        },{
          id: 4,
          name: "洪七公",
          nickName: "北丐、老叫花",
          skill: ["降龙十八掌","打狗棒法"]
        },
      ],
      modelData: {}
    }
  },
  computed: {
    skill: {
      set(val) {
        let skill = val.split("\n")
        this.modelData = {
          ...this.modelData,
          skill
        }
      },
      get() {
        return this.modelData.skill
        ? this.modelData.skill.join("\n")
        : ""
      }
    }
  },
  methods: {
    addUser() {
      this.usedIndex = -1
      this.modelData = {}
      this.isShow = true
    },
    editUser(user,index) {
      this.isShow = true
      this.modelData = {
        ...user
      }
      this.usedIndex = index
       
    },
    deleteUser(index) {
      if(window.confirm("是否确定删除？")) {
        this.userList.splice(index, 1)
      }
      
       
    },
    submit() {
      if(this.userIndex) {
        this.userList.splice(this.usedIndex, 1, this.modelData)
      } else {
      this.modelData = {
        ...this.modelData,
        id: Date.now()
      }
      this.userList.push(this.modelData)
      }
      this.cancel()
    },
    cancel() {
      this.usedIndex = -1
      this.modelData = {}
      this.isShow = false
    }
  }
}
</script>

<style>

</style>