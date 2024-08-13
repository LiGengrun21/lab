<!--
    成员日常信息查看
-->
<template>
    <div class="page">
        <!--
            成员筛选
        -->
        <div class="fiter">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="用户名称" prop="userName">
            <el-input
              v-model="queryParams.userName"
              placeholder="请输入用户名称"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select
              v-model="queryParams.status"
              placeholder="用户状态"
              clearable
              size="small"
              style="width: 240px"
            >
              <el-option
                v-for="dict in statusOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="创建时间" style="margin-left:30px">
            <el-date-picker
              v-model="dateRange"
              size="small"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
        </div>
        <!--
            功能按键
        -->
        <div class="fun_tab">

        </div>
        <!--
            表格
        -->
        <div class="ex_body">

        </div>
    </div>
    
</template>

<script>
export default {
    data(){
        return{
             // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 20,
                userName: undefined,
                phonenumber: undefined,
                status: undefined,
                deptId: undefined
            },


        }
    }
}

</script>

<style scoped>

</style>
