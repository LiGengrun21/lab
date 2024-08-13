<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="98px" label-position="left">
      <el-form-item label="对应课程id" prop="classId">
        <el-input
          v-model="queryParams.classId"
          placeholder="请输入对应课程id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="培训发布人" prop="trainAdder">
        <el-input
          v-model="queryParams.trainAdder"
          placeholder="请输入培训发布人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="培训时间" prop="trainTime">
        <el-date-picker clearable size="small"
                        v-model="queryParams.trainTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择培训时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="培训地点" prop="trainAd">
        <el-input
          v-model="queryParams.trainAd"
          placeholder="请输入培训地点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="培训备注" prop="trainTip">
        <el-input
          v-model="queryParams.trainTip"
          placeholder="请输入培训备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="培训最大人数" prop="trainMaxPerson">
        <el-input
          v-model="queryParams.trainMaxPerson"
          placeholder="请输入培训最大人数"
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
  
  <div class="total" v-for="(item,index) in itemList" :key="index" >
      <classItem v-bind:date="item" ></classItem> 
  </div>
  <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listItem, getItem, delItem, addItem, updateItem, exportItem } from "@/api/train/item";
import { listClass } from '@/api/train/class'
import { getUserProfile } from '@/api/system/user'
import classItem from "./components/classSubscribe.vue"
export default {
  components:{
    classItem
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
      // 表单校验
      rules: {
      },
      user:{},
      roleGroup:{},
      postGroup:{},

    };
  },
 mounted() {
    this.getUser();
    this.getclasses();
    this.getList();
  },

  methods: {
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
      });
    },
    getclasses(){
      listClass().then(responce =>{
        this.classes = responce.rows;
      })
    },
    /** 查询培训课程列表 */
    getList() {
      this.loading = true;
      listItem(this.queryParams).then(response => {
        this.itemList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 表单重置
    reset() {
      this.form = {
        trainId: null,
        classId: null,
        trainAdder: null,
        trainTime: null,
        trainAd: null,
        trainTip: null,
        trainMaxPerson: null
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
    classFormat(row){
      let string = null;
      for(var i = 0;i < this.classes.length;i++){
        if(row == this.classes[i].classId){
          string = this.classes[i].className;
        }
      }
      return string;
    },
  }
};
</script>
<style lang="scss">
.total{
  padding: 10px;
  margin-top: 20px;
  text-align: center;
  align-items: center;
  border-radius: 10px;
  box-shadow: 0px 0px 10px 10px rgba(223, 223, 223, 0.473);
  
}
</style>