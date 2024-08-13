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
      <el-form-item label="维保原因" prop="fixReason">
        <el-input
          v-model="queryParams.fixReason"
          placeholder="请输入维保原因"
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
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['equipment:fix:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fixList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备名称" align="center" prop="zjutEquipment.equipmentName" />
      <el-table-column label="设备管理员" align="center" prop="zjutEquipment.equipmentAdmin" />
      <el-table-column label="设备sn编号" align="center" prop="zjutEquipment.equipmentSnNumber" />
      <el-table-column label="设备型号" align="center" prop="zjutEquipment.equipmentModel" />
      <el-table-column label="维保原因" align="center" prop="fixReason" />
      <el-table-column label="设备状态" align="center"  prop="fixStatus"
      :filters="[{text: '已修复', value:'2'}, {text: '报修中', value: '1'}, {text: '待审核', value: '0'}]"
      :filter-method="filterHandler">
        <template slot-scope="scope">
            <el-button :type="(scope.row.fixStatus=='1'?'primary':(scope.row.fixStatus=='0'?'danger':'success'))">{{(scope.row.fixStatus=='1'?'报修中':(scope.row.fixStatus=='0'?'待审核':"已修复"))}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="附件" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleFile(scope.row)"
          >查看附件</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" class="el-icon-edit"
              @click="fixcheck(scope.row)">保修审核
          </el-button>
          <el-button type="text" class="el-icon-edit" v-if="(scope.row.fixStatus=='1')"
              @click="repairfi(scope.row)">设备修复完成?
          </el-button>
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
   <!--附件对话框-->
    <el-dialog  v-loading="loading" :title="title" :visible.sync="open2" width="800px" append-to-body>
      <el-table :data="this.fixFile" ref="fixFile">
        <el-table-column label="附件名称" prop="fixFileName"></el-table-column>
        <el-table-column label="附件地址" prop="fixFilePath"></el-table-column>
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
        <el-button @click="cancels">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="报修审核" :visible.sync="checkIndex" append-to-body  width="600" v-loading="loading" >
      <el-form label-width="150" label-position="left" :model="form" >
      <el-form-item label="报修设备">
        <el-input type="text" v-model="form.equipmentName"  :placeholder="form.equipmentName"></el-input>
      </el-form-item>
      <el-form-item label="报修设备编号">
          <el-input type="text" v-model="form.equipmentSnNumber"></el-input>
      </el-form-item>
      <el-form-item label="报修原因">
        <el-input type="text" v-model="fixReason"></el-input>
      </el-form-item>
      </el-form>
        <el-button type="primary" @click="repairBy">设备报修通过</el-button>
        <el-button type="warning" @click="repairRefu">设备报修未通过</el-button>
    </el-dialog>
  </div>
</template>

<script>
import { listFix, getFix, delFix, addFix, updateFix, exportFix,confirmFix,refuseFix,finishFix } from "@/api/equipment/fix";
import { getToken } from "@/utils/auth";
import { addFixfile, delFixfile } from '@/api/file/fixfile'

export default {
  name: "Fix",
  components: {
  },
  data() {
    return {
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
      fixFile: [],
      fixFiles: [],
      fixMessage: {},
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
      // 设备维保表格数据
      fixList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open2: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        equipmentId: null,
        fixStartTime: null,
        fixEndTime: null,
        fixReason: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        equipmentId: [
          { required: true, message: "设备id不能为空", trigger: "blur" }
        ],
      },
      //
      checkIndex:false,
      //
      fixReason:"",
      //
      fixId:0
    };
  },
  created() {
    this.getList();
  },
  methods: {
    fixcheck(row){
      console.log(row)
      this.fixId=row.fixId
      this.checkIndex=true
      this.fixReason=row.fixReason
      this.form=row.zjutEquipment

    },
    handleSubmitFile(){
      if(this.flag == 1){
        console.log("----------------");
        console.log(this.fixFiles);
        console.log(this.fixMessage);
        for(var i = 0;i < this.fixFiles.length;i++){
          this.form = {};
          this.form.fixId = this.fixMessage.fixId;
          this.form.fixFileName = this.fixFiles[i].name;
          this.form.fixFilePath = this.fixFiles[i].response.url;
          var data = this.form;
          data = JSON.stringify(data);
          console.log(data);
          addFixfile(data).then(response => {
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
      this.fixFile = null;
      console.log(row);
      this.fixMessage = row;
      this.fixFile = row.fixFileList;
      if(this.fixFile[0].fixFileId == null){
        this.fixFile = null;
      }
      console.log(this.fixFile);
      this.open2 = true;
      this.title = "附件一栏";
      console.log(this.flag);
    },
    //设备保修通过
    repairBy(){
      confirmFix({fixId:this.fixId}).then(res=>{
        this.msgSuccess("保修通过成功")
        this.checkIndex = false;
        this.getList();
      }).catch(err=>{
          this.msgError("设备保修处理失败")
        })
    },
    //设备修复完成
    repairfi(row){
        finishFix({fixId:row.fixId}).then(res=>{
          console.log(res)
        })
    },
    //设备不通过
    repairRefu(){
      console.log(this.fixId)
        refuseFix({fixId:this.fixId}).then(res=>{
          this.msgSuccess("保修保修不通过")
        }).catch(err=>{
          this.msgError("设备保修处理失败")
        })
    },
    handleDownload(row) {
      console.log(row);
      var name = row.fixFileName;
      var url = row.fixFilePath;
      console.log(name);
      console.log(url);
      var suffix = url.substring(url.lastIndexOf("."), url.length);
      const a = document.createElement('a')
      a.setAttribute('download', name + suffix)
      a.setAttribute('target', '_blank')
      a.setAttribute('href', url)
      a.click()
    },
    /** 查询设备维保列表 */
    getList() {
      this.loading = true;
      listFix(this.queryParams).then(response => {
        this.fixList = response.rows;
        this.total = response.total;
        this.loading = false;
        console.log(response);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancels(){
      this.open2 = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        fixId: null,
        equipmentId: null,
        fixStartTime: null,
        fixEndTime: null,
        fixReason: null,
        fixStatus: null,
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
      this.ids = selection.map(item => item.fixId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备维保";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fixId = row.fixId || this.ids
      getFix(fixId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备维保";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fixId != null) {
            updateFix(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFix(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const fixIds = row.fixId || this.ids;
      this.$confirm('是否确认删除设备维保编号为"' + fixIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delFix(fixIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    handleDeleteFile(row) {
      const fixFileIds = row.fixFileId || this.ids;
      this.$confirm('是否确认删除设备报废附件文件编号为"' + fixFileIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delFixfile(fixFileIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },

    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有设备维保数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportFix(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },

    submitUpload() {
      this.$refs.upload.submit();
    },
// 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
// 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.fixFiles = fileList;
      console.log(this.fixFiles);
      this.upload.isUploading = false;
      this.form.filePath = response.url;
      this.msgSuccess(response.msg);
      this.flag = 1;
      console.log(this.flag);
    },

  }
};
</script>
<style lang="scss">
.el-dialog{
  .el-form-item{
    line-height: 35px;
    .el-input{
     margin-top: 10px;
     width: 300px;
   }
   .el-button{
     margin-top: 10px;
   }
  }

}
</style>
