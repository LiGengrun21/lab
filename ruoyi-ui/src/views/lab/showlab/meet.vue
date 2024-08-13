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
      <!--<el-col :span="1.5">
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
      </el-col>-->
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
        <el-form-item label="房间管理员" prop="labAdmin">
          <el-input v-model="form.labAdmin" placeholder="请输入房间管理员" />
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
  </div>
</template>

<script>
import { listShowlab, getShowlab, delShowlab, addShowlab, updateShowlab, exportShowlab ,listShowlabEquipment} from "@/api/lab/showlab";
import { listUser } from '@/api/system/user'

export default {
  name: "Showlab",
  components: {
  },
  data() {
    return {
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
      showlabLists: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        labNum: null,
        labAd: null,
        labType :null,
        equipmentNum: null,
        zjutEquipmentList: [],
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
  },
  methods: {
    getAdmin(){
      listUser().then(res =>{
        this.admin = res.rows;
        console.log(this.admin);
      });
    },

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
        for(var i = 0,j = 0;i < response.rows.length; i++)
        {
          if(response.rows[i].labType == 2) {
            this.showlabLists[j] = response.rows[i];
            j++
          }
        }
        this.showlabList = this.showlabLists
        this.total = this.showlabLists.length;
        this.loading = false;
        console.log(this.showlabList);
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
