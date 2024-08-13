<template>
    <div class="page">
        <div class="infor">
        <div class="box1">
            <div class="pic">
                <UserAver :user="user" style=" height: 145px;margin-top: -24px;"></UserAver>
                
                <span class="el-icon-bank-card" style="margin-left:20px;margin-top:25px">19岁</span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                <span  style="margin-left:10px;margin-top:25px;" class="el-icon"><li :class="user.sex==1?'el-icon-male':'el-icon-female'" style="color:blue"></li>{{user.sex==1?'男':'女'}}</span>
            </div>
            <div class="infot_title">
                <span ><i class="el-icon-s-custom"></i>&emsp;&emsp;&emsp;名字:&emsp;&emsp;&emsp;<span>{{user.nickName}}</span></span><hr>
                <span ><i class="el-icon-s-platform" style="font-size:#fff"></i>&emsp;&emsp;&emsp;科室:&emsp;&emsp;&emsp;<span>{{user.status}}</span></span><hr>
                <span ><i class="el-icon-s-check"></i>&emsp;&emsp;&emsp;PI组:&emsp;&emsp;&emsp;<span>{{user.piUsername}}</span></span><hr>
            
            </div>
        </div>
        <div class="box2">
            <div class="item" v-for="(item,index) in date" :key="index">
                <span><i :class="icon[index]"></i>&emsp;&emsp;&emsp;{{label[index]}}</span>&emsp;&emsp;&emsp;<span>{{user[item]}}</span>
            </div>
            <el-button type="primary" class="alter" @click="infor_edit()">信息修改<li class="el-icon-edit-outline"></li></el-button>
           
        </div>
        <div class="box3">
    <el-table
      :data="tableData"
      >
      <el-table-column
        prop="date"
        label="权限说明"
        style="width:30vw;font-size:23px">
      </el-table-column>
      <el-table-column
        prop="name"
        label=""
        style="width:8vw;">
      </el-table-column>
      
      
        </el-table>
        </div>
        </div>
        <div class="explain">
            <!--科研项目介绍-->
            <div class="project" >
                <h2>{{project_infor.title}}</h2>
                <p>{{user.studyIntroduction}}</p>
            </div>
            <!--我的最新消息-->
            <div class="affair">
                    <div class="my_msg">
                        <el-badge :value="200" :max="99" >
                        <el-button size="medium" class="item" > 我的消息</el-button>
                        </el-badge>
                        <div class="msg_detail">

                        </div>
                    </div>
                    <div class="my_msg2">
                        <el-badge :value="200" :max="99" >
                        <el-button size="medium" class="item" > 今日待办事项</el-button>
                        </el-badge>
                        <div class="msg_detail2">

                        </div>
                    </div>
                
            </div>
        </div>
        <!--对话框-->
          <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-row>
                <el-col :span="12">
                    <el-form-item label="昵称" prop="nickName">
                    <el-input v-model="form.nickName" placeholder="请输入用户昵称" />
                    </el-form-item>
                </el-col>
                  </el-row>
                <el-row :span="12">
                    <el-form-item label="性别">
                    <el-select v-model="form.sex" placeholder="请选择" style="width:200px">
                        <el-option
                        label="男"
                        value="男"
                        ></el-option>
                        <el-option
                        label="女"
                        value="女"
                        ></el-option>
                    </el-select>
                    </el-form-item>
                </el-row>
              
                <el-row>
                <el-col :span="12">
                    <el-form-item v-if="form.userId == undefined" label="账号" prop="userName">
                    <el-input v-model="form.userName" placeholder="请输入用户名称" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="学位" prop="deptId">
                        <el-input v-model="form.degree" placeholder="请输入学位" />
                    </el-form-item>
                </el-col>
                </el-row>
                <el-row>
                <el-col :span="12">
                    <el-form-item v-if="form.userId == undefined" label="" prop="password">
                    <el-input v-model="form.password" placeholder="请输入用户密码"  />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="学校">
                        <el-input v-model="form.school" placeholder="请输入学校"  />
                    </el-form-item>
                </el-col>
                </el-row>
                <el-row>
                <el-col :span="12">
                    <el-form-item label="手机号码" prop="phonenumber">
                    <el-input v-model="form.phonenumber" placeholder="请输入手机号码" maxlength="11" />
                    </el-form-item>
                </el-col>
                </el-row>
                <el-row>
                <el-col :span="12">
                    <el-form-item label="邮箱" prop="email">
                    <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50" />
                    </el-form-item>
                </el-col>
                </el-row>
                <el-row>
                <el-col :span="24">
                    <el-form-item label="项目介绍">
                    <el-input v-model="form.studyIntroduction" type="textarea" placeholder="请输入pi姓名"></el-input>
                    </el-form-item>
                </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button  type="primary" @click="updateInfor()" >确定修改</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
            </el-dialog>
    </div>
