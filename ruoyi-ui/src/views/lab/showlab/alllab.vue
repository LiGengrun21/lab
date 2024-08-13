<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="房间编号" prop="labNum">
        <el-input
          v-model="queryParams.labNum"
          placeholder="请输入房间编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房间位置" prop="labAd">
        <el-input
          v-model="queryParams.labAd"
          placeholder="请输入房间位置"
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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['lab:showlab:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['lab:showlab:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['lab:showlab:remove']"
        >删除</el-button>
      </el-col>
      <!--<el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['lab:showlab:export']"
        >导出</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-s-operation"
          size="mini"
          @click="handleAddLabSubscribe"
          :disabled="single"
        >预约</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-s-operation"
          size="mini"
          @click="handleShowDetail"
          :disabled="single"
        >详情</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="showlabList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="房间编号" align="center" prop="labNum" />
      <el-table-column label="房间位置" align="center" prop="labAd" />
      <el-table-column label="房间管理员" align="center" prop="adminId" :formatter="adminFormat" />
      <el-table-column label="房间类型" align="center" prop="labType" :formatter="typeFormat"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['lab:showlab:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['lab:showlab:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改实验室列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="房间编号" prop="labNum">
          <el-input v-model="form.labNum" placeholder="请输入房间编号" />
        </el-form-item>
        <el-form-item label="房间位置" prop="labAd">
          <el-input v-model="form.labAd" placeholder="请输入房间位置" />
        </el-form-item>

        <el-form-item label="管理员" prop ="admin">
          <el-select v-model="form.adminId">
            <el-option
              v-for="item in admin"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="房间类型" prop="labType" >
          <el-select v-model="form.labType">
            <el-option
              v-for="dict in typeOption"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue">
            </el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加实验室预约对话框 -->
    <!--<el-dialog :title="title" :visible.sync="open2" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="实验室" prop="equipmentName" style="width: 400px">
          <el-input v-model="form.labNum" placeholder="请输入实验室" :disabled="true" />
        </el-form-item>

        <el-form-item label="开始时间" prop="labSubscribeStartTime">
          <el-date-picker clearable size="small"
                          v-model="form.labSubscribeStartTime"
                          type="datetime"
                          format="yyyy-MM-dd HH:mm"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择预约开始时间"
                          :picker-options="pickerOptions0">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="结束时间" prop="labSubscribeEndTime">
          <el-date-picker clearable size="small"
                          v-model="form.labSubscribeEndTime"
                          type="datetime"
                          format="yyyy-MM-dd HH:mm"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择预约结束时间"
                          :picker-options="pickerOptions0">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="预约人" prop="labSubscribePerson">
          <el-input v-model="form.labSubscribePerson" placeholder="请输入预约人" style="width: 200px" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="warning" @click="handleSubscribe">预 约</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>

      <el-divider content-position="center">近期预约记录</el-divider>
      <el-table :data="subscribefive" ref="labSubscribe">
        <el-table-column label="预约开始时间" prop="labSubscribeStartTime"></el-table-column>
        <el-table-column label="预约结束时间" prop="labSubscribeEndTime"></el-table-column>
        <el-table-column label="预约人" prop="labSubscribePerson"></el-table-column>
      </el-table>
    </el-dialog>-->

    <el-dialog :title="title" :visible.sync="open2" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="实验室" prop="labNum" style="width: 400px;margin-bottom: 0px">
          <el-input v-model="form.labNum" placeholder="请输入实验室" :disabled="true" />
        </el-form-item>

        <el-form-item style="margin-bottom: 0px">
          <el-input  type = "hidden" v-model="form.labSubscribePerson" style="margin-bottom: 0px" />
        </el-form-item>

        <el-form-item label="预约日期" prop="labSubscribeDate" style="margin-bottom: 0px" >
          <el-date-picker clearable size="small"
                          v-model="form.labSubscribeDate"
                          type="date"
                          format="yyyy-MM-dd"
                          value-format="yyyy-MM-dd"
                          placeholder="选择预约日期"
                          @change="subscribeD"
                          :picker-options="pickerOptions0">
          </el-date-picker>
        </el-form-item>

        <el-form-item style="margin-bottom: 0px">
          <el-input  type = "hidden" v-model="form.labSubscribePhone" style="margin-bottom: 0px" />
        </el-form-item>

        <el-form-item label="预约时间" prop="labSubscribeStartTime" style="margin-bottom: 0px">
          <el-time-select
            placeholder="起始时间"
            v-model="form.labSubscribeStartTime"
            :picker-options="{
          start: '08:00',
          step: '00:30',
          end: '23:00'
        }">
          </el-time-select>
          <el-time-select
            placeholder="结束时间"
            v-model="form.labSubscribeEndTime"
            :picker-options="{
          start: '08:00',
          step: '00:30',
          end: '23:00',
          minTime:form.labSubscribeStartTime
        }">
          </el-time-select>
        </el-form-item>
      </el-form>

      <el-divider content-position="center">该房间当日预约情况</el-divider>
      <el-table :data="this.subscribesame" ref="labSubscribe">
        <el-table-column label="预约日期" prop="labSubscribeDate"></el-table-column>
        <el-table-column label="预约开始时间" prop="labSubscribeStartTime"></el-table-column>
        <el-table-column label="预约结束时间" prop="labSubscribeEndTime"></el-table-column>
        <el-table-column label="预约人姓名" prop="labSubscribePerson"></el-table-column>
        <el-table-column label="预约人手机号" prop="labSubscribePhone"></el-table-column>
      </el-table>

      <div slot="footer" class="dialog-footer">
        <!--<el-button type="success" @click="handleSelectSubscribe">查看选择日期排班</el-button>-->
        <el-button type="warning" @click="handleSubscribe">预 约</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listShowlab, getShowlab, delShowlab, addShowlab, updateShowlab, exportShowlab ,listShowlabEquipment} from "@/api/lab/showlab";
