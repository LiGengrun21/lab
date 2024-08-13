<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备名称" prop="equipmentName">
        <el-input
          v-model="queryParams.equipmentName"
          placeholder="请输入设备名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管理员" prop="equipmentAdmin">
        <el-input
          v-model="queryParams.equipmentAdmin"
          placeholder="请输入设备管理员"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="sn编号" prop="equipmentSnNumber">
        <el-input
          v-model="queryParams.equipmentSnNumber"
          placeholder="请输入设备sn编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备型号" prop="equipmentModel">
        <el-input
          v-model="queryParams.equipmentModel"
          placeholder="请输入设备型号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-s-operation"
          size="mini"
          @click="handleAddEquipmentSubscribetest"
          :disabled="single"
          v-hasPermi="['equipment:show:subscribe']"
        >预约</el-button>
      </el-col>

      <!--<el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-s-operation"
          size="mini"
          @click="handleShowDetail"
          :disabled="single"
        >详情</el-button>
      </el-col>-->

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="showList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备名称" align="center" prop="equipmentName" />
      <el-table-column label="设备管理员" align="center" prop="userId" :formatter="adminFormat" />
      <el-table-column label="设备sn编号"  align="center" prop="equipmentSnNumber" />
      <el-table-column label="房间" align="center" prop="labId" :formatter="labFormat" />
      <el-table-column label="设备型号" align="center" prop="equipmentModel" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 增加设备预约对话框-->

    <el-dialog  :visible.sync="open2" width="600px" :title="title" append-to-body>
      <!-- 设备可预约时段的图-->
      <el-divider content-position="center">该设备当日预约情况</el-divider>
      <el-menu   active-text-color="#409EFF"  style="display:flex" >
         <el-menu-item v-for="(item,index) in TimeQues" :key="index" @click="selectQus(index)"   >{{item.label}}</el-menu-item>
      </el-menu>

      <el-col v-for="item in rowtime " :key="item" style="margin-top:3px">
            <el-button v-for="index in 5"  :key="index+5*item" :type="Timemanage[index+5*item+activeIndex*5*rowtime-6]===1?'danger':'primary'" style="font-size:8px;width:100px;height:50px">{{timeQuList[index+5*item+activeIndex*5*rowtime-6]+"—"+timeQuList[index+5*item+1+activeIndex*5*rowtime-6]}}</el-button>
      </el-col>


      <el-form ref="form" :model="form" :rules="rules" label-width="90px" hide-required-asterisk >
        <el-form-item  label="设备" prop="equipmentName" style="width: 300px;margin-bottom: 10px">
          <el-input v-model="form.equipmentName" placeholder="请输入实验室" :disabled="true" style="width: 220px;margin-bottom: 10px"/>
        </el-form-item>

        <el-form-item  label="预约日期:" prop="subscribeDate"  >
          <el-date-picker clearable size="small"
                          v-model="form.subscribeDate"
                          type="date"
                          format="yyyy-MM-dd"
                          value-format="yyyy-MM-dd"
                          placeholder="选择预约日期"
                          @change="subscribeD"
                          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约时间:" prop="subscribeStartTime" style = "margin-bottom: 0px" >
        <el-time-select
          placeholder="起始时间"
          format="HH:mm:ss"
          value-format="HH:mm:ss"
          v-model="form.subscribeStartTime"
          :picker-options="{
          start: '08:00',
          step: Timestep ,
          end: '23:00'
        }">
        </el-time-select>
        <el-time-select
          @change="subscribeGetm"
          placeholder="结束时间"
          value-format="hh:mm:ss"
          v-model="form.subscribeEndTime"
          format="hh:mm:ss"
          :picker-options="{
          start: '08:00',
          step: Timestep ,
          end: '23:00',
          minTime:form.subscribeStartTime

        }">
        </el-time-select>
        </el-form-item>
        <el-form-item >
          <el-input v-model="form.subscribePhone" style="width:350px;margin-top:10px
          ">
               <template slot="prepend" >电话号码</template>
          </el-input >
          <el-checkbox v-model="form.hasHelp" style="margin-top:-40px" @change="subscribeGetm">是否需要人工服务</el-checkbox>

        </el-form-item >

         <el-divider content-position="center">费用总计</el-divider>
           <el-form-item label="费用合计:">
          <el-button  :disabled="true" style="width:220px;" :loading="CostLoading" type="primary">
              费用:{{money}}元
            </el-button>
           </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="warning" @click="subscribeE" >确认预约</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listShow,
  getShow,
  delShow,
  addShow,
  updateShow,
  exportShow,
  listShowSubscribe,
  getWhiteShow
} from '@/api/equipment/show'
import {  addSubscribe, exportSubscribe,subscribeAdd,subscribeGetMoney } from "@/api/equipment/subscribe";
import { getCostType } from "@/api/equipment/cost";
import { listShowlab } from "@/api/lab/showlab";
import { getUserProfile, listUser } from '@/api/system/user'
import { listCost} from '@/api/equipment/cost'

