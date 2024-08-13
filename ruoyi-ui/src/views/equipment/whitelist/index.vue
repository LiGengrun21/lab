<template>
  <div class="pages">
      <div class="fun">
          <el-row>
            <el-button type="primary" icon="el-icon-plus"  @click="opendialog()">添加</el-button>
            <el-button type="warning" icon="el-icon-delete"
            :disabled="multiple"
             @click="handleDelete">删除</el-button>
            <el-button type="primary" plain icon="el-icon-upload2">导入</el-button>
            <el-button type="success" plain icon="el-icon-download">导出</el-button>
          </el-row>
      </div>
      <hr style="width:95%">
      <div class="body">
      <el-table v-loading="loading" :data="whitelistList" >
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column label="设备" align="center" prop="equipmentId" width="150"
      :formatter="EquipmentFormat"
      :filters="filter"
      :filter-method="filterHandler"
      />
      <el-table-column label="用户" align="center" width="200" prop="userId" :formatter="UserFormat" />
      <el-table-column
      prop="addDate"
      label="建立日期"
      sortable
      width="180">
    </el-table-column>
    <el-table-column
      prop="adderNickname"
      label="建立者"
      :filters="filter"
      :filter-method="filterHandler"
      width="180">
    </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click=" handleDelete(scope.row)"
            v-hasPermi="['equipment:whitelist:remove']"
          >删除</el-button>
          
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
      </div>

      <el-dialog :title="title"   :visible.sync="open" width="800px"  append-to-body>
        <el-form ref="form" :model="form"  label-width="100px" label-position="left"
        >
        <el-row>
        <el-form-item label="设备"  prop="equipmentId" >
          <el-select v-model="form.equipmentIds" >
            <el-option
              v-for="item in equipments"
              :key="item.equipmentId"
              :label="item.equipmentName"
              :value="item.equipmentId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item  label="院区">
        <el-select v-model="hospitalsID" >
            <el-option
              v-for="item in hospitals"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        </el-row> <hr>
        <!--<el-form-item label="人员"  prop="userId">
            <el-select v-model="form.userId" multiple>
            <el-option
              v-for="item in User"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId">
            </el-option>
            </el-select>
        </el-form-item>-->
        <el-form-item class="tree" label="部门选择" >
        <el-tree :data="data" :props="defaultProps" @node-click="GetuserBy" ></el-tree>

        </el-form-item><hr>
        <el-form-item label="人员选择">
          <el-transfer
          filterable
          filter-placeholder="请输入人员姓名"
          :titles="['成员','白名单成员']"
          v-model="whiteNum"
          :data="data2">
        </el-transfer>
        </el-form-item>

        </el-form>
        <div class="btn">
            <el-button type="primary"  @click=" ListAddUser">确定添加</el-button>
            <el-button type="primary"  @click="cancel()">取消添加</el-button>
        </div>
      </el-dialog>
  </div>
</template>

<script>
import {listWhitelist,getWhitelist,delWhitelist,addWhitelist,updateWhitelist,exportWhitelist,deleteWhitelist,AddWhites} from '@/api/equipment/whitelist'
import { listShow } from '@/api/equipment/show'
import { listUser,GetDept, GetAAllPis2,GetPiuser,getUserProfile } from '@/api/system/user'

