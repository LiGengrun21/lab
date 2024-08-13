<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="插座id" prop="deviceId">
        <el-input
          v-model="queryParams.deviceId"
          placeholder="请输入插座id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="插座电流" prop="deviceCurrent">
        <el-input
          v-model="queryParams.deviceCurrent"
          placeholder="请输入插座电流"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="插座功率" prop="devicePower">
        <el-input
          v-model="queryParams.devicePower"
          placeholder="请输入插座功率"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="插座电压" prop="deviceVoltage">
        <el-input
          v-model="queryParams.deviceVoltage"
          placeholder="请输入插座电压"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="插座开关" prop="deviceStatus">
        <el-select v-model="queryParams.deviceStatus" placeholder="请选择插座开关" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="统计时间" prop="logTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.logTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择统计时间">
        </el-date-picker>
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
          v-hasPermi="['equipment:devicelog:add']"
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
          v-hasPermi="['equipment:devicelog:edit']"
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
          v-hasPermi="['equipment:devicelog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['equipment:devicelog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="devicelogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="插座日志id" align="center" prop="deviceLogId" />
      <el-table-column label="插座id" align="center" prop="deviceId" />
      <el-table-column label="插座电流" align="center" prop="deviceCurrent" />
      <el-table-column label="插座功率" align="center" prop="devicePower" />
      <el-table-column label="插座电压" align="center" prop="deviceVoltage" />
      <el-table-column label="插座开关" align="center" prop="deviceStatus" />
      <el-table-column label="统计时间" align="center" prop="logTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.logTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['equipment:devicelog:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['equipment:devicelog:remove']"
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

    <!-- 添加或修改设备运行统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="插座id" prop="deviceId">
          <el-input v-model="form.deviceId" placeholder="请输入插座id" />
        </el-form-item>
        <el-form-item label="插座电流" prop="deviceCurrent">
          <el-input v-model="form.deviceCurrent" placeholder="请输入插座电流" />
        </el-form-item>
        <el-form-item label="插座功率" prop="devicePower">
          <el-input v-model="form.devicePower" placeholder="请输入插座功率" />
        </el-form-item>
        <el-form-item label="插座电压" prop="deviceVoltage">
          <el-input v-model="form.deviceVoltage" placeholder="请输入插座电压" />
        </el-form-item>
        <el-form-item label="插座开关">
          <el-radio-group v-model="form.deviceStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="统计时间" prop="logTime">
          <el-date-picker clearable size="small"
            v-model="form.logTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择统计时间">
          </el-date-picker>
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
import { listDevicelog, getDevicelog, delDevicelog, addDevicelog, updateDevicelog, exportDevicelog } from "@/api/equipment/devicelog";

export default {
  name: "Devicelog",
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
      // 设备运行统计表格数据
      devicelogList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceId: null,
        deviceCurrent: null,
        devicePower: null,
        deviceVoltage: null,
        deviceStatus: null,
        logTime: null
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
    /** 查询设备运行统计列表 */
    getList() {
      this.loading = true;
      listDevicelog(this.queryParams).then(response => {
        this.devicelogList = response.rows;
        this.total = response.total;
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
        deviceLogId: null,
        deviceId: null,
        deviceCurrent: null,
        devicePower: null,
        deviceVoltage: null,
        deviceStatus: "0",
        logTime: null
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
      this.ids = selection.map(item => item.deviceLogId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备运行统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const deviceLogId = row.deviceLogId || this.ids
      getDevicelog(deviceLogId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备运行统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deviceLogId != null) {
            updateDevicelog(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDevicelog(this.form).then(response => {
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
      const deviceLogIds = row.deviceLogId || this.ids;
      this.$confirm('是否确认删除设备运行统计编号为"' + deviceLogIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDevicelog(deviceLogIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有设备运行统计数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDevicelog(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
