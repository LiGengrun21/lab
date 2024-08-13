<template>
<div class="pages">
  <homeSearch></homeSearch>
  <div class="short">
        <div class="item" v-for="(item,index) in pics" :key="index" >
          <img :src="item.img" style="width:50px">
          <p> {{item.name}}</p>
        </div>
  </div>
      <!--设备情况-->
  <homeEquiment ></homeEquiment>
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
    <homeRoom></homeRoom>
  </div>
</template>

<script>
import pieDemo from  "@/components/echarts/PieDemo.vue"
import lines from '@/components/echarts/drawLine.vue'
import homeSearch from "./components/homeSearch.vue"
import homeRoom from "./components/homeRoom.vue"
import homeEquiment from "./components/homeEquimnet.vue"
export default {
  name: "index",
  components:{
    "pieDemo":pieDemo,
    "lines":lines,
    "homeRoom":homeRoom,
    "homeSearch":homeSearch,
    "homeEquiment":homeEquiment
  },
  data() {
    return {
      operator:'',
      loading: true,
      loading1: true,
      loading2: true,
      queryParams: {
      },
      // 版本号
      version: "3.4.0",
      //日期的标题
      Weektitle:"",
      
      week:["日","一","二","三","四","五","六"],
      pics:[
        {img:"https://saas.ilabservice.cloud/static/avatar.8ff022e2.png",name:"我的预约"},
        {img:"https://saas.ilabservice.cloud/static/avatar.8ff022e2.png",name:"我的信息"},
        {img:"https://saas.ilabservice.cloud/static/avatar.8ff022e2.png",name:"我的预约"},
        {img:"https://saas.ilabservice.cloud/static/avatar.8ff022e2.png",name:"我的预约"},
        {img:"https://saas.ilabservice.cloud/static/avatar.8ff022e2.png",name:"我的预约"},
        {img:"https://saas.ilabservice.cloud/static/avatar.8ff022e2.png",name:"我的预约"},
        {img:"https://saas.ilabservice.cloud/static/avatar.8ff022e2.png",name:"我的预约"},
      ],
      homepic:[
      "https://saas.ilabservice.cloud/static/deviceBg2.af5c9588.png",
      "https://saas.ilabservice.cloud/static/chemicalBg2.fbcc777d.png",
     "https://saas.ilabservice.cloud/static/labBg2.2527f98a.png",
      ],
      //设备数据
      Piedata:[
          {value: 1048, name: '类型一'},
          {value: 735, name: '类型二'},
          {value: 580, name: '类型三'},
          {value: 484, name: '类型四'},
          {value: 300, name: '类型五'}
      ],
      //化学品数据
      linedata:{
        label:["化学品一","化学品二","化学品三","化学品四","化学品五"],
        value:[120,140,150,250,320]
      },
      //化学品类的图标
      iconList:[{label:"违规领取",value:"el-icon-s-marketing"},{value:"el-icon-help",label:"过期"},{value:"el-icon-s-platform",label:"低库存"},],
      //
      activeIndex:"2",
      //值班人员信息
      workder:{},
    };
  },
};

</script>

<style scoped lang="scss">
.homeSearch{
  width: 100%;
}
.short{
  margin-left: 2%;
  width: 92%;
  display: flex;
  flex: 1;
  padding: 20px;
  background: #fff;
  box-shadow: 0px 0px 5px 5px #e0e0e08a;
  border-radius: 20px;
  .item{
    margin-left: 100px ;
  }
}
.chemical{
  width: 95%;
  padding: 10px;
  margin-left: 2%;
  background: linear-gradient(#66CDAA,#F0FFF0);
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
    .lines{
      width: 50%;
      min-width: 700px;
      box-shadow: 0px 0px 5px 10px rgba(218, 218, 218, 0.589);
    }
    .box-card{
        width: 50%;
        margin-left: 50px;
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
.homeRoom{
  width: 100%;
}
.homeEquiment{
  width: 100%;
}

</style>

