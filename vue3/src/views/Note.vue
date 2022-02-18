<template>
  <div class="container grid-sm s-content header">
    <div class="column col-12">
      <h1 class="logo" style="text-align: right">
        <i class="ico i-love"></i>
        <a href="/">Fall in Love</a>
      </h1>
      <p class="description" style="text-align: left">
        这里记录了 <a href="#" target="_blank">{{ lovers.boy }}</a> 和
        <a href="#" target="_blank">{{ lovers.girl }}</a> 相爱的故事<br />
        to be continued... ...
      </p>
    </div>

    <div class="container grid-sm s-content posts">
      <div class="column col-12">
        <ol class="description" style="text-align: left">
          To Do together List
          <li v-for="(item, index) in todoList" :key="index">
            <div :class="isDone(item)">{{ item }}</div>
          </li>
        </ol>
      </div>
    </div>
  </div>
</template>

<script setup>
const lovers = {
  boy: '涂瑜',
  girl: '邹雨薇',
}

const todoList = [
  '露营⛺️',
  '躺在草地上看满天星✨✨',
  '滑雪⛷️',
  '听火苗燃烧噼里啪啦的白噪音',
  'x抓到一片落叶呀 一定要抓到一片新鲜的落叶🍂 ',
  //  涂瑜：3 vs 邹雨薇 2
  '去森林森林！',
  '涂瑜说和他一起看《十二夜》，然后我听他讲故事',
  '涂瑜还说要看《暖暖内含光》',
  '冲浪🏄‍♀',
  '爬紫金山呀！（算了 太难了 被紫金山支配的恐惧）',
  '一起听歌10000分钟🎵（涂瑜说听到网易云倒闭，我觉得他又说大话）',
  //并不 认真的哈！当前进度： 虾米倒闭 QQ音乐倒闭 咪咕音乐倒闭 网易云音乐倒闭 街声倒闭 Spotify倒闭
  'Rick & Morty - Season I',
  'Rick & Morty - Season II',
  'Rick & Morty - Season III',
  'Rick & Morty - Season IV',
  '《一一》我可以每年看一次',
  'x一起去吃热气腾腾的牛肉汤',
  '一起看4/100/500/1000部电影',
  'x带涂瑜吃不要香菜但也有灵魂的鸭血粉丝汤',
  'x骑车 大于五公里的那种（有人总是在微信上随口答应我一些以为我会忘记的约定 发现被记录在案之后又感到惶恐）',
  //（勉强tick 上次马马虎虎骑了三四公里  乱说 明明4.9公里！
  '吃香菜 吃肥肠 No ！！！',
  'x吃脑花',
  '邹雨薇要带我去吃正宗正宗的鱼香肉丝',
  '明年把文学回忆录带给邹雨薇薇薇薇',
  '去西藏',
  'x给邹雨薇清空多抓鱼购物车 (you made it!!! 💕 love!)',
  'x带邹雨薇吃可香可香的生滚及第粥',
  'x陪邹雨薇一起看病 体检（我没病🙄）',
  'x看海啊看海🌊',
  //- 初步计划12/25-12/31 涠洲岛
  //- step 1 机票已定 12/26
  'x给邹雨薇关闭微信隐私收集（邹雨薇自己关的🙄️）',
  '（涂瑜让我和他）和涂瑜一起玩一款单机游戏',
  '一起去迪士尼（看烟花🎆 不玩儿过山车跳楼机）',
  '下次见面邹雨薇给我说说我在她心中的形象',
  //（我这是给你一个重塑形象的机会，趁在下次见面之前好好树立自己的形象，就像面试之前临时抱佛脚一样😏
  'x观摩邹雨薇剪头发💇‍♂️（观摩不了了 邹雨薇已经剪完了）',
  '早睡早起不熬夜！！（2020年 🙄）',
  '一起看完贾樟柯的所有电影',
  '十年后来无锡长广溪湿地公园看那颗小小的乌桕树有没有长大',
  '涂瑜找好工作后一起学python',
  'x一起放风筝🪁（用两百米的线放了300m）',
  'x看脱口秀现场',
  '一起给邹雨薇的xs换电池（售后包赔13📱）（等xs的电池也到需要维修的时候吧）',
  '一起去看茄子蛋现场',
  '《蜻蜓之眼》',
  '更新涂瑜的简历',
  '《76天》',
  '《百万英镑》',
]

function isDone(item) {
  let x = item.indexOf('x')
  return !x ? 'done' : ''
}
</script>

<style lang="scss" scoped></style>
