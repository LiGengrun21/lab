<template>
  <div class="app-container">
    <el-row :gutter="20">

      <el-col :span="24" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>设备详情</span>
          </div>
            <el-col :span="8" :xs="24" style="margin-left: 50px">
              <span style="font-size: 18px ; font-weight: 900">设备信息</span>
              <br><br>
              <li class="list-group-item" style="font-size: 14px">
                <svg-icon icon-class="user" />设备名称
                <div class="pull-right">{{this.equipment.equipmentName}}</div>
              </li>
              <li class="list-group-item" style="font-size: 14px">
                <svg-icon icon-class="phone" />设备编号
                <div class="pull-right">{{this.equipment.equipmentModel}}</div>
              </li>
              <li class="list-group-item" style="font-size: 14px">
                <svg-icon icon-class="email" />设备插座
                <div class="pull-right">{{this.equipment.deviceId}}</div>
              </li>
              <li class="list-group-item" style="font-size: 14px">
                <svg-icon icon-class="email" />设备SN型号
                <div class="pull-right">{{this.equipment.equipmentSnNumber}}</div>
              </li>
              <li class="list-group-item" style="font-size: 14px">
                <svg-icon icon-class="tree" />设备管理员ID
                <div class="pull-right">{{this.equipment.userId}}</div>
              </li>
              <li class="list-group-item" style="font-size: 14px">
                <svg-icon icon-class="user" />设备状态
                <div class="pull-right">{{this.equipment.equipmentStatus}}</div>
              </li>
              <li class="list-group-item" style="font-size: 14px">
                <svg-icon icon-class="user" />是否可预约
                <div class="pull-right">{{this.equipment.equipmentSubscribeType}}</div>
              </li>
              <li class="list-group-item" style="font-size: 14px">
                <svg-icon icon-class="user" />设备位于实验室
                <div class="pull-right">{{this.labName}}</div>
              </li>
            </el-col>

            <el-col :span="14" :xs="24" style="margin-left: 50px">
              <div id="myChart" :style="{width: '600px', height: '400px'}"></div>
            </el-col>

          <el-col :span="8" :xs="24" style="margin-left: 50px">
          <span style="font-size: 18px ; font-weight: 900">实验室信息</span>
            <br><br>
          <li class="list-group-item" style="font-size: 14px">
            <svg-icon icon-class="user" />实验室名称
            <div class="pull-right">{{this.labName}}</div>
          </li>
          <li class="list-group-item" style="font-size: 14px">
            <svg-icon icon-class="phone" />实验室位置
            <div class="pull-right">{{this.lab.labAd}}</div>
          </li>
          <li class="list-group-item" style="font-size: 14px">
            <svg-icon icon-class="email" />实验室管理员
            <div class="pull-right">{{this.lab.labAdmin}}</div>
          </li>
            <br><br>
        </el-col>
        <el-col :span="8" :xs="24" style="margin-left: 50px">
          <span style="font-size: 18px ; font-weight: 900 ; margin-left: 20px ">周利用率</span>
          <br><br>
          <el-progress type="circle" :percentage=this.P style="margin-left: 220px"></el-progress>
        </el-col>

        <el-col :span="23" :xs="24">
          <br><br>
          <div id="myChartline" style="width: 100%;height: 70vh;" v-loading="loading"> </div>
          <div style="margin-left: 310px">
            <el-button type="success" @click="handleCurrent" size="mini" >电流</el-button>
            <el-button type="success" @click="handleVoltage" size="mini">电压</el-button>
            <el-button type="success" @click="handlePower" size="mini">功率</el-button>
            <el-button type="primary" @click="handleOneH" size="mini" style="margin-left: 50px">最近一小时</el-button>
            <el-button type="primary" @click="handleThreeH" size="mini">最近三小时</el-button>
            <el-button type="primary" @click="handleTwentyFourH" size="mini">最近二十四小时</el-button>
          </div>
          <div style="margin-left: 550px">
            <br>
            <el-button type="primary" size="mini" disabled="true">按日期查看</el-button>
              <el-date-picker
                v-model="valuedate"
                type="date"
                placeholder="选择日期查看"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                style="margin-left: 5px"
                :picker-options="pickerOptions"
                @change="selectDate">
              </el-date-picker>
          </div>
        </el-col>

        <el-col :span="20" :xs="24" style="margin-left: 50px">
          <br><br><br><br>
          <span style="font-size: 18px ; font-weight: 900 ;">今日预约时间表</span>
          <br><br>
          <el-table :data="subscribesame" ref="equipmentSubscribe">
            <el-table-column label="预约日期" align="center" prop="subscribeDate"></el-table-column>
            <el-table-column label="预约开始时间" align="center" prop="subscribeStartTime"></el-table-column>
            <el-table-column label="预约结束时间" align="center" prop="subscribeEndTime"></el-table-column>
            <el-table-column label="预约人姓名" align="center" prop="subscribeName"></el-table-column>
            <el-table-column label="预约人手机号" align="center" prop="subscribePhone"></el-table-column>
          </el-table>
        </el-col>
          <br><br>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {showThisWeek} from '@/api/lab/datemassage'
