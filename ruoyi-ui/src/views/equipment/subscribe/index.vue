<!--
  设备预约
-->
<template>
  <div class="app-container">
    <div class="flow" v-show="flow_open">
      <el-steps :active="3">
            <el-step title="步骤 1" icon="el-icon-edit">描述一</el-step>
            <el-step title="步骤 2" icon="el-icon-upload">描述二</el-step>
            <el-step title="步骤 3" icon="el-icon-picture">描述三</el-step>
            <el-step title="步骤 4" icon="el-icon-picture">描述四</el-step>
        </el-steps>
  </div>
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
      <el-form-item label="开始时间" prop="subscribeStartTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.subscribeStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择预约开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="subscribeEndTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.subscribeEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择预约结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约人" prop="subscribeName">
        <el-input
          v-model="queryParams.subscribeName"
          placeholder="请输入预约人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约状态" prop="subscribeStatus">
        <el-input
          v-model="queryParams.subscribeStatus"
          placeholder="请输入预约状态"
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
      <!--<el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['equipment:subscribe:add']"
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
          v-hasPermi="['equipment:subscribe:edit']"
        >修改</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['equipment:subscribe:remove']"
        >删除</el-button>
      </el-col>
      <!--<el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['equipment:subscribe:export']"
        >导出</el-button>
      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="subscribeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

      <el-table-column label="设备名称" align="center" prop="equipmentName" />
      <el-table-column label="预约日期" align="center" prop="subscribeDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.subscribeDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约开始时间" align="center" prop="subscribeStartTime" width="180"></el-table-column>
      <el-table-column label="预约结束时间" align="center" prop="subscribeEndTime" width="180"></el-table-column>
      <el-table-column label="预约人" align="center" prop="subscribeName" />
      <el-table-column label="预约手机号" align="center" prop="subscribePhone" />
      <el-table-column label="预约状态" align="center" prop="subscribeStatus"   >
        <template slot-scope="scope" >
          <span style="color:#FF7F50" v-if="scope.row.subscribeStatus== 0">预约中</span>
          <span style="color:#7CFC00" v-if="scope.row.subscribeStatus== 1">预约完成</span>
          <span style="color:#1E90FF" v-if="scope.row.subscribeStatus== 2">待审核</span>
          <span style="color:#DC143C" v-if="scope.row.subscribeStatus== 3">审核未通过</span>
        </template>

      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['equipment:subscribe:remove']"
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

    <!-- 添加或修改设备预约对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备名称" prop="equipmentName">
          <el-select v-model="form.equipmentName" placeholder="请选择设备名称">
            <el-option
              v-for="item in equipment"
              :key="item.equipmentSubscribeId"
              :label="item.equipmentName"
              :value="item.equipmentName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" prop="subscribeStartTime">
          <el-date-picker clearable size="small"
            v-model="form.subscribeStartTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择预约开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="subscribeEndTime">
          <el-date-picker clearable size="small"
            v-model="form.subscribeEndTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择预约结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约人" prop="subscribeName">
          <el-input v-model="form.subscribeName" placeholder="请输入预约人" />
        </el-form-item>
        <el-form-item label="预约状态" prop="subscribeStatus">
          <el-input v-model="form.subscribeStatus" placeholder="请输入预约状态" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listSubscribe,
  getSubscribe,
  delSubscribe,
  addSubscribe,
  updateSubscribe,
  exportSubscribe,
  listMySubscribe
} from '@/api/equipment/subscribe'
import {listShow} from '@/api/equipment/show'

export default {
  name: "Subscribe",
  components: {
  },
  data() {
    return {
      //设备数组
      equipment: [],
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
      // 设备预约表格数据
      subscribeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        equipmentId: null,
        equipmentName: null,
        subscribeStartTime: null,
        subscribeEndTime: null,
        subscribeName: null,
        subscribeStatus: null,
        subscribeDate: null,
        subscribePhone: null,
      },
      // 表单参数
      form: {},
      formS: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getequip();
  },
  methods: {
    /** 预约状态 */
    subscribeFormatter(row, column){
      const equipmentProp = row[column.property];
      let string = null;
      if(equipmentProp == 0){
        string = "预约中";
      }
      else if(equipmentProp == 1){
        string = "预约完成";
      }
      else if(equipmentProp == 2){
        string = "待审核";
      }
      else if(equipmentProp == 3){
        string = "审核未通过";
      }
      return string;
    },

    /** 传递设备列表 */
    getequip() {
      listShow().then(res =>{
        this.equipment = res.rows;
      })
    },
    /** 查询设备预约列表 */
    getList() {
      this.loading = true;
      listSubscribe(this.queryParams).then(response => {
        this.subscribeList = response.rows;
        this.total = response.total;
        console.log(this.subscribeList)
        var mydate = this.getNowDate();
        mydate = String(mydate);
        console.log(mydate);
        for(var i = 0;i<this.subscribeList.length;i++){
          if(mydate > String(this.subscribeList[i].subscribeDate + " " + this.subscribeList[i].subscribeEndTime)){
            this.subscribeList[i].subscribeStatus = 1;
            this.formS = this.subscribeList[i];
            updateSubscribe(this.formS).then(response => {

            });
          }
        }
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        equipmentSubscribeId: null,
        equipmentId: null,
        equipmentName: null,
        subscribeStartTime: null,
        subscribeEndTime: null,
        subscribeName: null,
        subscribeStatus: null
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
      this.ids = selection.map(item => item.equipmentSubscribeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备预约";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const equipmentSubscribeId = row.equipmentSubscribeId || this.ids
      getSubscribe(equipmentSubscribeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备预约";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.equipmentSubscribeId != null) {
            updateSubscribe(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSubscribe(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
      console.log(this.form.equipmentName)

    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const equipmentSubscribeIds = row.equipmentSubscribeId || this.ids;
      this.$confirm('是否确认删除设备预约编号为"' + equipmentSubscribeIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSubscribe(equipmentSubscribeIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有设备预约数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSubscribe(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },

    /** 获取当前时间*/
    getNowFormatDate() {
      var date = new Date();
      var seperator1 = "-";
      var seperator2 = ":";
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
      return currentdate;
    },


    getNowDate() {
      var date = new Date();
      var sign1 = "-";
      var sign2 = ":";
      var year = date.getFullYear() // 年
      var month = date.getMonth() + 1; // 月
      var day  = date.getDate(); // 日
      var hour = date.getHours(); // 时
      var minutes = date.getMinutes(); // 分
      var seconds = date.getSeconds() //秒
      // 给一位数数据前面加 “0”
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (day >= 0 && day <= 9) {
        day = "0" + day;
     }
      if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
      }
      if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
      }
      if (seconds >= 0 && seconds <= 9) {
      seconds = "0" + seconds;
      }
      var currentdate = year + sign1 + month + sign1 + day + " " + hour + sign2 + minutes + sign2 + seconds;
      return currentdate;
    }
  }
};
</script>
