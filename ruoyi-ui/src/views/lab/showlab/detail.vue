<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6" :xs="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>房间信息</span>
          </div>
          <div>
            <div class="text-center">
            </div>
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <svg-icon icon-class="user" />房间编号
                <div class="pull-right">{{this.lab.labNum}}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="phone" />房间位置
                <div class="pull-right">{{this.lab.labAd}}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="email" />房间管理员
                <div class="pull-right">{{this.lab.labAdmin}}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="tree" />房间类型
                <div class="pull-right">{{this.lab.labType}}</div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>设备信息</span>
          </div>
          <el-tabs>
            <div v-for="(item,i) in this.lab.zjutEquipmentList">
              <div v-if="item.equipmentName != null">
              <el-col :span="10" :xs="24" style="margin-left: 50px ; margin-top: 20px">
                <span style="font-size: 18px ; font-weight: 900">设备{{i+1}}</span>
                <li class="list-group-item" style="font-size: 14px">
                  <svg-icon icon-class="user" />设备名称
                  <div class="pull-right">{{item.equipmentName}}</div>
                </li>
                <li class="list-group-item" style="font-size: 14px">
                  <svg-icon icon-class="phone" />设备编号
                  <div class="pull-right">{{item.equipmentModel}}</div>
                </li>
                <li class="list-group-item" style="font-size: 14px">
                  <svg-icon icon-class="email" />设备SN型号
                  <div class="pull-right">{{item.equipmentSnNumber}}</div>
                </li>
                <li class="list-group-item" style="font-size: 14px">
                  <svg-icon icon-class="tree" />设备管理员
                  <div class="pull-right">{{item.equipmentAdmin}}</div>
                </li>
              </el-col>
              </div>
              <div v-else>
                <div>该房间暂无设备</div>
              </div>
            </div>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { listShowlab, getShowlab, delShowlab, addShowlab, updateShowlab, exportShowlab } from "@/api/lab/showlab";

export default {
  name: 'detail',
  data() {
    return {
      //
      lab: [],
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
      // 设备表格数据
      showList: [],
      // 设备预约表格数据
      equipmentSubscribeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {},
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },

  created(){
    const labId = this.$route.params.labId
    console.log(labId)
    getShowlab(labId).then(response => {
      this.lab = response.data;
      if(this.lab.labType == 1){
        this.lab.labType = "实验室"
      }
      if(this.lab.labType == 2){
        this.lab.labType = "会议室"
      }
      console.log(this.lab);
    });
  },

  methods: {

  }
}
</script>

<style scoped>

</style>
