<template>
     <div class="chemical">
    <div class="title">
      <img :src ="homepic[1]" style="width:100%">
    </div>
    <div class="content"> 
      <lines :barlabel="linedata.label" :barValue="linedata.value"></lines>
       <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>化学药品情况</span>
            <el-button style="float: right; padding: 3px 0" type="text">具体查看</el-button>
          </div>
           <!--环形图-->
         <div class="items">
           <div class="item" v-for="(item,index) in iconList" :key="index" >
              <li :class="item.value"></li><span>{{item.label}}</span>
           </div>
         </div>
      </el-card>
    </div>
    
  </div>
</template>
<script>
export default {
    data(){
        return{
            
        }
    }
}
</script>
<style lang="scss" >

</style>