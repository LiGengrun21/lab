<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="实验室id" prop="labId">
        <el-input
          v-model="queryParams.labId"
          placeholder="请输入实验室id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="labSubscribeStartTime">
        <el-date-picker clearable size="small"
                        v-model="queryParams.labSubscribeStartTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择预约开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="labSubscribeEndTime">
        <el-date-picker clearable size="small"
                        v-model="queryParams.labSubscribeEndTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择预约结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约人" prop="labSubscribePerson">
        <el-input
          v-model="queryParams.labSubscribePerson"
          placeholder="请输入实验室预约人"
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
          v-hasPermi="['lab:subscribe:add']"
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
          v-hasPermi="['lab:subscribe:edit']"
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
          v-hasPermi="['lab:subscribe:remove']"
        >删除</el-button>
      </el-col>
      <!--<el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['lab:subscribe:export']"
        >导出</el-button>
      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="subscribeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="实验室编号" align="center" prop="zjutLab.labNum" />
      <el-table-column label="预约日期" align="center" prop="labSubscribeDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.labSubscribeDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约开始时间" align="center" prop="labSubscribeStartTime" width="180"></el-table-column>
      <el-table-column label="预约结束时间" align="center" prop="labSubscribeEndTime" width="180"></el-table-column>
      <el-table-column label="实验室预约人" align="center" prop="labSubscribePerson" />
      <el-table-column label="预约人手机号" align="center" prop="labSubscribePhone" />
      <el-table-column label="预约状态" align="center" prop="labSubscribeStatus" :formatter="subscribeFormatter"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleAccept(scope.row)"
            v-hasPermi="['lab:subscribe:edit']"
          >通过</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleFail(scope.row)"
            v-hasPermi="['lab:subscribe:remove']"
          >拒绝</el-button>
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

    <!-- 添加或修改实验室预约对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="实验室id" prop="labId">
          <el-input v-model="form.labId" placeholder="请输入实验室id" />
        </el-form-item>
        <el-form-item label="预约开始时间" prop="labSubscribeStartTime">
          <el-date-picker clearable size="small"
                          v-model="form.labSubscribeStartTime"
                          type="date"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择预约开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约结束时间" prop="labSubscribeEndTime">
          <el-date-picker clearable size="small"
                          v-model="form.labSubscribeEndTime"
                          type="date"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择预约结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实验室预约人" prop="labSubscribePerson">
          <el-input v-model="form.labSubscribePerson" placeholder="请输入实验室预约人" />
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
  listlabSubscribe,
  getSubscribe,
  delSubscribe,
  addSubscribe,
  updateSubscribe,
  exportSubscribe,
  listlabAuditSubscribe
} from '@/api/lab/subscribe'
import { listShowlab, getShowlab, delShowlab, addShowlab, updateShowlab, exportShowlab ,listShowlabEquipment} from "@/api/lab/showlab";
import { listAuditSubscribe } from '@/api/equipment/subscribe'

export default {
  name: "Subscribe",
  components: {
  },
  data() {
    return {
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
      // 实验室预约表格数据
      subscribeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        labId: null,
        labSubscribeDate: null,
        labSubscribeStartTime: null,
        labSubscribeEndTime: null,
        labSubscribePerson: null,
        labSubscribeStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleAccept(row) {
      this.reset();
      const labSubscribeId = row.labSubscribeId || this.ids
      getSubscribe(labSubscribeId).then(response => {
        this.form = response.data;
        console.log(this.form);
        this.form.labSubscribeStatus = 0;
        updateSubscribe(this.form).then(response => {
          this.msgSuccess("审核通过成功");
          this.getList();
        });
      });
    },

    handleFail(row) {
      this.reset();
      const labSubscribeId = row.labSubscribeId || this.ids
      getSubscribe(labSubscribeId).then(response => {
        this.form = response.data;
        console.log(this.form);
        this.form.labSubscribeStatus = 3;
        updateSubscribe(this.form).then(response => {
          this.msgSuccess("审核通过成功");
          this.getList();
        });
      });
    },
    /** 预约状态 */
    subscribeFormatter(row, column){
      const equipmentProp = row[column.property];
      let string = null;
      if(equipmentProp == 0){
        string = "预约中";
      }
      else if(equipmentProp == 1) {
        string = "预约完成";
      }
      else if(equipmentProp == 2) {
        string = "待审核"
      }
      else if(equipmentProp == 3){
        string = "审核未通过";
      }
      return string;
    },

    /** 查询实验室预约列表 */
    getList() {
      this.loading = true;
      listlabAuditSubscribe(this.queryParams).then(response => {
        this.subscribeList = response.rows;
        console.log(this.subscribeList);
        this.total = response.total;
        var mydate = this.getNowDate();
        mydate = String(mydate);
        console.log(mydate);
        for(var i = 0;i<this.subscribeList.length;i++){
          if(mydate > String(this.subscribeList[i].labSubscribeDate + " " + this.subscribeList[i].labSubscribeEndTime)){
            console.log(this.subscribeList[i].labSubscribeDate + " " + this.subscribeList[i].labSubscribeEndTime);
            this.subscribeList[i].labSubscribeStatus = 1;
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
        labSubscribeId: null,
        labId: null,
        labSubscribeStartTime: null,
        labSubscribeEndTime: null,
        labSubscribePerson: null,
        labSubscribeStatus: null,
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
      this.ids = selection.map(item => item.labSubscribeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加实验室预约";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const labSubscribeId = row.labSubscribeId || this.ids
      getSubscribe(labSubscribeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改实验室预约";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.labSubscribeId != null) {
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
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const labSubscribeIds = row.labSubscribeId || this.ids;
      this.$confirm('是否确认删除实验室预约编号为"' + labSubscribeIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delSubscribe(labSubscribeIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有实验室预约数据项?', "警告", {
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
