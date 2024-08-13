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
      <el-form-item label="设备状态" prop="equipmentStatus">
        <el-select v-model="queryParams.equipmentStatus" placeholder="请选择设备状态" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['equipment:show:edit']"
        >修改</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-s-operation"
          size="mini"
          @click="handleShowDetail"
          :disabled="single"
        >运行详情</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-s-operation"
          size="mini"
          @click="handleDevice"
          :disabled="single"
          v-hasPermi="['device:switch']"
        >插座状态</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="showList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备名称" align="center" prop="equipmentName" />
      <el-table-column label="设备管理员" align="center" prop="userId" :formatter="adminFormat" />
      <el-table-column label="设备sn编号" width="250" align="center" prop="equipmentSnNumber"  />
      <el-table-column label="设备插座编号" width="250" align="center" prop="deviceId"  />
      <el-table-column label="房间" align="center" prop="labId" :formatter="labFormat" />
      <el-table-column label="是否自动断电" align="center" prop="autoOff" :formatter="autoOffFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['equipment:show:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['equipment:show:remove']"
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

    <!-- 添加或修改设备对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备名称" prop="equipmentName">
          <el-input v-model="form.equipmentName" placeholder="请输入设备名称" />
        </el-form-item>

        <el-form-item label="管理员" prop ="admin">
          <el-select v-model="form.userId">
            <el-option
              v-for="item in admin"
              :key="item.userId"
              :label="item.userName"
              :value="item.userId">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="sn编号" prop="equipmentSnNumber">
          <el-input v-model="form.equipmentSnNumber" placeholder="请输入设备sn编号" />
        </el-form-item>

        <el-form-item label="所属房间" prop ="labId">
          <el-select v-model="form.labId">
            <el-option
              v-for="item in lab"
              :key="item.labId"
              :label="item.labNum"
              :value="item.labId">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="设备状态" prop="equipmentStatus">
          <el-select v-model="form.equipmentStatus">
            <el-option
              v-for="dict in statusOption"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="预约状态" prop="equipmentSubscribeType">
          <el-select v-model="form.equipmentSubscribeType">
            <el-option
              v-for="dict in subscribeOption"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="是否自动断电" prop="autoOff">
          <el-select v-model="form.autoOff">
            <el-option
              v-for="dict in this.autoOffOption"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="设备型号" prop="equipmentModel">
          <el-input v-model="form.equipmentModel" placeholder="请输入设备型号" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>




    <el-dialog :title="title" :visible.sync="open5" width="500px" append-to-body>
      <el-form ref="formDevice" :model="formDevice" :rules="rules" label-width="80px">
        <el-form-item label="插座ID">
          <el-input v-model="formDevice.deviceId"></el-input>
        </el-form-item>
        <el-form-item label="开关状态">
          <el-input v-model="this.dstatus"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="success" @click="handleSwitchOn">插 座 通 电</el-button>
        <el-button type="danger" @click="handleSwitchOff">插 座 断 电</el-button>
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
  getDeviceStatus, switchOn, switchOff
} from '@/api/equipment/show'
import { listDestory, getDestory, delDestory, addDestory, updateDestory, exportDestory } from "@/api/equipment/destory";
import { listShowlab, getShowlab, delShowlab, addShowlab, updateShowlab, exportShowlab } from "@/api/lab/showlab";
import { getToken } from "@/utils/auth";
import { addDestoryfile, listDestoryfile } from '@/api/file/destoryfile'
import { addFix, listFix } from '@/api/equipment/fix'
import { addFixfile } from '@/api/file/fixfile'
import { listUser } from '@/api/system/user'

