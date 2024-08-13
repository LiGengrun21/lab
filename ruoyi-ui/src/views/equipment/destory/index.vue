<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备id" prop="equipmentId">
        <el-input
          v-model="queryParams.equipmentId"
          placeholder="请输入设备id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报废原因" prop="destoryReason">
        <el-input
          v-model="queryParams.destoryReason"
          placeholder="请输入报废原因"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人" prop="destoryPerson">
        <el-input
          v-model="queryParams.destoryPerson"
          placeholder="请输入报废申请人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报废日期" prop="destoryTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.destoryTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择报废日期">
        </el-date-picker>
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
          v-hasPermi="['equipment:destory:add']"
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
          v-hasPermi="['equipment:destory:edit']"
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
          v-hasPermi="['equipment:destory:remove']"
        >删除</el-button>
      </el-col>
      <!--<el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['equipment:destory:export']"
        >导出</el-button>
      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="destoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备名称" align="center" prop="zjutEquipment.equipmentName" />
      <el-table-column label="设备管理员" align="center" prop="zjutEquipment.equipmentAdmin" />
      <el-table-column label="设备sn编号" align="center" prop="zjutEquipment.equipmentSnNumber" />
      <el-table-column label="设备型号" align="center" prop="zjutEquipment.equipmentModel" />
      <el-table-column label="报废原因" align="center" prop="destoryReason" />
      <el-table-column label="报废申请人" align="center" prop="destoryPerson" />
      <el-table-column label="报废日期" align="center" prop="destoryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.destoryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="附件" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleFile(scope.row)"
          >查看附件</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--附件对话框-->
    <el-dialog  v-loading="loading" :title="title" :visible.sync="open2" width="800px" append-to-body>
      <el-table :data="this.destoryFile" ref="destoryFile">
        <el-table-column label="附件名称" prop="destoryFileName"></el-table-column>
        <el-table-column label="附件地址" prop="destoryFilePath"></el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleDownload(scope.row)"
            >附件下载</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDeleteFile(scope.row)"
              v-hasPermi="['equipment:show:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div style="margin-right: 60px ; font-family: 微软雅黑 ; color: #13ce66">上传附件</div>
      <el-upload
        ref="upload"
        :limit="5"
        accept=".jpg, .png, .pdf, .docx, .xlsx"
        :action="upload.url"
        :headers="upload.headers"
        :file-list="upload.fileList"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false">
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" :loading="upload.isUploading" @click="submitUpload">确认上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png/pdf/docx/xlsx文件</div>
      </el-upload>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSubmitFile">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改设备报废对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备id" prop="equipmentId">
          <el-input v-model="form.equipmentId" placeholder="请输入设备id" />
        </el-form-item>
        <el-form-item label="报废原因" prop="destoryReason">
          <el-input v-model="form.destoryReason" placeholder="请输入报废原因" />
        </el-form-item>
        <el-form-item label="报废申请人" prop="destoryPerson">
          <el-input v-model="form.destoryPerson" placeholder="请输入报废申请人" />
        </el-form-item>
        <el-form-item label="报废日期" prop="destoryTime">
          <el-date-picker clearable size="small"
            v-model="form.destoryTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择报废日期">
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
import { listDestory, getDestory, delDestory, addDestory, updateDestory, exportDestory } from "@/api/equipment/destory";
import { addDestoryfile, delDestoryfile, updateDestoryfile } from '@/api/file/destoryfile'
import { getToken } from "@/utils/auth";

export default {
  name: "Destory",
  components: {
  },
  data() {
    return {

      flag: 0,

      upload: {
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/common/upload",
        // 上传的文件列表
        fileList: []
      },
      //附件数据
      destoryFile: [],
      destoryFiles: [],
      destoryMessage: {},
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
      // 设备报废表格数据
      destoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open2: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        equipmentId: null,
        destoryReason: null,
        destoryPerson: null,
        destoryTime: null,
        equipmentName: null,
        equipmentAdmin: null,
        equipmentSnNumber: null,
        equipmentModel: null,
        equipmentStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        destoryReason: [
          { required: true, message: "报废原因不能为空", trigger: "blur" }
        ],
        destoryPerson: [
          { required: true, message: "报废申请人不能为空", trigger: "blur" }
        ],
        destoryTime: [
          { required: true, message: "报废日期不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleSubmitFile(){
        if(this.flag == 1){
        console.log("----------------");
        console.log(this.destoryFiles);
        console.log(this.destoryMessage);
        for(var i = 0;i < this.destoryFiles.length;i++){
          this.form = {};
          this.form.destoryId = this.destoryMessage.destoryId;
          this.form.destoryFileName = this.destoryFiles[i].name;
          this.form.destoryFilePath = this.destoryFiles[i].response.url;
          var data = this.form;
          data = JSON.stringify(data);
         console.log(data);
          addDestoryfile(data).then(response => {
            this.msgSuccess("新增成功");
            this.getList();
         });
        }
        this.open2 = false;
        this.flag = 0;
        }
        else{
          this.open2 = false;
        }
    },

    //附件查看
    handleFile(row) {
      this.destoryFile = null;
      console.log(row);
      this.destoryMessage = row;
      this.destoryFile = row.destoryFileList;
      if(this.destoryFile[0].destoryFileId == null){
        this.destoryFile = null;
      }
      console.log(this.destoryFile);
      this.open2 = true;
      this.title = "附件一栏";
      console.log(this.flag);
    },

    handleDownload(row) {
      console.log(row);
      var name = row.destoryFileName;
      var url = row.destoryFilePath;
      console.log(name);
      console.log(url);
      var suffix = url.substring(url.lastIndexOf("."), url.length);
      const a = document.createElement('a')
      a.setAttribute('download', name + suffix)
      a.setAttribute('target', '_blank')
      a.setAttribute('href', url)
      a.click()
    },

    /** 查询设备报废列表 */
    getList() {
      this.loading = true;
      listDestory(this.queryParams).then(response => {
        this.destoryList = response.rows;
        this.total = response.total;
        this.loading = false;
        console.log(response);
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
        destoryId: null,
        equipmentId: null,
        destoryReason: null,
        destoryPerson: null,
        destoryTime: null,
        equipmentName: null,
        equipmentAdmin: null,
        equipmentSnNumber: null,
        equipmentModel: null,
        equipmentStatus: null
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
      this.ids = selection.map(item => item.destoryId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备报废";
      this.upload.fileList = [];
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const destoryId = row.destoryId || this.ids
      getDestory(destoryId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备报废";
      });
      this.upload.fileList = [{ name: this.form.fileName, url: this.form.filePath }];
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.destoryId != null) {
            updateDestory(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDestory(this.form).then(response => {
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
      const destoryIds = row.destoryId || this.ids;
      this.$confirm('是否确认删除设备报废编号为"' + destoryIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDestory(destoryIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
          this.open2 = false;
        })
    },

    handleDeleteFile(row) {
      const destoryFileIds = row.destoryFileId || this.ids;
      this.$confirm('是否确认删除设备报废附件文件编号为"' + destoryFileIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delDestoryfile(destoryFileIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },

    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有设备报废数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDestory(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },

// 文件提交处理
    submitUpload() {
      this.$refs.upload.submit();
    },
// 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
// 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.destoryFiles = fileList;
      console.log(this.destoryFiles);
      this.upload.isUploading = false;
      this.form.filePath = response.url;
      this.msgSuccess(response.msg);
      this.flag = 1;
      console.log(this.flag);
    },
  }
};
</script>