import { getShow } from '@/api/equipment/show'
import { listSubscribe, getSubscribe, delSubscribe, addSubscribe, updateSubscribe, exportSubscribe } from "@/api/lab/subscribe";
import { getUserProfile, listUser } from '@/api/system/user'

export default {
  name: "Showlab",
  components: {
  },
  data() {
    return {
      user: [],

      pickerOptions0: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7;//如果没有后面的-8.64e7就是不可以选择今天的
        }
      },
      labSubscribeList: [],
      subscribefive: [],
      subscribesame: [],
      typeOption: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 实验室列表表格数据
      showlabList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open2: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        labNum: null,
        labAd: null,
        equipmentNum: null,
        zjutEquipmentList: [],
        labSubscribePhone: [],
      },
      // 表单参数
      form: {},
      form2: {},
      admin: [],
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getAdmin();
    this.getList();
    this.getType();
    this.getUser();
  },
  methods: {
    adminFormat(row, column){
      const adminProp = row[column.property];
      let string = null;
      for(var i = 0;i < this.admin.length;i++){
        if(adminProp == this.admin[i].userId){
          string = this.admin[i].nickName;
        }
      }
      return string;
    },

    getAdmin(){
      listUser().then(res =>{
        this.admin = res.rows;
        console.log(this.admin);
      });
    },
    /** 获取用户信息 */
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
        console.log(this.user);
      });
    },

    /** 预约*/
    handleAddLabSubscribe(row){
      this.reset();
      const labId = row.labId || this.ids
      getShowlab(labId).then(response => {
        this.form = response.data;
        this.labSubscribeList = response.data.labSubscribeList;
        this.open2 = true;
        this.title = "实验室预约";
        console.log(this.labSubscribeList);
      });
    },

    /** 选择日期后的回调 */
    subscribeD(value){
      console.log(value)
      console.log(this.labSubscribeList)
      this.subscribesame = [];
      for(var i = 0,j = 0;i < this.labSubscribeList.length; i++){
        if(this.labSubscribeList[i].labSubscribeDate == value){
          this.subscribesame[j] = this.labSubscribeList[i];
          j++;
        }
      }
      console.log(this.subscribesame);
    },

    /** 提交预定按钮 */
    handleSubscribe() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          var flag = 1;
          this.form.labSubscribeList = this.labSubscribeList;
          console.log(this.subscribesame);
          lab:for(var i = 0;i < this.subscribesame.length; i++){
            console.log(this.subscribesame[i].labSubscribeStartTime);
            console.log(this.form.labSubscribeStartTime + ":00" );
            console.log(this.form.labSubscribeEndTime + ":00" );
            console.log(this.subscribesame[i].labSubscribeEndTime);

            var time1 = String(this.form.labSubscribeStartTime + ":00");
            var time2 = String(this.form.labSubscribeEndTime + ":00");
            var time3 = String(this.subscribesame[i].labSubscribeStartTime);
            var time4 = String(this.subscribesame[i].labSubscribeEndTime)

            if((time1 > time3 && time1 < time4) || (time2 > time3 && time2 < time4)){
              flag = 0;
              break lab;
            }
          }

          if(this.form.labSubscribeStartTime > this.form.labSubscribeEndTime){
            flag = 2;
          }


          console.log(flag);

          if(flag == 1){
            this.form.labSubscribePhone = this.user.phonenumber;
            this.form.labSubscribePerson = this.user.nickName;
            this.form.labSubscribeStatus = 2;
            console.log(this.form);
            addSubscribe(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open2 = false;
              this.getList();
            });

          }
          else
          if(flag == 0){
            this.msgError("该时间段已有预约");
          }

          else
          if(flag == 2){
            this.msgError("开始时间不得大于结束时间");
          }

          else{
            this.msgError("error");
          }

        }
        /*addSubscribe(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open2 = false;
          this.getList();
          });*/
      });
    },

    /** 房间类型*/
    getType(){
      this.getDicts("zjut_lab_type").then(response => {
        this.typeOption = response.data;
        console.log(this.typeOption);
      });
    },

    typeFormat(row, column){
      const typeProp = row[column.property];
      let string = null;
      if(typeProp == 1){
        string = "实验室";
      }
      else{
        string = "会议室";
      }
      return string;
    },
    /** 详情页 */
    handleShowDetail(row){
      this.reset();
      const labId = row.labId || this.ids
      const loc = "/lab/showlab/" + labId
      this.$router.push(loc)
    },

    /** 查询实验室列表列表 */
    getList() {
      this.loading = true;
      listShowlab(this.queryParams).then(response => {
        this.showlabList = response.rows;
        this.total = response.total;
        this.loading = false;
        console.log(this.showlabList);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.open2 = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        labId: null,
        labNum: null,
        labAd: null,
        labAdmin: null,
        equipmentNum: null,
      };
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
      this.ids = selection.map(item => item.labId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加实验室列表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const labId = row.labId || this.ids
      getShowlab(labId).then(response => {
        this.form = response.data;
        console.log(this.form)
        this.open = true;
        this.title = "修改实验室列表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.labId != null) {
            updateShowlab(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addShowlab(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const labIds = row.labId || this.ids;
      this.$confirm('是否确认删除实验室列表编号为"' + labIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delShowlab(labIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有实验室列表数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return exportShowlab(queryParams);
      }).then(response => {
        this.download(response.msg);
      })
    }
  }
};
</script>
