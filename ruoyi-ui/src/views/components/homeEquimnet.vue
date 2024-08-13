<template>
    <div class="equiment">
    <div class="title">
      <img :src ="homepic[0]" style="width:100%">
    </div>
    <div class="content"> 
      <pieDemo :PieDate="Equimnets"  v-bind:title="title"></pieDemo>
       <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>设备情况</span>
            <el-button style="float: right; padding: 3px 0" type="text">具体查看</el-button>
          </div>
           <!--环形图-->
          <div v-for="o in 4" :key="o" class="textItem">
            <el-divider content-position="left">设备情况</el-divider>
            <el-progress :text-inside="true" :stroke-width="26" :percentage="10*o"></el-progress>
          </div>
        </el-card>  
    </div>
    
  </div>
</template>
<script>
import pieDemo from  "@/components/echarts/PieDemo.vue"
import {getEquimnetNow} from "@/api/system/user"
export default {
    components:{
      pieDemo
    },
    data(){
        return{
           Equimnets:[{name:"总计",value:2}],
           title:"设备预约情况",
           homepic:[
              "https://saas.ilabservice.cloud/static/deviceBg2.af5c9588.png",
              "https://saas.ilabservice.cloud/static/chemicalBg2.fbcc777d.png",
              "https://saas.ilabservice.cloud/static/labBg2.2527f98a.png",
              ],
        }
    },
  mounted(){
      this.GetEquiment()
  },
  methods: {
    /**获取设备预约情况 */
    GetEquiment(){
      const data=new Date(new Date()).toLocaleDateString()
      getEquimnetNow({definiteTime:data}).then(res=>{
         this.Equimnets.push({name:"被预约",value:res.data.subscribedAmount})
         this.Equimnets.push({name:"未预约",value:res.data.notSubcribedAmount})
         console.log(this.Equimnets)
      })
    }
  },
}
</script>
<style lang="scss" scoped>
.equiment{
   width: 95%;
  padding: 10px;
  margin-left: 2%;
  background: linear-gradient(#388cfa,#e8fcfd);
  .img{
    position: absolute;
    width: 100%;
  }
  .content{
    background: #fff;
    width: 95%;
    padding: 20px;
    margin-left: 2.5%;
    display: flex;
    border-radius: 10px;
    .pieDemo{
      width: 50%;
      box-shadow: 0px 0px 5px 10px rgba(218, 218, 218, 0.589);
    }
    .box-card{
      width: 50%;
        margin-left: -50px;
    }

  }
}
</style>