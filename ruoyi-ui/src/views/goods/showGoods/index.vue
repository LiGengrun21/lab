<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="耗材编号" prop="goodsNum">
        <el-input
          v-model="queryParams.goodsNum"
          placeholder="请输入耗材编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="耗材数量" prop="goodsQuantity">
        <el-input
          v-model="queryParams.goodsQuantity"
          placeholder="请输入耗材数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预警数量" prop="goodsWarning">
        <el-input
          v-model="queryParams.goodsWarning"
          placeholder="请输入耗材预警数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="耗材单位" prop="goodsUnit">
        <el-input
          v-model="queryParams.goodsUnit"
          placeholder="请输入耗材单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="存储位置" prop="goodsAd">
        <el-input
          v-model="queryParams.goodsAd"
          placeholder="请输入耗材存储位置"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="耗材类型" prop="goodsType">
        <el-select v-model="queryParams.goodsType" placeholder="请选择耗材类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
          v-hasPermi="['goods:showGoods:add']"
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
          v-hasPermi="['goods:showGoods:edit']"
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
          v-hasPermi="['goods:showGoods:remove']"
        >删除</el-button>
      </el-col>
      <!--<el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['goods:showGoods:export']"
        >导出</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-puls"
          size="mini"
          :disabled="single"
          @click="handleAddGoods"
        >入库</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-puls"
          size="mini"
          :disabled="single"
          @click="handleTakeGoods"
        >领用</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="showGoodsList" @selection-change="handleSelectionChange" :cell-style="warningStyle">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="耗材编号" align="center" prop="goodsNum" />
      <el-table-column label="耗材名称" align="center" prop="goodsName" />
      <el-table-column label="耗材数量" align="center" prop="goodsQuantity" />
      <el-table-column label="耗材单位" align="center" prop="goodsUnit" />
      <el-table-column label="耗材存储位置" align="center" prop="goodsAd" />
      <el-table-column label="耗材预警数量" align="center" prop="goodsWarning" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['goods:showGoods:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['goods:showGoods:remove']"
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

    <!-- 添加或修改耗材列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="耗材编号" prop="goodsNum">
          <el-input v-model="form.goodsNum" placeholder="请输入耗材编号" />
        </el-form-item>
        <el-form-item label="耗材名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入耗材名称" />
        </el-form-item>
        <el-form-item label="耗材数量" prop="goodsQuantity">
          <el-input v-model="form.goodsQuantity" placeholder="请输入耗材数量" />
        </el-form-item>
        <el-form-item label="耗材预警数量" prop="goodsWarning">
          <el-input v-model="form.goodsWarning" placeholder="请输入耗材预警数量" />
        </el-form-item>
        <el-form-item label="耗材单位" prop="goodsUnit">
          <el-input v-model="form.goodsUnit" placeholder="请输入耗材单位" />
        </el-form-item>
        <el-form-item label="耗材存储位置" prop="goodsAd">
          <el-input v-model="form.goodsAd" placeholder="请输入耗材存储位置" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 入库耗材对话框 -->
    <el-dialog :title="title" :visible.sync="open2" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="耗材名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入耗材名称" :disabled="true" />
        </el-form-item>
        <el-form-item label="入库数量" prop="goodsQuantitys">
          <el-input v-model="form.goodsQuantitys" placeholder="请输入入库数量" />
        </el-form-item>
        <el-form-item label="耗材单位" prop="goodsUnit">
          <el-input v-model="form.goodsUnit" placeholder="请输入耗材单位" :disabled="true" />
        </el-form-item>
        <el-form-item label="备注" prop="goodsLogMassage">
          <el-input v-model="form.goodsLogMassage" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitAdd">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--耗材领用对话框-->
    <el-dialog :title="title" :visible.sync="open3" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="耗材名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入耗材名称" :disabled="true" />
        </el-form-item>
        <el-form-item label="领用数量" prop="goodsQuantitys">
          <el-input v-model="form.goodsQuantitys" placeholder="请输入领用数量" />
        </el-form-item>
        <el-form-item label="耗材单位" prop="goodsUnit">
          <el-input v-model="form.goodsUnit" placeholder="请输入耗材单位" :disabled="true" />
        </el-form-item>
        <el-form-item label="备注" prop="goodsLogMassage">
          <el-input v-model="form.goodsLogMassage" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitTake">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listShowGoods, getShowGoods, delShowGoods, addGoods,addShowGoods, updateShowGoods, exportShowGoods } from "@/api/goods/showGoods";
import { getUserProfile } from '@/api/system/user'
import { addLog } from '@/api/goods/log'

export default {
  name: "ShowGoods",
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
      // 耗材列表表格数据
      showGoodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open2: false,
      open3: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsNum: null,
        goodsQuantity: null,
        goodsWarning: null,
        goodsUnit: null,
        goodsAd: null,
        goodsType: null,
        goodsName: null,
      },
      // 表单参数
      form: {
      },
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getUser();
  },
  methods: {
    //低库存改变颜色
    warningStyle(row){
      if(row.column.label==="耗材数量"&& row.row.goodsQuantity < row.row.goodsWarning){
        return 'color:red'
      }
    },

    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
        console.log(this.user);
      });
    },
    /** 查询耗材列表列表 */
    getList() {
      this.loading = true;
      listShowGoods(this.queryParams).then(response => {
        this.showGoodsList = response.rows;
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
        goodsId: null,
        goodsNum: null,
        goodsQuantity: null,
        goodsWarning: null,
        goodsUnit: null,
        goodsAd: null,
        goodsType: "1",
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
      this.ids = selection.map(item => item.goodsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加耗材列表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const goodsId = row.goodsId || this.ids
      getShowGoods(goodsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改耗材列表";
      });
    },

    /** 增加耗材操作*/
    handleAddGoods(row) {
      this.reset();
      const goodsId = row.goodsId || this.ids
      getShowGoods(goodsId).then(response => {
        this.form = response.data;
        this.open2 = true;
        this.title = "耗材入库";
      });
    },

    /** 领用耗材操作*/
    handleTakeGoods(row) {
      this.reset();
      const goodsId = row.goodsId || this.ids
      getShowGoods(goodsId).then(response => {
        this.form = response.data;
        this.open3 = true;
        this.title = "耗材领用";
      });
    },

    /** 提交入库按钮 */
    submitAdd() {
      this.$refs["form"].validate(valid => {
          if (this.form.goodsId != null) {
            console.log(this.form.goodsQuantitys)
            this.form.goodsQuantity = this.form.goodsQuantitys;
            this.formlog = {
              goodsId: this.form.goodsId,
              goodsNum: this.form.goodsNum,
              goodsName: this.form.goodsName,
              goodsAd: this.form.goodsAd,
              goodsPerson: this.user.nickName,
              actionType: "入库",
              actionNum: this.form.goodsQuantitys,
              goodsLogMassage: this.form.goodsLogMassage,
              actionDate: this.getNowFormatDate(),
            }
            addGoods(this.form).then(response => {
              this.msgSuccess("入库成功");
              this.open2 = false;
              this.getList();
            });
            addLog(this.formlog).then(response => {
              console.log("添加日志成功")
            });
          }
      });
    },

    /** 提交领用按钮 */
    submitTake() {
      this.$refs["form"].validate(valid => {
        if (this.form.goodsId != null) {
          this.form.goodsQuantity = -(this.form.goodsQuantitys);
          this.formlog = {
            goodsId: this.form.goodsId,
            goodsNum: this.form.goodsNum,
            goodsName: this.form.goodsName,
            goodsAd: this.form.goodsAd,
            goodsPerson: this.user.nickName,
            actionType: "领用",
            actionNum: this.form.goodsQuantitys,
            goodsLogMassage: this.form.goodsLogMassage,
            actionDate: this.getNowFormatDate(),
          }
          addGoods(this.form).then(response => {
            this.msgSuccess("领用成功");
            console.log(response)
            this.open3 = false;
            this.getList();
            if(response.msg) {
              addLog(this.formlog).then(response => {
                console.log("添加日志成功")
              });
            }
          });
        }
      });
    },


    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.goodsId != null) {
            updateShowGoods(this.form).then(response => {
              this.msgSuccess("修改成功");
              console.log(response);
              this.open = false;
              this.getList();
            });
          } else {
            addShowGoods(this.form).then(response => {
              console.log(this.form.goodsType);
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
      const goodsIds = row.goodsId || this.ids;
      this.$confirm('是否确认删除耗材列表编号为"' + goodsIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delShowGoods(goodsIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有耗材列表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportShowGoods(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },

    getNowFormatDate() {
      var date = new Date();
      var seperator1 = "-";
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      var currentdate = year + seperator1 + month + seperator1 + strDate;
      return currentdate;
    },

  }
};
</script>
