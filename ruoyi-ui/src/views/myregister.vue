
<template>
  <div class = "register">
    <el-col :span="8" :xs="24">
      <el-card>
        <div slot="header" class="clearfix" >
          <a style="font-size: larger"  href="#" @click="index=0" :class="{active:index==0}">普通注册账号</a>|
          <a style="font-size:larger" href="#" @click="index=1" :class="{active:index==1}">PI注册</a>
        </div>
        <div class="reg_body" v-if="index==0">
        <el-form ref="form" :model="form" :rules="rules"  label-position="left"  label-width="100px">
          <el-form-item label="用户名字" prop="nickName">
            <el-input v-model="form.nickName" placeholder="请输入名字" />
          </el-form-item>
           <el-form-item label="性别" prop="sex">
                <el-select v-model="form.sex" placeholder="请选择性别">
                <el-option label="男" value="1"></el-option>
                <el-option label="女" value="0"></el-option>
                </el-select>
            </el-form-item>
          <el-form-item label="账号" prop="userName">
            <el-input v-model="form.userName" placeholder="请输入账号"/>
          </el-form-item>
          <el-form-item label="邮箱"  class="email" prop="email">
            <el-input v-model="form.email" placeholder="请输入邮箱" />
            
          </el-form-item>
          <el-form-item label="手机号" prop="phonenumber">
            <el-input v-model="form.phonenumber" placeholder="请输入手机号"/>
          </el-form-item>
          <el-form-item label="课题组长" prop="piUsername" >
             <el-autocomplete
              class="inline-input"
              v-model="form.piUsername"
              :fetch-suggestions="querySearch"
              placeholder="请输入pi"
              @select="handleSelect"
            ></el-autocomplete>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" placeholder="请输入密码" type="password" autoComplete="new-password"/>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="submitForm()" style="margin-left:66%;">确 定</el-button>
        <el-button @click="cancel">返 回</el-button>
        </div>
        <div class="pi_body" v-if="index==1">
           <el-form ref="form2" :model="pi_form" :rules="rules"  label-position="left" label-width="100px">
          <el-form-item label="用户名字" prop="nickName">
            <el-input v-model="pi_form.nickName" placeholder="请输入名字" />
          </el-form-item>
           <el-form-item label="性别"  prop="sex">
                <el-select v-model="pi_form.sex" placeholder="请选择性别">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
                </el-select>
            </el-form-item>
          <el-form-item label="账号"  prop="userName">
            <el-input v-model="pi_form.userName" placeholder="请输入账号"/>
          </el-form-item>
          <el-form-item label="邮箱"  prop="email">
            <el-input v-model="pi_form.email" placeholder="请输入邮箱"/>
          </el-form-item>
          <el-form-item label="手机号" prop="phonenumber">
            <el-input v-model="pi_form.phonenumber" placeholder="请输入手机号"/>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="pi_form.password" placeholder="请输入密码" type="password" autoComplete="new-password"/>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="piform()" style="line-width:50%;transform:(-50%)">确 定</el-button>
        <el-button @click="cancel">返 回</el-button>
        </div>
      </el-card>
    </el-col>
  </div>
</template>

<script>
import { addUser, piregist, registerUser, GetAllPis } from '@/api/system/user'
//引入固定的echarts图标库
import drawcircle from "@/utils/echart.js"
import  {RULE_REGISTER} from "@/utils/verify.js"
export default {
  name: "myregister",
  data() {
    return {
      index:0,
      redirect: undefined,
      //表单参数
      form: {
        },
      pi_form:{
        
      },
      rules: RULE_REGISTER,
      rules2: {},
      email_value:"",
      PiList:["test","PostMan"],

    };
  },
  mounted(){
    this.Getpis()
  },
  methods: {
    /**获取所有的pi */
    async Getpis(){
      const res=await GetAllPis()
      this.PiList=res.data
      console.log(this.PiList)
      this.PiList=this.PiList.map(function(item){
          return  {value:item.nickName,label:item.userId}
      })
      console.log(this.PiList)   
    },
    /** 提交按钮 */
    submitForm(){
      for(var i=0;i<this.PiList.length;i++)
      {
        if(this.PiList[i].value==this.form.piUsername){
          this.form.piId=this.PiList[i].label
        }
      }
      console.log(this.form)
      if (this.index==0){
      this.$refs["form"].validate(valid => {
        if (valid) {
          console.log(this.form);
          registerUser(this.form).then(response => {
            console.log(response);
            if(response.code == 200)
            {
              this.msgSuccess("注册成功");
              const loc = "/login";
              this.$router.push(loc);
            }
          });
        }
      });
      }
       
    },
    piform(){
            this.pi_form.piId=-1
            piregist(this.pi_form).then(response => {
              console.log(response);
              if(response.code == 200)
              {
                this.msgSuccess("注册成功");
                const loc = "/login";
                this.$router.push(loc);
              }
            });
    },
    cancel(){
      const loc = "/login";
      this.$router.push(loc);
    },
    querySearch(queryString, cb) {
        var PiList = this.PiList;
        var results = queryString ? PiList.filter(this.createFilter(queryString)) : PiList;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
       createFilter(queryString) {
        return (PiList) => {
          return (PiList.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
  },

};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.register {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
} 
.clearfix{
  text-align: center;
  margin-left: 50%;
  padding: 10px;
  width: 600px;
  transform: translate(-50%,-50%);
}
.clearfix span:hover{
  color:  #1881e4;
}
.clearfix .active{
  color:  #1881e4;
}


</style>
