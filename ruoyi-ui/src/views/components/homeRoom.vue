<template>
      <div class="room">
      <div class="title">
      <img :src ="homepic[2]" style="width:100%">
    </div>
    <div class="content"> 
      <lineBar :label="labCondition.label" :value="labCondition.value" :title="lineTitle"></lineBar>
       <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>实验室房间情况情况</span>
            <el-button style="float: right; padding: 3px 0" type="text">具体查看</el-button>
          </div>
           <!--环形图-->
      </el-card>
    </div>
  </div>
</template>
<script>
import lineBar from "@/components/echarts/lineBar.vue"
import {GetLabUn} from "@/api/system/user"

export default {
     components:{
        "lineBar":lineBar,
    },
    data(){
        return{
        //实验室情况
        labCondition: {label:[],value:[]},
        //实验室情况
        lineTitle:"实验室情况",
        //
         homepic:[
            "https://saas.ilabservice.cloud/static/deviceBg2.af5c9588.png",
            "https://saas.ilabservice.cloud/static/chemicalBg2.fbcc777d.png",
            "https://saas.ilabservice.cloud/static/labBg2.2527f98a.png",
            ],
        }
    },
    mounted(){
        this.GetLab()
    },
    methods:{
        /**获取房间情况情况 */
        GetLab(){
            var myDate = new Date;
				var year = myDate.getFullYear();    //获取当前年
				var mon = myDate.getMonth() + 1;    //获取当前月
				var date = myDate.getDate();        //获取当前日
				var hours = myDate.getHours();      //获取当前小时
				var minutes = myDate.getMinutes(); //获取当前分钟
				var seconds = myDate.getSeconds(); //获取当前秒
				var now = year + "-" + mon + "-" + date + " " + hours + ":" + minutes + ":" + seconds;
            GetLabUn({definiteTime:now}).then(res=>{
                this.labCondition.label.push("可预约")
                this.labCondition.label.push("不可预约")
                this.labCondition.value.push(res.data.subscribedAmount)
                this.labCondition.value.push(res.data.notSubcribedAmount)
              
            })
        },
    }
}
</script>
<style lang="scss" scoped>
.room{
  background: linear-gradient(#F4A460,#FFFAF0);
  width: 95%;
  padding:10px;
  margin-left: 2%;
  .img{
    position: absolute;
    width: 100%;
  }
  .content{
    background: #fff;
    width: 95%;
    padding: 20px;
    margin-left: 2%;
    display: flex;
    .box-card{
        width: 500px;
        margin-left: 50px;
        .lineBar{
          width: 50%;
        }
        .items{
          display: flex;
          flex-direction: column;
          flex: 1;
          .item{
            line-height: 70px;
            align-items: center;
            margin-top: 50px;
            li{
              color: #3bc99a;
              font-size: 70px;
            }
            span{
              font-size: 20px;
              color: #25a178;
              margin-top: -30px;
              margin-left: 30px;
            }
          }
        }
    }
  }
}
</style>