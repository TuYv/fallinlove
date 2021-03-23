

<template>
  <div>
    <h1>自定义指令</h1>
    <input type="text" ><br> 
    <input type="text" v-focus> 
    <div class="drag" v-drag>可以拖拽的div</div>
  </div>
</template>
<script>
export default {
  name: "demo11",
  data() {
    return { 
    } 
  },
  computed: {
  },
  methods: {
  },
  directives: {
    focus: {
      inserted:(el) => {
        el.focus()
      }
    },
    drag: {
      inserted:(el) =>  {
        const fnDown = (ev) => {
          console.log(ev);
          let currentX = el.offsetLeft;
          let currentY = el.offsetTop;

          let downX = ev.clientX;
          let downY = ev.clientY;

          const fnMove = (ev) => {
            let left = ev.clientX - downX + currentX;
            let top = ev.clientY - downY + currentY;

            el.style.left = left + 'px';
            el.style.top = top + 'px';
          };
          const fnUp = (ev) => {
          document.removeEventListener("mousemove", fnMove, false);
          document.removeEventListener("mouseup", fnUp, false);
          };

          document.addEventListener("mousemove", fnMove, false);
          document.addEventListener("mouseup", fnUp, false);

          return false;
        }
        el.addEventListener("mousedown", fnDown, false);
        
      }
    }
  }
}
</script>

<style lang="less" scoped>
.drag {
  position: absolute;
  width: 400px;
  height: 300px;
  background: blue;
}

</style>