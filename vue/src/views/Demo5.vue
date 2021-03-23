<template>
  <div>
    <h1>v-on 事件绑定</h1>
    <div>{{number}}</div>
    <button v-on:click="add" >加1</button>
    <button @click="reduce" >减1</button>
    <button @click="reduce(2)" >减2</button>

    <hr>

    <div v-if="isShow">isShow为true</div>
    <div v-else>isShow为false</div>
    <button @click="toggle">切换isShow</button>

    <hr>

    <ul>
      <li v-for="(item) in Student" :key="item.id">{{item}}</li>
    </ul>
    <button @click="addStu">添加学生</button>
    <button @click="removeStu">删除学生</button>
    <button @click="editStu">修改学生</button>

    <hr>

    <div v-for="(v, k, i) in obj" :key="k">
      {{i}} - {{k}} - {{v}}
    </div>
     
    <button @click="addAttr">添加属性</button>
    <button @click="removeAttr">删除属性</button>

  </div>
</template>
<script>
export default {
  name: "demo5",
  data() {
    return {
      number: 1,
      isShow: true,
      Student: [
        {
          id: 1,
          name: 'Apill'
        },
        {
          id: 2,
          name: 'Bob'
        },
        {
          id: 3,
          name: 'Clack'
        },
        {
          id: 4,
          name: 'David'
        },
        {
          id: 5,
          name: 'Eirc'
        },
      ],
      obj: {
        name: "Musk",
        age: 20,
        sex: "男"
      }
    }
  },
  methods: {
    add () {
      this.number++;
      this.$http.get("test/test")
    },
    reduce(val) {
      if(typeof val === 'number') {
        this.number-= val
      } else {
        this.number--
      }
    },
    toggle() {
      this.isShow = !this.isShow
    },
    addStu() {
      //shift 从列表头添加   push 从列表尾添加
      this.Student.push({
        id: this.Student.length+1,
        title: "Musk"
      })
    },
    removeStu() {
      //同上 unshift/pop
      this.Student.pop()
    },
    editStu() {
      //  vue 不能直接替换修改列表里面的对象
      // this.Student[0] = {
      //   id: Date.now(),
      //   name: "Musk"
      // }

      //可以这样实现 @set(要替换的数组，替换的对象下标， 替换的对象)
      // this.$set(this.Student, 0, {
      //   id: Date.now(),
      //   name: "Musk"
      // })

      // 也可以这样  splice(替换的下标，替换的数量， 替换的对象)
      this.Student.splice(0, 2, {
        id: Date.now(),
        name: "Musk"
      })
    },
    addAttr() {
      // 也不行
      //this.obj.hobby= "twitter"

      this.$set(this.obj, 'hobby', 'twitter')
      this.obj = {
        ...this.obj,
        hobby: 'twitter'
      }
    },
    removeAttr()  {
      let _obj = {...this.obj};
      delete _obj.age;
      this.obj = {..._obj}
    }
  }
}
</script>

<style>

</style>