import { listShow, getShow, delShow, addShow, updateShow, exportShow } from "@/api/equipment/show";
import { getShowlab, listShowlab } from '@/api/lab/showlab'
import log from '@/views/monitor/job/log'
import {
  list1HoursDevicelog,
  list24HoursDevicelog,
  list3HoursDevicelog,
  listDevicelog, listOneDayDevicelog
} from '@/api/equipment/devicelog'
let echarts = require('echarts/lib/echarts');
//引入柱状图
require('echarts/lib/chart/bar');
//引入饼图
require('echarts/lib/chart/pie');
require("echarts/lib/chart/line")
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');
export default {
  name: 'detail',
  data() {
    return {
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now() ;//如果没有后面的-8.64e7就是不可以选择今天的
        }
      },
      index:0,
      type:0,
      P: null,
      subscribesame: [],
      data: [],
      result: [0, 0, 0, 0, 0, 0, 0],
      subscribeOption: [],
      statusOption: [],
      from: [],
      equipment: [],
      equipmentData: [],
      lab: [],
      labName: null,
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
      // 查询参数
      queryParams: {},
      // 表单参数
      form: {},
      formDeviceLog: {},
      equipmentDeciveLog: [],
      aDayDeviceLog: [],
      // 表单校验
      rules: {},
      timer: '',
      valuedate: '',
    };
  },

  created() {
    this.getEquipment()
  },

  mounted() {
    this.setData()
    this.setChartline()
    this.timer = setInterval(this.setChartline, 5000)
  },

  methods: {
    selectDate(value){
      this.index = 3;
      this.loading = true;
      this.valuedate = value;
      console.log(this.valuedate);
    },

    getEquipment() {
      const equipmentId = this.$route.params.equipmentId
      console.log(equipmentId)
      getShow(equipmentId).then(response => {
        this.equipment = response.data;
        if (this.equipment.equipmentStatus == 0) {
          this.equipment.equipmentStatus = "正常"
        }
        if (this.equipment.equipmentStatus == 1) {
          this.equipment.equipmentStatus = "保修"
        }
        if (this.equipment.equipmentStatus == 2) {
          this.equipment.equipmentStatus = "报废"
        }
        if (this.equipment.equipmentSubscribeType == 0) {
          this.equipment.equipmentSubscribeType = "可预约"
        }
        if (this.equipment.equipmentSubscribeType == 1) {
          this.equipment.equipmentSubscribeType = "不可预约"
        }
        getShowlab(this.equipment.labId).then(response => {
          this.lab = response.data;
          this.labName = response.data.labNum;
        });
      });
    },

    setData() {
      const equipmentId = this.$route.params.equipmentId
      showThisWeek().then(responce => {
        console.log(responce.data)
        this.data = responce.data;

        getShow(equipmentId).then(response => {
          var date = new Date();
          this.equipmentData = response.data;
          for (var i = 0; i < this.equipmentData.equipmentSubscribeList.length; i++) {
            for (var j = 0; j < 7; j++) {
              if (date.getFullYear() + "-" + this.data[j] == this.equipmentData.equipmentSubscribeList[i].subscribeDate) {
                this.result[j]++;
              }
            }
          }
        }).then(() => {

          var date = new Date();
          this.subscribesame = [];
          for (var i = 0, j = 0; i < this.equipmentData.equipmentSubscribeList.length; i++) {
            var month = date.getMonth() + 1;
            if (month >= 0 && month <= 9) {
              month = "0" + month;
            }
            var day = date.getDate();
            if (day >= 0 && day <= 9) {
              day = "0" + day;
            }
            if (this.equipmentData.equipmentSubscribeList[i].subscribeDate == date.getFullYear() + "-" + month + "-" + day) {
              this.subscribesame[j] = this.equipmentData.equipmentSubscribeList[i];
              j++;
            }
          }

          var flag = 0;
          for (var i = 0; i < 7; i++) {
            if (this.result[i] != 0) {
              flag++;
            }
          }
          console.log("flag" + flag)
          this.P = Math.round((flag / 7) * 100);
          this.drawLine();
        })
      });
    },

    setChartline() {
      const equipmentId = this.$route.params.equipmentId
      var equipmentCurrentLog = new Array();
      var equipmentVoltageLog = new Array();
      var equipmentPowerLog = new Array();
      getShow(equipmentId).then(response =>{
        var equipment = response.data;
        if(this.index == 0) {
          list1HoursDevicelog().then(response => {
            this.formDeviceLog = response.rows;
            for (var i = 0, j = 0; i < this.formDeviceLog.length; i++) {
              if (this.formDeviceLog[i].deviceId == equipment.deviceId) {
                equipmentCurrentLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].deviceCurrent)
                  ]
                }
                equipmentVoltageLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].deviceVoltage)
                  ]
                }
                equipmentPowerLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].devicePower)
                  ]
                }
                j++;
              }
            }
            if(this.type == 0){
              this.drawChartLine(equipmentCurrentLog,"电流","电流监控");
              this.loading = false;
            }
            else if(this.type == 1){
              this.drawChartLine(equipmentVoltageLog,"电压","电压监控");
              this.loading = false;
            }
            else if(this.type == 2){
              this.drawChartLine(equipmentPowerLog,"功率","功率监控");
              this.loading = false;
            }
          });
        }
        else if(this.index == 1) {
          list3HoursDevicelog().then(response => {
            this.formDeviceLog = response.rows;
            for (var i = 0, j = 0; i < this.formDeviceLog.length; i++) {
              if (this.formDeviceLog[i].deviceId == equipment.deviceId) {
                equipmentCurrentLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].deviceCurrent)
                  ]
                }
                equipmentVoltageLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].deviceVoltage)
                  ]
                }
                equipmentPowerLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].devicePower)
                  ]
                }
                j++;
              }
            }
            if(this.type == 0){
              this.drawChartLine(equipmentCurrentLog,"电流","电流监控");
              this.loading = false;
            }
            else if(this.type == 1){
              this.drawChartLine(equipmentVoltageLog,"电压","电压监控");
              this.loading = false;
            }
            else if(this.type == 2){
              this.drawChartLine(equipmentPowerLog,"功率","功率监控");
              this.loading = false;
            }
          });
        }
        else if(this.index == 2) {
          list24HoursDevicelog().then(response => {
            this.formDeviceLog = response.rows;
            for (var i = 0, j = 0; i < this.formDeviceLog.length; i++) {
              if (this.formDeviceLog[i].deviceId == equipment.deviceId) {
                equipmentCurrentLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].deviceCurrent)
                  ]
                }
                equipmentVoltageLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].deviceVoltage)
                  ]
                }
                equipmentPowerLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].devicePower)
                  ]
                }
                j++;
              }
            }
            if(this.type == 0){
              this.drawChartLine(equipmentCurrentLog,"电流","电流监控");
              this.loading = false;
            }
            else if(this.type == 1){
              this.drawChartLine(equipmentVoltageLog,"电压","电压监控");
              this.loading = false;
            }
            else if(this.type == 2){
              this.drawChartLine(equipmentPowerLog,"功率","功率监控");
              this.loading = false;
            }
          });
        }
        else if(this.index == 3) {
          listOneDayDevicelog(this.queryParams,this.valuedate).then(response=>{
            this.formDeviceLog = response.rows;
            for (var i = 0, j = 0; i < this.formDeviceLog.length; i++) {
              if (this.formDeviceLog[i].deviceId == equipment.deviceId) {
                equipmentCurrentLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].deviceCurrent)
                  ]
                }
                equipmentVoltageLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].deviceVoltage)
                  ]
                }
                equipmentPowerLog[j] = {
                  name: this.formDeviceLog[i].logTime,
                  value: [
                    this.formDeviceLog[i].logTime,
                    parseInt(this.formDeviceLog[i].devicePower)
                  ]
                }
                j++;
              }
            }
            if(this.type == 0){
              this.drawChartLine(equipmentCurrentLog,"电流","电流监控");
              this.loading = false;
            }
            else if(this.type == 1){
              this.drawChartLine(equipmentVoltageLog,"电压","电压监控");
              this.loading = false;
            }
            else if(this.type == 2){
              this.drawChartLine(equipmentPowerLog,"功率","功率监控");
              this.loading = false;
            }
          });
        }
      });
    },

    handleOneH(){
      this.index = 0;
      this.loading = true;
    },

    handleThreeH(){
      this.index = 1;
      this.loading = true;
    },

    handleTwentyFourH(){
      this.index = 2;
      this.loading = true;
    },

    handleCurrent(){
      this.type = 0;
      this.loading = true;
    },

    handleVoltage(){
      this.type = 1;
      this.loading = true;
    },

    handlePower(){
      this.type = 2;
      this.loading = true;
    },

    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(document.getElementById('myChart'))
      myChart.setOption({
        title: { text: '本周预约情况' },
        tooltip: {},
        color: ['#00c3e5'],
        xAxis: {
          data: this.data,
        },
        yAxis: {
          minInterval: 1
        },
        series: [{
          name: '次数',
          type: 'bar',
          data: this.result,
        }]
      });
    },

    drawChartLine(equipmentDeciveLog,s,t) {
      let myChartline = echarts.init(document.getElementById('myChartline'))
      myChartline.setOption({
        tooltip: {              //设置tip提示
          trigger: 'axis'
        },
        title: { text: t },

        xAxis: {
          type: 'time',
          boundaryGap: true, //控制日期是否在中间显示
          axisLabel: {
            show: true, //是否显示日期
            interval: 0, //强制显示全部 // rotate: 40,//倾斜的角度
            textStyle: {
              color: '#000', //日期的颜色
              fontSize: 12 //字体的大小
            }
          },
          axisLine: {
            lineStyle: {
              color: '#ccc' // x轴的颜色
            }
          },
          triggerEvent : true
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value}',
            textStyle: {
              color: '#000' //数字的颜色
            },
            inside: false //控制数据是否在内侧还是外侧显示
          },
          axisLine: {
            lineStyle: {
              color: '#ccc' // 折线的颜色
            }
          }
        },
        series: [
          {
            // data: this.dataY,
            name: s,
            symbol: 'none',
            data: equipmentDeciveLog,
            type: 'line',
            lineStyle: { color: '#839eef'}
          },
        ]
      });
    },
  },
  beforeDestroy() {
    clearInterval(this.timer);
  }
}
</script>

<style scoped>

</style>