export default {
  name: "Show",
  components: {
  },
  data() {
    return {
      destoryList: [],
      flag: 0,
      destoryFile: [],
      destoryFiles: [],
      destoryMessage: {},
      dstatus: null,
      dId: null,

      fixList: [],
      fixFile: [],
      fixFiles: [],
      fixMessage: {},

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
      //equipmentSubscribe: [],
      //实验室
      lab:[],
      admin: [],
      //日期选择只能今天以后的
      pickerOptions0: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7;//如果没有后面的-8.64e7就是不可以选择今天的
        }
      },
      //预约
      subscribefive: [],
      subscribeOption :[],
      autoOffOption :[],
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
      open4: false,
      open5: false,
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
      form: {},
      formD: {},
      formDF: {},
      formF: {},
      formFF: {},
      formDevice: {},
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
        subscribeStartTime: [
          { required: true, message: "预约开始时间不能为空", trigger: "blur" }
        ],
        subscribeEndTime: [
          { required: true, message: "预约结束时间不能为空", trigger: "blur" }
        ],
        subscribeName: [
          { required: true, message: "预约人不能为空", trigger: "blur" }
        ],
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
    this.getlab();
    this.getAdmin();
    this.getList();
    this.getStatus();
    this.getSubscribeType();
    this.getautoOffOption();
  },
  methods: {
    getAdmin(){
      listUser().then(res =>{
        this.admin = res.rows;
        console.log(this.admin);
      });
    },
    /** 获取实验室信息*/
    getlab(){
      listShowlab().then(res =>{
        this.lab = res.rows;
      });
    },

    /** 详情页 */
    handleShowDetail(row){
      this.reset();
      const equipmentId = row.equipmentId || this.ids
      const loc = "/equipment/show/" + equipmentId
      this.$router.push(loc)
    },

    /** 报废 */
    handleDestory(row){
      this.reset();
      const equipmentId = row.equipmentId || this.ids;
      getShow(equipmentId).then(response => {
        this.form = response.data;
        this.open3 = true;
        this.title = "报废设备";
        console.log(this.form);
      });
    },

    /** 维保 */
    handleFix(row){
      this.reset();
      const equipmentId = row.equipmentId || this.ids;
      getShow(equipmentId).then(response =>{
        this.form = response.data;
        this.open4 = true;
        this.title = "添加维保";
        console.log(this.form);
      });
    },

    handleDevice(row){
      const equipmentId = row.equipmentId || this.ids;
      getShow(equipmentId).then(response =>{
        this.formDevice = response.data;
        console.log(response.data)
        var deviceId = response.data.deviceId;
        getDeviceStatus(deviceId).then(response=>{
          this.title = "插座状态";
          this.open5 = true;
          this.dstatus = response.rows[0][0].value;
          console.log(response.rows[0][0]);
        });
      });
    },

    handleSwitchOn() {
      this.$refs["formDevice"].validate(valid => {
        if (valid) {
          console.log(this.formDevice);
          var deviceId = this.formDevice.deviceId
          switchOn(deviceId).then(response=>{
            this.msgSuccess("通电成功");
            this.open5 = false;
          });
        }
      });
    },

    handleSwitchOff() {
      this.$refs["formDevice"].validate(valid => {
        if (valid) {
          console.log(this.formDevice);
          var deviceId = this.formDevice.deviceId
          switchOff(deviceId).then(response=>{
            this.msgSuccess("断电成功");
            this.open5 = false;
          });
        }
      });
    },

    /** 设备状态 */
    getStatus(){
      this.getDicts("zjut_equipment_status").then(response => {
        this.statusOption = response.data;
      });
    },


    /** 是否可预约*/
    getSubscribeType(){
      this.getDicts("zjut_equipment_subscribe_type").then(response =>{
        this.subscribeOption = response.data;
      });
    },

    getautoOffOption(){
      this.getDicts("device_autooff").then(response =>{
        this.autoOffOption = response.data;
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

    /** 设备状态*/
    statusFormat(row, column){
      const equipmentProp = row[column.property];
      let string = null;
      if(equipmentProp == 0){
        string = "正常";
      }
      else
      if(equipmentProp == 1){
        string = "维保";
      }
      else{
        string = "损坏";
      }
      return string;
    },

    autoOffFormat(row, column){
      const equipmentProp = row[column.property];
      let string = null;
      if(equipmentProp == 0){
        string = "不可自动断电";
      }
      else
      if(equipmentProp == 1){
        string = "可以自动断电";
      }
      return string;
    },

    subscribeFormat(row, column){
      const equipmentProp = row[column.property];
      let string = null;
      if(equipmentProp == 0){
        string = "可预约";
      }
      else {
        string = "不可预约";
      }
      return string;
    },
    /** 查询设备列表 */
    getList() {
      this.loading = true;
      listShow(this.queryParams).then(response => {
        this.showList = response.rows;
        this.total = response.total;
        this.loading = false;
        console.log(this.showList);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.open2 = false;
      this.open3 = false;
      this.open5 = false;
      this.reset();
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      console.log(row)
      this.reset();
      const equipmentId = row.equipmentId || this.ids
      getShow(equipmentId).then(response => {
        console.log(response);
        this.form = response.data;
        this.equipmentSubscribeList = response.data.equipmentSubscribeList;
        this.open = true;
        this.title = "修改设备";
        console.log(this.equipmentSubscribeList);
        this.equipmentSubscribeList = this.equipmentSubscribeList.reverse();
        console.log(this.equipmentSubscribeList);
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.equipmentSubscribeList = this.equipmentSubscribeList;
          if (this.form.equipmentId != null) {
            updateShow(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.open2 = false;
              this.getList();
            });

          } else {
            addShow(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.open2 = false;
              this.getList();
            });
          }
        }
      });
    },


    /** 报废提交按钮 */
    /**handleAddDestory(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.equipmentStatus != "2") {
            //console.log(this.form)
            this.formD.equipmentId = this.form.equipmentId;
            this.formD.destoryPerson = this.form.destoryPerson;
            this.formD.destoryReason = this.form.destoryReason;
            this.formD.destoryTime = this.form.destoryTime;
            this.form.equipmentStatus = "2";
            this.form.equipmentSubscribeType = "1";
            console.log(this.formD)
            addDestory(this.formD).then(response => {
              this.msgSuccess("报废成功")
              this.getList();
            }).then(res => {
              if(this.flag == 1) {
                listDestory(this.queryParams).then(response => {
                  this.destoryList = response.rows;
                  this.total = response.total;
                  this.loading = false;
                  console.log(this.destoryFiles);
                  console.log(this.destoryList);
                  console.log(this.destoryList[0].destoryId);
                  for (var i = 0; i < this.destoryFiles.length; i++) {
                    this.formDF = {};
                    this.formDF.destoryId = this.destoryList[0].destoryId;
                    this.formDF.destoryFileName = this.destoryFiles[i].name;
                    this.formDF.destoryFilePath = this.destoryFiles[i].response.url;
                    var data = this.formDF;
                    data = JSON.stringify(data);
                    console.log(data);
                    addDestoryfile(data).then(response => {
                      console.log("新增成功")
                    });
                  }
                });
              }
              this.open3 = false;
                updateShow(this.form).then(response => {
                console.log("状态修改成功")
              });
            });
          } else {
            this.msgError("该设备已经报废")
          }
        }
      });
      this.getList();
      this.upload.fileList = [];
    },*/

    /** 维保提交按钮 */
    handleAddFix(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          console.log(this.form)
          this.formF.equipmentId = this.form.equipmentId;
          this.formF.fixReason = this.form.fixReason;
          this.formF.fixStartTime = this.form.fixStartTime;
          this.formF.fixEndTime = this.form.fixEndTime;
          this.form.equipmentStatus = "1";
          this.form.equipmentSubscribeType = "1";
          console.log(this.formF)
          addFix(this.formF).then(response => {
            this.msgSuccess("添加维保成功")
            this.getList();
          }).then(res => {
            if(this.flag == 1) {
              listFix(this.queryParams).then(response => {
                this.fixList = response.rows;
                this.total = response.total;
                this.loading = false;
                console.log(this.fixFiles);
                console.log(this.fixList);
                console.log(this.fixList[0].fixId);
                for (var i = 0; i < this.fixFiles.length; i++) {
                  this.formFF = {};
                  this.formFF.fixId = this.fixList[0].fixId;
                  this.formFF.fixFileName = this.fixFiles[i].name;
                  this.formFF.fixFilePath = this.fixFiles[i].response.url;
                  var data = this.formFF;
                  data = JSON.stringify(data);
                  console.log(data);
                  addFixfile(data).then(response => {
                    console.log("新增成功")
                  });
                }
              });
            }
            this.open4 = false;
            updateShow(this.form).then(response => {
              console.log("状态修改成功")
            });
          });
        }
      });
      this.getList();
      this.upload.fileList = [];
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const equipmentIds = row.equipmentId || this.ids;
      this.$confirm('是否确认删除设备编号为"' + equipmentIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delShow(equipmentIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },

    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有设备数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return exportShow(queryParams);
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
    handleDestoryFileSuccess(response, file, fileList) {
      this.destoryFiles = fileList;
      console.log(this.destoryFiles);
      this.upload.isUploading = false;
      this.form.filePath = response.url;
      this.flag = 1;
      console.log(this.flag);
    },

    handleFixFileSuccess(response, file, fileList) {
      this.fixFiles = fileList;
      console.log(this.fixFiles);
      this.upload.isUploading = false;
      this.form.filePath = response.url;
      this.flag = 1;
      console.log(this.flag);
    },
  }
};
</script>