</template>
<script>
import {getUserProfile,getChemicals,updateUser}  from "@/api/system/user"
import UserAver from "./commponets/userAvatar.vue"
/**
 * nickName  用户名字
 * sex       性别
 * 
 */
export default {
    components:{
        UserAver
    },
    data(){
    const checkPhone = (rule, value, callback) => {
      if (value) {
        const reg = /^1[3|4|5|7|8][0-9]\d{8}$/

        if (reg.test(value)) {
          callback()
        } else {
          return callback(new Error('手机号格式错误'))
        }
      } else {
        return callback(new Error('手机号不能为空'))
      }
    }
        return{
            user:{
                nickName:"杨洋",
                sex:1,
                status:"化学科",
                piUsername:"地表最强",
                degree:"", 
                school:"hua科大",
                userName:"201906060310",
                phonenumber:"17858426841",      
                studyDuration:"2020-09-10",
                email:"2795474869@qq.com",
                studyIntroduction:"硕士",
                userId:0
            },
            label:["学位","学校","账号","电话","年限","邮箱"],
            icon:["el-icon-eleme","el-icon-school","el-icon-user-solid","el-icon-phone","el-icon-date","el-icon-message"],
            date:["degree","school","userName","phonenumber", "studyDuration","email"],
            project_infor:{title:"科研项目介绍"},
            tableData: [{
            date: ' 是否设计病原体微生物？',
            name: '是',
           
          }, {
            date: '是否设计人体样本？',
            name: '是',
          }, {
            date: '是否设计危化品？',
            name: '是',
            
          }],
        form: {
            userID:1,
            nickName:"杨洋",
            sex:1,
            status:"化学科",
            piUsername:"地表最强",
            degree:"", 
            school:"hua科大",
            userName:"201906060310",
            phonenumber:"17858426841",      
            studyDuration:"2020-09-10",
            email:"2795474869@qq.com",
            studyIntroduction:"硕士"
        },
        formLabelWidth: '120px',
        open:false,
        rules:{
            email:[
                    { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }],
            phonenumber:[
                { validator: checkPhone}
            ]
        },        //校验规则
        title:"信息修改",
        Chemicals_list:{
            chemicals:[],
            micros:[],
            personSamples:[]
        }
        }
        
    },
    mounted(){
        this.GetUsetInfor()
        this.GetChem()
    },
    methods:{
        //获取用户个人信息
        GetUsetInfor(){
            getUserProfile().then(res=>{
                this.form=res.data
                this.user=res.data
            })
        },
        /**
         * 获取实验室准许情况
         */
        GetChem(){
            getChemicals({userId:this.user.userId}).then(res=>{
                console.log(res)
                thos.Chemicals_list=res.data
            })
        },
        
        updateInfor(){
            updateUser(this.form).then(res=> {
              console.log(res)
              this.msgSuccess("修改成功");
              this.open = false;
              GetUsetInfor()
            });
        },
        //表单重置
        reset(){
            this.form={
                userID:1,
                nickName:"",
                sex:1,
                status:"",
                piUsername:"",
                degree:"", 
                school:"",
                userName:"",
                phonenumber:"",      
                studyDuration:"",
                email:"",
                studyIntroduction:""
            }
        },
        /**弹出信息编辑 */
        infor_edit()
        {
            this.open=true
        },
        submitForm(){

        },
        // 取消按钮
        cancel() {
        this.open = false;
       
        },

    }
}
</script>
<style  scoped>
.page{
    display: flex;
}
.el-select{
    width: 200px;
}
    .infor{
        width: 25vw;
        height: 95%;
        border:5px #F2F2F2 ;
    }
    .box1{
        margin-left: 50px;
        margin-top: 50px;
        width: 25vw;
        height: 23vh;
        display: flex;
        flex:1;
        
    }
    .box1 img{
        width:120px ;
        height:120px ;
       
        border:#AAAAAA  5px;
    }
    .box1 .infot_title{
        margin-left: 20px;
        display: flex;
        flex-direction: column;
    }
    .box1 .infot_title p{
        margin-top: 5px;
        font-size: 18px;
    }
    .box2 .item{
        height: 6.7vh;
        width: 25vw;
    }
    .box2 .item span{
        margin-left: 30px;
        line-height: 6.7vh;
    }
  .item:nth-child(2n) {
     background-color: #ffffff;
  }
  
  .item:nth-child(2n+1) {
     background-color: #F2F2F2;
  }
  .infor .box3 .el-table{
      width:30vw;
      height: 30vh;
      margin-left: 20px;
  }
  .item i{
      font-size: 20px;
      line-height: 6.7vh;
      
      margin-left: -5px;
  }
  .alter{
      position: relative;
      margin-left: 10%;
      font-size: 15px;
      width: 80%;
      border-radius: 20px;
      margin-top: 5px;
  }

  .explain{
      width: 70vw;
      height: 100vh;
      margin-left: 15px;
    border:#AAAAAA  5px;
  }
 .explain .project{
     position: relative;
     width: 95%;
     margin-left:20px ;
     height: 60vh;
     box-shadow: 0px 0px 5px 5px #c4c4c433;
     border-radius: 10px ;
  }
  .project p{
      width: 95%;
      margin-left: 20px;
  }
  .project h2{
      margin-left: 10px;
  }
  

  .explain .affair {
      display: flex;
      width:60vw;
      height:35vh;
      margin-top: 15px;
      margin-left: 15px;
  }
  .explain .affair .my_msg .item{
      background:#ffffff;
      color:rgb(5, 5, 5);border: none;
      border-radius:10px
  }
   .explain .affair .my_msg .item:hover{
       background:#21A2EB;
       color: white;
   }
  .explain .affair .msg_detail{
      margin-top:10px;
      margin-left: 15px;
        width: 26vw;
        height: 35vh;
        border-radius: 2px;
        border-color:6px #AAAAAA ;
        box-shadow:0px 0px 0px 5px #c4c4c434;
  }
   .explain .affair .my_msg2 .item
   {
        background:#ffffff;
        color:rgb(5, 5, 5);border: none;
        border-radius:10px;
       margin-left: 45px;
   }
   .explain .affair .my_msg2 .item:hover
   {
         background:#21A2EB;
         color: white;
   }
  .explain .affair .msg_detail2{
      margin-top:10px;
      margin-left: 45px;
        width: 26vw;
        height: 35vh;
        border-radius: 2px;
        border-color:6px #AAAAAA;
        box-shadow:0px 0px 0px 5px #c4c4c431;
  }
  .el-icon-eleme{
     color: #21A2EB;
     font-size: 150%;
  }
 .el-icon-school{
     color:#21A2EB ;font-size: 150%;
 }
 .el-icon-user-solid{
     color: rgb(218, 69, 32);font-size: 150%;
 }
 .el-icon-phone{
     color: rgb(27, 212, 11);font-size: 150%;
 }
 .el-icon-date{
    color: rgb(32, 218, 193);font-size: 150%;
 }
 .el-icon-message{
    color: goldenrod;font-size: 150%;
 }
 .el-icon-postcard{
     color: goldenrod;font-size: 150%;
 }
 .el-icon-s-custom{
      color: goldenrod;font-size: 150%;
      margin-top: 15px;

 }
</style>