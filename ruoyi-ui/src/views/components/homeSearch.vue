<template>
     <div class="app-container ">
    
      <el-card class="box-card"  v-loading="loading">
          <div class="header">
              <span>欢迎来到智慧实验室系统!!!</span>
              <img src="https://saas.ilabservice.cloud/static/search_pic2.b1f491c0.png" style="position:absolute;width:300px;margin-top:30px">
          </div>
          <div class="search">
            <el-menu :default-active="activeIndex"   mode="horizontal" >
              <el-menu-item index="1">设备</el-menu-item>
              <el-menu-item index="2">化学品</el-menu-item>
              <el-menu-item index="3">房间</el-menu-item>
            </el-menu>
            <div class="in" style="display:flex">
               <el-input
                  placeholder="请输入内容"
                  v-model="inputSear">
                  <i slot="prefix" class="el-input__icon el-icon-search"></i>
                </el-input>
                <el-button type="success">搜素</el-button></div>
          </div>
      </el-card> 
        <el-card class="Position" v-loading="loading">
      <!--七天设备情况-->
          <div class="header">
            <p class="left">实验室日历</p>
            <p class="right">值班人员:{{workder.name}}</p>
            <p class="right">{{Weektitle}}</p>
            
          </div>
          <div class="body">
            <canledar></canledar>

               
               
          </div>
          <div class="phone">值班电话：{{workder.phone}}</div>
        </el-card>
    <!--快捷键-->

</div>
</template>
<script>
import {GerWork} from "@/api/system/user"
import canledar from "./calendar.vue"
export default {
  components:{
    canledar
  },
    data(){
      return{
        loading:true,
        //search内容
        inputSear:"",
        //值班人员信息
        workder:{},
        //
        activeIndex:"1",
        //日期的标题
        Weektitle:"",
        activeIndex:"1",
        week:["日","一","二","三","四","五","六"],
      }
    },
    mounted(){
        this.getweeek()
        this.Getwatch()
    },
    methods:{
      //获取一周的时间范围
    getweeek(){
        var myDate = new Date();
        var tYear = myDate.getFullYear();
        var tMonth = myDate.getMonth();
        this.Weektitle=tYear+"年"+tMonth+"月"
        
    },
      //获取值班人员信息
    Getwatch(){
      const date=new Date(new Date()).toLocaleDateString()
      GerWork(date).then(res=>{
        this.workder={name:res.data.nickName,phone:res.data.phonenumber}
        this.loading=false
      })
    },

    }

}
</script>
<style scoped lang="scss">
.app-container{
  display: flex;
.box-card{
  border-radius:10px;
  padding: 20px;
  width: 48%;
  min-width: 500px;
  background: linear-gradient(#3bc99a,#b3f3f3);
  .header{
    
    span{
      color: white;
    }
  }
  .search{
    float:center;
    margin-top: 200px;
    .el-menu{
      background: transparent;
      color: white;
    }
    .in{
      line-height: 35px;
       .el-input{
      margin-top: 10px;
    }
    .el-button{
      height: 35px;
      margin-top: 10px;
    }
    }
   
    
  }
}
.Position{
  background: linear-gradient(#0066FF,#c7ecff);
  min-width: 500px;
  width: 48%;
  margin-left: 10px;
  border-radius:10px;
  padding: 10px;
  .header{
    display: flex;
    flex: 1;
      .left{
        font-weight: 700;
        color: white;
      }
      .right{
        font-weight: 700;
        color: white;
        margin-left: 100px;
      }
  }
  .body{
    height: 200px;
  }

  .phone{
    margin-top: 10px;
  }
}
}
</style>