export default {
  data(){
    return{
        whitelistList:[],
        User: [],
        equipments: [],
        hospitals:["医院一","医院二"],
        hospitalsID:"",
        data: [{
          label: '部门 1',
          children: [{
            label: '组别一'
          }]
        },{
            label:"PI",
            children:[{
              label: '所有的PI',
              value:"AllPIS"
            }]
          }],
        //成员列表
        data2:[],
        //获取的value值
       whiteNum: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      ,
        queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      // 遮罩层
      loading: true,
      //对话框表单
      form:{},
      //是否开启对话框
      open:false,
      //是否选中数据
      multiple:false,
      //
      title:"添加白名单",
      filtertag:[],
      //设备列表
      equipments:[],
      //数量
      total:0,
      //筛选项
      filter:[{text:"共聚焦显微镜",value:"共聚焦显微镜"},{text:"-80度冰箱",value:"-80度冰箱"}],
      //建立者 筛选的数据
      creater:[{text:"若依",value:"若依"},{text:"-80度冰箱",value:"-80度冰箱"}],
      depIds:[],
      //某PI下的成员列表
      userList:[145,146]

    }
  },
  watch:{
    whiteNum: {
      handler(newValue,oldValue) {
        console.log(newValue)
      }
    }
  },
  created(){
    //获取设备列表
    this.getEquipments();
    //获取所有的白名单列表
    this.getList();
    //获取部门里欸奥的用户
    this.getUser();
    this.GetDepList()
    
  },
  methods:{
    /**批量添加白名单 */
    ListAddUser(){
      //获取建立者信息
      let  createid="";
      getUserProfile().then(res=>{
          createid=res.data.nickName
      })
      //数据处理
      let equipmentWhitelistList=[]
      for(var i=0;i<this.whiteNum.length;i++){
        equipmentWhitelistList.push({equipmentId:this.form.equipmentIds,userId:this.whiteNum[i],addDate:new Date(),adderNickname:createid})
      }
      AddWhites(equipmentWhitelistList).then(res=>{
        this.msgSuccess("添加成功")
        this.getList()
        this.open=false
      }).catch(err=>{
        console.log(err)
        this.msgError("不能重复添加")
      })
    },
    //根据PI获取所有的成员
    async  GetuserBy(row){
      if(row.value==="AllPIS"){
        const res=await GetAAllPis2()
        this.data2=res.data.map(function(item){
          return {label:item.nickName,key:item.userId,value:item.userId}
        })
      }
      else{
        GetPiuser({piId:row.value}).then(res=>{
        const data=res.data
        this.data2=data.map(function(item,index){
          console.log(item.nickName)
          return {label:item.nickName,key:item.userId,value:item.userId}
      })
      console.log(this.data2)
      })}
    },
    /**获取所有PI */
    async GetPIs(){
    const date=[]
     GetAAllPis2().then(res=>{
       console.log(res.data)
       this.data[0].children=res.data.map(function(item){
         return {label:item.nickName,value:item.userId}
       })
       this.data
     })
    },

    /**获取所有的部门 */
    async GetDepList(){
    const res=await GetDept()
    const result=res.data
    this.depId=result.map(function(item,index){
        return {deptId:item.deptId,label:item.deptName}
      })
      //格this.GetPIs(this.depId)
    },
    /**获取白名单数据 */
    getList(){
        this.loading = true;
        listWhitelist(this.queryParams).then(response => {
          this.whitelistList = response.rows;
          this.total = response.total;
          this.loading = false;
      });
    },
    /**获取设备名 */
    getEquipments(){
      listShow().then(response =>{
        this.equipments = response.rows;
      });
    },
    /** */
    getUser(){
      listUser().then(res =>{
        this.User = res.rows;
      });
    },
    /**打开对话框 */
    opendialog(){
      this.open=true
      this. GetPIs();
    },
    /**关闭对话框 */
    cancel(){
      this.open=false
    },
    //重置数据
    reset() {
      this.form = {
        equipmentId: null,
        userId: null
      };
      this.resetForm("form");
    },
    //新建白名单
    newwhiteList(){
      //数据处理
      const list_data=[]

        addWhitelist()
    },
    //删除白名单
    handleDelete(row) {
      const equipmentIds = row.equipmentId || this.ids;
      this.$confirm('是否确认删除设备白名单编号为"' + equipmentIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteWhitelist(row);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    formatter(row, column) {
        return row.address;
      },
    filterTag(value, row) {
        return row.tag === value;
      },
    filterHandler(value, row, column) {
        const string= this.EquipmentFormat(row, column)
        return   string=== value;
      },
    UserFormat(row,column){
      const userProp = row[column.property];
      let string = null;
      for(var i = 0;i < this.User.length;i++){
        if(userProp == this.User[i].userId){
          string = this.User[i].nickName;
        }
      }
      return string;
    },
    EquipmentFormat(row, column){
      const equipmentProp = row[column.property];
      let string = null;
      for(var i = 0;i < this.equipments.length;i++){
        if(equipmentProp == this.equipments[i].equipmentId){
          string = this.equipments[i].equipmentName;
        }
      }
      return string;
    },
    filterMethod(query, item) {
        return item.value.indexOf(query) > -1;
      }
  }
}
</script>
<style  scoped>
.pages{
  margin-left: 50px;
  width: 1100px;
}
  .fun{

    margin-top: 50px;
  }
  .el-dialog{
    display: flex;
    text-align: center;
  }
  .el-dialog .el-row{
    display: flex;
  }
  .el-dialog .el-form-item{
      width: 700px;


    }
    .el-dialog .btn{
      display: flex;
      margin-left: 62%;
    }
</style>
