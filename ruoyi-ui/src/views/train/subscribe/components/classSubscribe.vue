<template>
    <div class="total"  >
      <div class="title"><h2>{{classFormat(item.classId)}}</h2></div>
      <div class="bodys">
        <div class="item">
          <span class="items"><span class="Bigtitle" ><li class="el-icon-s-custom"></li>课程发起人:</span>
          <span class="master">{{item.trainAdder}}</span></span>
          <span class="items"><span class="Bigtitle" ><li class="el-icon-phone-outline"></li>培训联系方式:</span>
          <span class="master">{{item.trainTime}}</span></span>
        </div>
      <div class="item">
          <span class="items"><span class="Bigtitle" ><li class="el-icon-date"></li>培训时间:</span>
          <span class="master">{{item.trainTime}}</span></span>
          <span class="items"><span class="Bigtitle"><li class="el-icon-map-location"></li>培训地点:</span>
          <span class="master">{{item.trainAd}}</span></span>
          <span class="num"><span class="Bigtitle"><li class="el-icon-map-location"></li>最大人数:</span>
          <span class="master">{{item.trainMaxPerson}}</span></span>
      </div>
      </div>
      <el-button  icon="el-icon-circle-plus-outline" type="primary"
       @click="handleSubscribe(item.trainId)" v-show="bool">可报名</el-button>
      <el-button  icon="el-icon-circle-plus-outline" type="danger" 
      @click="handleDelete(item.trainId)"     v-show="!bool">取消报名</el-button>
  </div>
</template>
<script>
import { listClass } from '@/api/train/class'
import { addTrainUser,listTrainUser,deleteTrainUser } from '@/api/train/trainUser'
import { getUserProfile } from '@/api/system/user'
export default {
    props:["date"],
    data(){
        return{
            //报名数据
            item:this.date,
            classes:[],
            recorde:[],
            user:{},
            bool:true
            //
        }
    },
    watch:{
        date:function(newData,oldData){
            this.item = newData; 
            this.bool=true 
            this.isSign() 
        }
	},
    mounted(){
        this.getUser()
        this.getclasses()
        this.isSign() 
    },
    methods:{
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
      });
    },
     /**获取课程预约记录 */
    /**判断该课程是否已经报名 */
    async isSign(){  
      let data=await listTrainUser()
      this.recorde=data.rows
      this.bool=true
      for(var i=0;i<this.recorde.length;i++){
        if((this.user.userId === this.recorde[i].userId) && (this.item.trainId === this.recorde[i].trainId)){
          this.bool=false
          console.log("有该报名")
          break
        }
      }
    },
    /**取消报名 */
    handleDelete(row) {
      const form={trainId:row,userId:this.user.userId}
      this.$confirm('是否确认取消该培训的报名', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(()=> {
          return deleteTrainUser(form);
        }).then(() => {
        this.isSign()
        this.msgSuccess("取消成功")
        })
    },
    /** 报名按钮操作 */
    handleSubscribe(row){
      var trainUser = {
          trainId:row,
          userId:this.user.userId,
      }
      this.$confirm('是否确认报名该培训?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() =>{
        addTrainUser(trainUser).then(res=>{
          this.isSign()
          this.msgSuccess("报名成功")
        })
      })
    },
        //根据课程ID获取课程名字
    classFormat(row){
        let string = null;
        for(var i = 0;i < this.classes.length;i++){
            if(row == this.classes[i].classId){
                string = this.classes[i].className;
            }
        }
            return string;
    },
    //获取课程列表
    getclasses(){
      listClass().then(responce =>{
        this.classes = responce.rows;
      })
      console.log(this.classes)
    },
    }
}
</script>
<style lang="scss">
.total{
  padding: 10px;
  margin-top: 20px;
  text-align: center;
  align-items: center;
  border-radius: 10px;
  box-shadow: 0px 0px 10px 10px rgba(223, 223, 223, 0.473);
  .item{
    display: flex;
    margin-top: 10px;
    .items{
      display: flex;
      width: 380px;
      
      .Bigtitle{
        font-size:16px;
        font-weight:700 ;
        margin-left: 80px;
        line-height: 35px;
      }
      .master{
        font-size:14px ;
        margin-left: 5px;
        line-height: 35px;
      }
      .el-icon-s-custom{
        font-size: 120%;
        color: #99CC99;
      }
      .el-icon-date{
        font-size: 120%;
        color: #CCCC00
      }
      
      .el-icon-map-location{
        font-size: 120%;
        color:#3366CC ;
      }
      .el-icon-phone-outline{
        font-size: 120%;
        color:#3366CC ;
      }
      .el-icon-school{
        font-size: 120%;
        color:#3366CC ;
      }


    }
    .num{
      display: flex;
      width: 250px;
      span{
        font-size: 23px;
        }
      .master{
        font-size: 23px;
        line-height: 35px;
        margin-left: 4px; 
      }
    }
    

  }
  .el-button{
    margin-top: 15px;
    padding-bottom: 10px;
    
  }
  
}
</style>