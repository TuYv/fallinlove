 <template>
  <div>
    <el-container style="border: 1px solid #eee">
    <el-header style="text-align: right; font-size: 12px">
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>查看</el-dropdown-item>
          <el-dropdown-item>新增</el-dropdown-item>
          <el-dropdown-item>删除</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>王小虎</span>
    </el-header>
    <el-container>
      <el-aside style="width=10px background-color: rgb(238, 241, 246)">
        <el-menu
          default-active="this.$route.path"
          router mode="horizontal"
      class="el-menu-vertical-demo"
           @open="handleOpen"
      @close="handleClose">
      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.title">
        <template slot="title">
          <i class="el-icon-s-platform"></i>
          <span> {{ item.name }}</span>
        </template>

      </el-menu-item>
    </el-menu>
      </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
    </el-container>
</el-container>
  </div>
</template>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
</style>
<script>
  export default {
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(20).fill(item),
        navList: [
          {title:"/about",name:"about"},
          {title:"/demo1",name:"demo1"},
          {title:"/demo2",name:"demo2"},
          {title:"/demo3",name:"demo3"},
          {title:"/demo4",name:"demo4"},
          {title:"/",name:"demo5"},
          {title:"/demo6",name:"demo6"},
          {title:"/demo7",name:"demo7"},
          {title:"/demo8",name:"demo8"},
          {title:"/demo9",name:"demo9"},
          {title:"/demo10",name:"demo10"},
          {title:"/demo11",name:"demo11"},

        ]
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log("test" + key + keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  };
</script> 
