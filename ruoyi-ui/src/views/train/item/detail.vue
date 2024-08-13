<template>
  <div class="app-container">

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <!--<el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['train:item:add']"
        >新增</el-button>-->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['train:item:edit']"
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
          v-hasPermi="['train:item:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="trainAndUser" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学员姓名" align="center" prop="nickName" />
      <el-table-column label="对应课程" align="center" prop="classId" :formatter="classFormat" />
      <el-table-column label="学员邮箱" align="center" prop="email" />
      <el-table-column label="学员手机号" align="center" prop="phonenumber" />
      <el-table-column label="学员得分" align="center" prop="userScore" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['train:item:edit']"
          >打分</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['train:item:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改培训对话框 -->
    <!--<el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="培训课程" prop="classId">
          <el-select v-model="form.classId">
            <el-option
              v-for="item in classes"
              :key="item.classId"
              :label="item.className"
              :value="item.classId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="培训发布人" prop="trainAdder">
          <el-input v-model="form.trainAdder" placeholder="请输入培训发布人" />
        </el-form-item>
        <el-form-item label="培训时间" prop="trainTime">
          <el-date-picker clearable size="small"
                          v-model="form.trainTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择培训时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="培训地点" prop="trainAd">
          <el-input v-model="form.trainAd" placeholder="请输入培训地点" />
        </el-form-item>
        <el-form-item label="培训备注" prop="trainTip">
          <el-input v-model="form.trainTip" placeholder="请输入培训备注" />
        </el-form-item>
        <el-form-item label="培训最大人数" prop="trainMaxPerson">
          <el-input v-model="form.trainMaxPerson" placeholder="请输入培训最大人数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>-->

    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户得分" prop="userScore">
          <el-input v-model="form.userScore" placeholder="请输入用户得分" />
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
import { listItem, getItem, delItem, addItem, updateItem, exportItem, getTrainAndUser } from '@/api/train/item'
import { listClass } from '@/api/train/class'
import { addTrainUser, getTrainUser, updateTrainUser } from '@/api/train/trainUser'

export default {
  name: "Item",
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
      // 培训表格数据
      itemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open2: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        classId: null,
        trainAdder: null,
        trainTime: null,
        trainAd: null,
        trainTip: null,
        trainMaxPerson: null
      },
      // 表单参数
      form: {},
      trainAndUser: {},
      // 表单校验
      rules: {
      },
      classes:[],
      Id :0,
    };
  },
  created() {
    const trainId = this.$route.params.trainId
    this.Id = trainId
    console.log(trainId)
    this.getList();
    this.getclasses();
  },
  methods: {
    getclasses(){
      listClass().then(responce =>{
        this.classes = responce.rows;
      })
    },
    /** 查询培训列表 */
    getList() {
      getTrainAndUser(this.Id).then(response => {
        this.trainAndUser = response.rows;
        console.log(this.trainAndUser);
      });
      this.loading = false;
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.open2 = false;
      this.reset();
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
      this.ids = selection.map(item => item.trainId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加培训";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const trainId = row.trainId || this.ids
      console.log(row);
      const userId = row.userId;
      getTrainUser(trainId,userId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改分数";
      });
    },

    handleTrainUser(row){
      const trainId = row.equipmentId || this.ids;
      const loc = "/train/item/" + trainId;
      this.$router.push(loc);
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.trainId != null) {
            updateTrainUser(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTrainUser(this.form).then(response => {
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
      const trainIds = row.trainId || this.ids;
      this.$confirm('是否确认删除培训编号为"' + trainIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delItem(trainIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有培训数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return exportItem(queryParams);
      }).then(response => {
        this.download(response.msg);
      })
    },

    classFormat(row, column){
      const classProp = row[column.property];
      let string = null;
      for(var i = 0;i < this.classes.length;i++){
        if(classProp == this.classes[i].classId){
          string = this.classes[i].className;
        }
      }
      return string;
    },

  }
};
</script>