export default {
  name: "Show",
  components: {
  },
  data() {
    return {
      user: {},
      equipmentSubscribe: [],
      //实验室
      lab:[],
      //日期选择只能今天以后的
      pickerOptions0: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7;//如果没有后面的-8.64e7就是不可以选择今天的
        }
      },
      //预约
      subscribesame: [],
      subscribefive: [],
      subscribeOption :[],
      admin: [],
      //状态
      statusOption: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedEquipmentSubscribe: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 设备表格数据
      showList: [],
      // 设备预约表格数据
      equipmentSubscribeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open2: false,
      open3: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        equipmentName: null,
        equipmentAdmin: null,
        equipmentSnNumber: null,
        equipmentStatus: null,
        equipmentModel: null,
        destoryReason: null,
        destoryPerson: null,
        destoryTime: null,
        labId: null,
        labNum: null,
        equipmentSubscribeType: null,
      },
      // 表单参数
      form: {hasHelp:false},
      formS: {},
      // 表单校验
      rules: {
        equipmentAdmin: [
          { required: true, message: "管理员不能为空", trigger: "blur" }
        ],
        equipmentSnNumber: [
          { required: true, message: "SN编号不能为空", trigger: "blur" }
        ],
        equipmentModel: [
          { required: true, message: "型号不能为空", trigger: "blur" }
        ],
        subscribeDate: [
          { required: true, message: "预约日期不能为空", trigger: "blur" }
        ],
        subscribeStartTime: [
          { required: true, message: "预约开始时间不能为空", trigger: "blur" }
        ],
        subscribeEndTime: [
          { required: true, message: "预约结束时间不能为空", trigger: "blur" }
        ],
      },
      //设备预约设备可预约费用
      CostType:[],
      //设备的预约费用
      money:150,
      //当前的设备的ID
      equipmentId:0,
      //当前的用户ID
      userId:0,
      //时段
      //价格加载动画
      CostLoading:false,
      //时间步长
      Timestep:"00:30",
      timeNum:0,
      //被占用时间列表  1 为被占用  0 为空余
      Timemanage:[],
      //时间段集合
      timeQuList:["8:00"],
      //时间端
      TimeQues:[{label:"8:00——13:00",value:0},{label:"13:00——18:00",value:1},{label:"18:00——23:00",value:2}],
      //
      activeIndex:0,
      //
      rowtime:0,
      //
      forecan:false
    };
  },
  created() {
    this.getlab();
    this.getAdmin();
    this.getUser();
    this.getList();
  },
  mounted(){
  },

  methods: {
//时间段选择
  selectQus(index){
    this.activeIndex=index
  },
  //获取设备预约时间步长信息 2
async GetsubcribeTime(){
    try{
        const res=await  listCost({equipmentId:this.equipmentId[0]})
        this.timeNum=res.rows[0].minInterval
        switch(this.timeNum) {
          case 0.25:
            this.Timestep="00:15"
            break;
          case 1:
            this.Timestep="01:00"
            break;
          case 0.75:
            this.Timestep="00:45"
            break;
          default:
            this.Timestep="00:30"
          }

    }catch(err){
        err
    }
  },
  /**获取时间店函数 */
  GettimeQuList(){
      const start="08:00"
      const end="23:00"
      var dateQ=60*this.timeNum
      for(var i=1;i<=this.Timemanage.length;i++)
      {
          var date=i*dateQ
          var hour=String(8+parseInt(date/60))
          var min=String(date-60*parseInt(date/60))
          if(min=="0"){min+="0"}
          this.timeQuList.push(hour+":"+min)
      }

  },
  /**预约*/
  subscribeE(){
    if(this.Flagfor())
    {
    var form= { userId:this.user.userId,
                equipmentId:this.equipmentId[0],
                subscribeDate:this.form.subscribeDate,
                subscribeStartTime:this.form.subscribeStartTime,
                subscribeEndTime:this.form.subscribeEndTime,
                subscribePhone:this.user.phonenumber,
                hasHelp:this.form.hasHelp,
                actualTotalCost:this.money,
                subscribeStatus:((this.forecan||this.form.hasHelp)?2:0)}
                console.log(form)
     this.form.subscribePhone=this.user.phonenumber
      subscribeAdd(form).then(res=>{
        console.log(res)
        this.msgSuccess("预约成功");
         this.open2 = false;
      }).catch(err=>{
        console.log(err)
      })
    }
    },
    /**获取费用*/
  subscribeGetm(){
    this.CostLoading=true
      console.log(form)
      subscribeGetMoney(form).then(res=>{
        console.log(res)
        this.money=res.data.totalCost
        this.CostLoading=fals
      }).catch(err=>{
        console.log(err)
      })
  },
   /**获取设备的收费类型 */
  async getCostTy(){
     var form=this.equipmentId[0]
        getCostType({equipmentId:form}).then(res=>{
            this.CostType=res.data
        }).catch(err=>{
            err
        })
  },
  /** 获取用户信息 */
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
      });
    },
    /** 选择日期后的回调 */
    subscribeD(value){
      this.subscribesame = [];
      this.Timemanage=[]
      for(var i = 0,j = 0;i < this.equipmentSubscribeList.length; i++){
        if(this.equipmentSubscribeList[i].subscribeDate == value && this.equipmentSubscribeList[i].subscribeStatus != 3){
          this.subscribesame[j] = this.equipmentSubscribeList[i];
          j++;
        }
      }
      //转换为时间数组
      //时间起点  时间终点
      const timeStart=new Date("2021/01/01 08:00:00")
      const timeEnd=new Date("2021/01/01 23:00:00")
      //总共的时间间隔数
      var Timelen=this.getInervalHour(timeStart,timeEnd)/60/(this.timeNum)
      for(let i=0;i<Timelen;i++){this.Timemanage[i]=0}

      for(var i=0;i<this.subscribesame.length;i++){
        //计算开始时间差
        let cacudate=new Date("2021/01/01 "+this.subscribesame[i].subscribeStartTime)
        var hour=this.getInervalHour(timeStart,cacudate)
        var stHour=hour/60/(this.timeNum)
        //计算结束时间差
        let cacudate2=new Date("2021/01/01 "+this.subscribesame[i].subscribeEndTime)
        var hour2=this.getInervalHour(timeStart,cacudate2)
        var endHour=hour2/60/(this.timeNum)

        console.log(this.subscribesame.
        length)

        for(let j=stHour;j<endHour;j++)
        {
            this.Timemanage[j]=1
        }
      }

      this.GettimeQuList()
      this.rowtime=1/this.timeNum
    },
    //时间差计算函数 1
    getInervalHour(startDate, endDate) {
            var ms = endDate.getTime() - startDate.getTime();
            if (ms < 0) return 0;
            return Math.floor(ms/1000/60);
    },
    /** 获取实验室信息*/
    getlab(){
      listShowlab().then(res =>{
        for(var i = 0;i < res.rows.length; i++){
          if(res.rows[i].labType == 1){
            this.lab[i] = res.rows[i];
          }
        }
      });
    },
    /**判断时间段是否被预约 */
    Flagfor(){
      for(var i = 0;i < this.subscribesame.length; i++){
            var time1 = String(this.form.subscribeStartTime + ":00");
            var time2 = String(this.form.subscribeEndTime + ":00");
            var time3 = String(this.subscribesame[i].subscribeStartTime);
            var time4 = String(this.subscribesame[i].subscribeEndTime)
            if((time1 > time3 && time1 < time4) || (time2 > time3 && time2 < time4) || (time1 < time3 && time2 > time4)){
              this.msgError("该时间段已经被预约")
              return false
            }
          }
          return true

    },
    /** 详情页 */
    handleShowDetail(row){
      this.reset();
      const equipmentId = row.equipmentId || this.ids
      const loc = "/equipment/show/" + equipmentId
      this.$router.push(loc)
    },

    /** 预约 */
    handleAddEquipmentSubscribetest(row){
      this.reset();
      this.equipmentId = row.equipmentId || this.ids
      //查询是否有资格
        getWhiteShow(this.equipmentId).then(response =>{
          var s = response.data.sysUser;
          let white=0
          for(var i = 0;i < s.length ;i++){
            if(this.user.userId === s[i].userId){
              white=1
              break;
            }
          }
          if(white===1){
            this.forecan=false
          }else{
            this.msgError("你没有权限预约该设备,需向管理员申请")
            this.forecan=true
          }
          //获取设备的时间步长
            this.GetsubcribeTime()
             getShow(this.equipmentId).then(response => {
                this.form = response.data;
                this.equipmentSubscribeList = response.data.equipmentSubscribeList;
                this.title="设备预约"})
           this.open2=true
        });
    },
    /**设备房间 */
    labFormat(row, column){
      const labProp = row[column.property];
      let string = null;
      for(var i = 0;i < this.lab.length;i++){
        if(labProp == this.lab[i].labId){
          string = this.lab[i].labNum;
        }
      }
      return string;
    },
    /** 查询设备列表 */
    getList() {
      this.loading = true;
      listShowSubscribe(this.queryParams).then(response => {
        this.showList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.open2 = false;
      this.open3 = false;
      this.reset();
      this.subscribesame = null;
    },
    // 表单重置
    reset() {
      this.form = {
        equipmentId: null,
        equipmentName: null,
        equipmentAdmin: null,
        equipmentSnNumber: null,
        equipmentStatus: "0",
        equipmentModel: null
      };
      this.equipmentSubscribeList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.equipmentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    //预约的数据更新

    getAdmin(){
      listUser().then(res =>{
        this.admin = res.rows;
      });
    },

    adminFormat(row, column){
      const adminProp = row[column.property];
      let string = null;
      for(var i = 0;i < this.admin.length;i++){
        if(adminProp == this.admin[i].userId){
          string = this.admin[i].userName;
        }
      }
      return string;
    },
  }
};
</script>
<style>
    .el-row .unactive{
      background:rgb(16, 255, 96);
    }
    .el-row .active{
       background:rgb(252, 49, 49);
    }
    .unactive{
      margin-left: -10px;
    }
</style>
