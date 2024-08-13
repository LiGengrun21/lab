<template>
    <div class="page">
        <div class="from">
            <el-form :date="CostTable" label-width="100" :inline="true">
                <el-form-item  label="费用类型">
                   <el-select v-model="form.costType">
                       <el-option v-for="(item,index) in CostType" :key="index" :value="item">
                       </el-option>
                   </el-select>
                </el-form-item>
                <el-form-item  label="时间类型">
                    <el-select
                        class="inline-input"
                        v-model="form.timetype"
                    >
                        <el-option v-for="(item,index) in timeTypes" :key="index" :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item  label="时间段">
                    <el-date-picker
                        v-model="form.timeQue"
                        align="right"
                        :type="typetype[this.form.timetype]"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        placeholder="选择日期"
                        value-format="yyyy-MM-dd"
                        >
                    </el-date-picker>
                </el-form-item>
                <el-row>
                <el-form-item  label="成员筛选">
                    <el-select v-model="form.PI"
                        class="inline-input"
                        :fetch-suggestions="querySearch"
                        placeholder="请输入内容"
                        @select="handleSelect"

                        >
                        <el-option v-for="(item,index) in PIS" :key="index" :value="item.label" :label="item.value">
                        </el-option></el-select>
                </el-form-item>
                <el-form-item>
                <el-button type="info" class="el-icon-refresh-left" style="width:110px;" @click="reset">重置</el-button>
                <el-button type="primary"   class="el-icon-search"  style="width:110px;" @click="search">查询</el-button>
                </el-form-item>
                </el-row>
            </el-form>
        </div>
    <div class="circle" >
        <div>
        <div class="box1" id="cicle" />
        <div class="details">
            <div class="detailsItem" v-for="(item,index) in  Piedetails" :key="index" >
                <el-button type="primary">{{item.value}}</el-button>
                <h2>{{item.name}}</h2>
            </div>
        </div>

        </div>

        <div class="right_table">
            <h3>设备总表数据</h3>
             <el-table
                :data="tableData"
                :header-cell-style="{color:'#000000','text-align': 'center'}"
                style="width: 100%">
                <el-table-column
                label="成员名字"
                align="center"
                >
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.name }}</span>
                </template>
                </el-table-column >
                <el-table-column align="center"
                label="PI组"
                prop="name">
                </el-table-column>
                 <el-table-column
                label="设备使用费用"

                sortable
                align="center"
                prop="equipmentMoney">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                    <div slot="reference" class="name-wrapper" style="disply:flex">
                        <el-tag style="height:30px;width:50px;">{{scope.row.equipmentMoney}}</el-tag>
                    </div>
                    </el-popover>
                </template>
                </el-table-column>
                 <el-table-column
                label="设备使用费用"

                sortable
                align="center"
                prop="materialMoney">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                    <div slot="reference" class="name-wrapper" style="disply:flex">
                        <el-tag style="height:30px;width:50px;">{{scope.row.materialMoney}}</el-tag>
                    </div>
                    </el-popover>
                </template>
                </el-table-column>

                <el-table-column
                label="费用总计"
                width="100"
                sortable
                align="center"
                prop="totalMoney">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                    <div slot="reference" class="name-wrapper" style="disply:flex">
                        <el-tag style="height:30px;width:50px;">{{scope.row.totalMoney}}</el-tag>
                    </div>
                    </el-popover>
                </template>
                </el-table-column>
                <el-table-column
                label="操作"
                align="center"

                >
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="viewDetail(scope.row)"
                    >圆环图查看</el-button>
                </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
    <div class="down">
        <div class="pies">
            <div class="Pie" id="pie1">
        </div>
        <div class="Pie" id="pie2">
        </div>
        </div>
        <div class="echarts1" id="month_echart" >
        </div>
    </div>
    <div class="down2">
        <div class="search">
            <el-form :date="bardate" :inline="false" label-position="top" >
                <el-form-item label="时间间隔" >
                    <el-select v-model="bardate.month">
                        <el-option v-for="(item,index) in timeTypes" :key="index" :value="item" >
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item  label="时间段">
                    <el-date-picker
                        v-model="form.timeQue"
                        align="right"
                        :type="typetype[bardate.month]"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        placeholder="日期"
                        value-format="yyyy-MM-dd"
                        >
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" style="width:110px;margin-top:10px;border:none">排序</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="bar2" id="bar2">
         </div>
    </div>
    <div class="down2">
        <div class="search">
            <el-form :date="bardate" :inline="false" label-position="top" >
                <el-form-item label="人员选择" >
                    <el-select v-model="bardate.month">
                        <el-option v-for="(item,index) in date" :key="index" :value="item" >
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="时间间隔" >
                    <el-select v-model="bardate.month">
                        <el-option v-for="(item,index) in date" :key="index" :value="item" >
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="时间段选择" align="center">
                    <el-select v-model="bardate.date">
                        <el-option v-for="(item,index) in date2" :key="index" :value="item" >
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" style="width:110px;margin-top:10px;">排序</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="bar2" id="main">
         </div>
    </div>


    </div>
</template>
<script>
import  {getCostType,getpiTotal,getSubscribesByPiUni} from '@/api/equipment/cost'
import {GetAAllPis2} from "@/api/system/user"
import getCurrentMonthLast from "@/utils/Publicfun"
let echarts = require('echarts/lib/echarts');
// 引入柱状图
require('echarts/lib/chart/bar');
// 引入饼图
require('echarts/lib/chart/pie');
require("echarts/lib/chart/line")
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');
export default {
    data(){
        return{
            //消费类型
            CostList:[],
            //按钮数据
            //搜素表单
            form:{
                timetype:"day"
            },
            PIS:[],
            //消费类型
            CostType:["equipmentMoney","materialMoney","artificialMoney"],
            //日期类型
            timeTypes:["day","month","year"],
            //对应的日期type
            typetype:{day:"daterange",month:"month",year:"year"},
            //选择的时间段
            timeques:[],
            PIDetails:[
                {PI:"POSTMAN",monthValue:"564",}
            ],
            bardate:{},

            detailsdate:[
                {
                    name:"类型一",value:128
                },
                {
                    name:"类型二",value:127
                },
                {
                    name:"类型三",value:125
                },
            ],
            activeIndex:"",
             piedate1:[
                {value: 1048, name: '类型一'},
                {value: 735, name: '类型二'},
                {value: 580, name: '类型三'},
                {value: 484, name: '类型四'},
                {value: 300, name: '类型五'}
            ],
            piedate2:[
                {value: 1578, name: '成员一'},
                {value: 715, name: '成员二'},
                {value: 890, name: '成员三'},
                {value: 254, name: '成员四'},
                {value: 320, name: '成员五'}
            ],
            color:[
                    "#99CCFF",
                    "#66CCFF",
                    "#CCFFFF",
                    "#6699CC",
                    "#99CCFF",
                    "#66CCCC"
                    ],
            color2:[
        " #5df2fd",
        "#fdbd5d",
        "#42f88e",
        " #42a0f8",
        "#f86c42"
         ],
         date:[],
         date2:[],
         //折线图数据集
         Linedate:[],
        tableData:[],
        weekdate:{},
        weekdatelabel:[],
        //圆环图数据
        Piedetails:[
            {name:"设备使用费",value:170},
            {name:"服务费",value:150},
            {name:"人工费",value:160}
        ]
        }
    },
    mounted(){
        this.GetCost()
        this.GerAllPi()
        //获取设备的收费类型

        this.drawBar();
        this.drawPie("pie1")
        this.drawfix()
        this. drawcilcle()
    },
    watch:{

    },
    methods:{
    getCurrentMonthLast(date){
    var endDate = new Date(date); //date 是需要传递的时间如：2018-08
    var month=endDate.getMonth();
    var nextMonth=++month;
    var nextMonthFirstDay=new Date(endDate.getFullYear(),nextMonth,1);
    var oneDay=1000*60*60*24;
    var dateString=new Date(nextMonthFirstDay-oneDay);
    var json_date = dateString.toJSON();
    return new Date(new Date(json_date) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')

    },
    /**环形图的展示 */
    viewDetail(row){
        this.Piedetails[0].value=row.equipmentMoney
        this.Piedetails[1].value=row.materialMoney
        this.Piedetails[2].value= row.artificialMoney
        this.drawcilcle()
    },
    /**搜索触发按键 */
    async search(){
        //pi组时间的选择 一位时间段
        console.log(this.form.timetype)
        if(this.form.timetype==="day")
        {
            const beginDate=this.from.timeQue[0]
            const endDate=this.form.timeQue[1]
            this.getpiDaysTotal({piId:this.from.pi,beginDate:beginDate,endDate:endDate})
        }
        else{
            const beginDate=this.form.timeQue
            const endDate=this.getCurrentMonthLast(beginDate)
            console.log({piId:this.form.pi,beginDate:beginDate,endDate:endDate})
            this.getpiMonthTotal({piId:1,beginDate:beginDate,endDate:endDate})
        }
    },

    /**重置搜索框 */
    reset(){
        this.form={}
    },
    //获取所有PI的多日的消费信息
        async GetCost(){
        const res=await getSubscribesByPiUni({beginDate:'2021-06-01',endDate:"2021-08-28"})
        console.log(res.data)
        this.tableData=res.data
        this. piedate2=this.tableData.map(function(item,index){

            return {value:item.totalMoney,name:item.name}
        })
        this.drawPie2("pie2")
        this.drawdweek()

        },
    /**获取pi组月数据并展示 */
    getpiMonthTotal(resForm){
        console.log(resForm)
        getpiTotal(resForm).then(res=>{
            this.Linedate=res.data
            this.drawBar()
        })
    },

    //获取Pi组内多日的数据
    getpiDaysTotal(resForm){
            getpiTotal(resForm).then(res=>{
            this.Linedate=res.data
            for(var i=0;i <= this.Linedate.length;i++)
            {
                categet=toString(i+1)
            }
            console.log(categet)
        })
    },


    //获取所有的PI
    GerAllPi(){
        GetAAllPis2().then(res=>{
            this.PIS=res.data.map(function(item){
                return {label:item.userId,value:item.nickName}
            })

        })

    },
    //获取费用类型
    GetCostType(){
            getCostType({equipmentId:1}).then(res=>{
                this.CostType=res.data
            })
    },


 //圆环图
    drawcilcle(){
            var chartDom = document.getElementById('cicle');
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                color:[
                    "#5B8FF9",
                    "#085EC0",
                    "#0047A5",
                    "#00318A",
                    "#00318A",

                    ],
                tooltip: {
                    trigger: 'item'
                },
               title:{
                    text:"PI组的数据"
               },
                legend: {
                    top: '5%',
                    left: 'center'
                },
                series: [
                    {
                        name: '收费分布',
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '40',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: this.Piedetails
                    }
                ]
            };

            option && myChart.setOption(option);
    },
    //画图
    drawBar(){
        // 基于dom，初始化echarts实例
     let data=this.Linedate;


           let option = {
               title : {
                   text: this.form.PI+'组月设备使用费用',

               },
               tooltip : {
                   trigger: 'axis',
                   backgroundColor:' rgba(23, 99, 143, 0.75)' ,
                   padding:[5,10],
                   formatter(params){
                    let point=params[0];

                    return '2021年'+point.name+'月 : '+point.value
                   }
               },
               legend: {
                   data:['2021'],
                   textStyle: {
                     color: '#7aaab8',
                     fontSize:14
                   },

               },

               calculable : true,

               xAxis : [
                   {
                     name : '(月)',
                     nameLocation: 'end',
                     nameTextStyle:{
                     color: '#292929',
                     fontSize:16,
                     padding:[50, 0, 0, 10]
                    },
                       type : 'category',
                       boundaryGap : false,
                       data : ['1','2','3','4','5','6','7','8','9','10','11','12',"13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","29","30"],
                       axisLine: {
                         show: false
                       },
                       axisTick: {
                           show: false
                       },
                       offset:10,
                       axisLabel: {
                           show:true,
                           textStyle: {
                             color: '#292929',
                             fontSize:16
                           }
                       },
                       axisPointer:{ //基准线
                         lineStyle:{
                          color:'rgb(46, 148, 245)'
                        }
                       }

                   }
               ],

               yAxis : [
                   {
                     axisLabel: {
                       show:true,
                       textStyle: {
                         color: '#292929',
                         fontSize:16
                       }
                    },
                    axisTick: {
                       show: false
                   },
                   axisLine: {
                       show: false
                   },
                   offset:10,
                   splitLine: {
                     show: true,
                     lineStyle:{
                         type:'dashed',
                         color: [' #292929b9']

                     }
                   }
                 }
               ],
               series : [
                   {
                       name:'2021',
                       type:'line',
                       smooth:true,
                       symbolSize: 10,   //设定实心点的大小
                       lineStyle:{
                         color:'#6699FF' //改变折线颜色
                       },
                       lineWidth:2,
                       itemStyle: {
                         normal: {
                            areaStyle: {type: 'default'},
                            color: new echarts.graphic.LinearGradient(
                                 0, 0, 0, 1,
                                 [

                                     {offset: 0, color: "#3D76DD"},
                                     {offset: 0.5, color: "#7DAAFF"},
                                     {offset: 1, color: "#9AC5FF"}
                                 ]
                             ),
                            borderColor:'#1d9aee',//拐点边框颜色
                            borderWidth:2//拐点边框大小
                         }},
                        data:data
                   },

               ]
           };
           let myChart = echarts.init(document.getElementById('month_echart'));
           myChart.setOption(option);
    },
    //横行柱形图
    drawdweek(){
      var dom = document.getElementById("bar2");
      var myChart1 = echarts.init(dom);
      var app = {};
      option1 = null;
       //初始化数据
          var category = this.piedate2.map(function(item){
                return  item.name
          });
          var barData = this.piedate2.map(function(item){
                return  item.value
          });

          var option1 = {
              title: {
                  text: '不同成员的设备费用对比',
                      textStyle: {
                          color: '#03a9f4'
                      }
              },
                      legend: {
                          data:['用户数']
                      },
              tooltip: {
                  trigger: 'axis',
                  axisPointer: {
                      type: 'shadow'
                  }
              },
              grid: {
                  left: '3%',
                  right: '16%',
                  bottom: '3%',
                  containLabel: true
              },
              xAxis: {
                  type: 'value',
                  axisLine: {
                      show: false,
                      lineStyle: {
                          color: "rgb(54, 54, 54)"
                      }
                  },
                  axisLabel: {
                      rotate:45,      //倾斜度 -90 至 90 默认为0
                      show: false
                  },
                  axisTick: {
                      show: false
                  },
                   splitLine: {           // 分隔线
                      show: false,        // 默认显示，属性show控制显示与否
                  },
              },


              yAxis: {
                  type: 'category',
                  data: category,

                  splitLine: {           // 分隔线
                      show: false,        // 默认显示，属性show控制显示与否
                  },
                  axisLine: {
                      show: false,
                      lineStyle: {
                          color: "rgb(54, 54, 54)"
                      }
                  },
                  axisTick: {
                      show: false
                  },
                  offset: 10,
              },
              series: [
                  {
                      name: '数量',
                      type: 'bar',
                      data: barData,
                      barWidth: 14,
                      // barGap: 10,
                      // smooth: true,
                      label: {
                          normal: {
                              show: true,
                              position: 'right',
                              offset: [5, -2],
                              textStyle: {
                                  color: '#F68300',
                                  fontSize: 13
                              }
                          }
                      },
                      itemStyle: {
                          emphasis: {
                              barBorderRadius: 7
                          },
                          normal: {
                              barBorderRadius: 7,
                              color: function(params){
                                  var colorList = [
                                      ['#3977E6','#37BBF8'],
                                      ['#E8576C','#661C5A']
                                  ]
                                  for(let i=0;i<barData.length;i++){
                                      if(barData[params.dataIndex] <= 3000){
                                          return new echarts.graphic.LinearGradient(
                                              0, 0, 1, 0,
                                              [
                                                  {offset: 0, color: '#99CCFF'},
                                                  {offset: 1, color: '#6699CC'}
                                              ])
                                      }else{
                                          return new echarts.graphic.LinearGradient(
                                              0, 0, 1, 0,
                                              [
                                                   {offset: 0, color: '#99CCFF'},
                                                  {offset: 0.5, color: '#6699CC'},
                                                  {offset: 1, color: '#336699'}

                                              ])
                                      }
                                  }
                              }
                          },
                      },
                  },

              ]
          };

      if (option1 && typeof option1 === "object") {
          myChart1.setOption(option1, true);
      }
    },
    //画圆饼图
    drawPie(id){
            var chartDom = document.getElementById(id);
            console.log( document.getElementById('pie1'))
            var myChart = echarts.init(chartDom);
            var option;
            var date= this.piedate1
            option = {
                 color:[
                    "#66CCCC",
                    "#CCFF66",
                    "#FF99CC",
                    "#666699",
                    "#FF9999",
                    "#FF9900",
                    "#FFFF66"
                    ],
                title: {
                    text: '不同类型的设备花费占比',
                    subtext: '不同类型的设备花费',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                },
                series: [
                    {
                        name: '本类型花费',
                        type: 'pie',
                        radius: '50%',
                        data: date,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
     if (option && typeof option === "object") {
          myChart.setOption(option);
      }


    },
    drawPie2(id){
            var chartDom = document.getElementById(id);
            console.log( document.getElementById('pie2'))
            var myChart = echarts.init(chartDom);
            var option;
            var date= this.piedate2
            option = {
                 color:[
                    "#B8E1FF",
                    "#9AC5FF","#3D76DD",
                    "#0047A5",
                    "#5B8FF9",
                    "#3D76DD",

                    ],
                title: {
                    text: '不同成员的设备花费占比',
                    subtext: '不同成员的设备花费',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                },
                series: [
                    {
                        name: '成员花费',
                        type: 'pie',
                        radius: '50%',
                        data: date,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
     if (option && typeof option === "object") {
          myChart.setOption(option);
      }


    },
    //联动图
    drawfix(){
      var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;

        setTimeout(function () {

            option = {
                 title: {
                    text: '（成员——PI）不同类型的设备花费',
                    subtext: '具体数据',
                    left: 'center'
                },
                 color:[
                  "#5B8FF9",
                    "#61DDAA",
                    "#65789B",
                   "#F6903D",
                    "#7262fd",
                    "#78D3F8",
                    ],
                legend: {},
                tooltip: {
                    trigger: 'axis',
                    showContent: false
                },
                dataset: {
                    source: [
                        ['事件', '2012', '2013', '2014', '2015', '2016', '2017',"2018","2019","2020","2021","2022","2023"],
                        ['类型一', 56.5, 82.1, 88.7, 70.1, 53.4, 85.1,56.5, 82.1, 88.7, 70.1, 53.4, 85.1],
                        ['类型二', 51.1, 51.4, 55.1, 53.3, 73.8, 68.7,51.1, 51.4, 55.1, 53.3, 73.8, 68.7],
                        ['类型三', 40.1, 62.2, 69.5, 36.4, 45.2, 32.5,40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
                        ['类型四', 25.2, 37.1, 41.2, 18, 33.9, 49.1,25.2, 37.1, 41.2, 18, 33.9, 49.1]
                    ]
                },
                xAxis: {type: 'category'},
                yAxis: {gridIndex: 0},
                grid: {top: '55%'},
                series: [
                    {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
                    {
                        type: 'pie',
                        id: 'pie',
                        radius: '30%',
                        center: ['50%', '25%'],
                        emphasis: {focus: 'data'},
                        label: {
                            formatter: '{b}: {@2012} ({d}%)'
                        },
                        encode: {
                            itemName: 'product',
                            value: '2012',
                            tooltip: '2012'
                        }
                    }
                ]
            };

            myChart.on('updateAxisPointer', function (event) {
                var xAxisInfo = event.axesInfo[0];
                if (xAxisInfo) {
                    var dimension = xAxisInfo.value + 1;
                    myChart.setOption({
                        series: {
                            id: 'pie',
                            label: {
                                formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                            },
                            encode: {
                                value: dimension,
                                tooltip: dimension
                            }
                        }
                    });
                }
            });

            myChart.setOption(option);

        });

        option && myChart.setOption(option);

    },
    //匹配函数
    querySearch(queryString, cb) {
        var PIS = this.PIS;
        var results = queryString ?PIS.filter(this.createFilter(queryString)) : PIS;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
    createFilter(queryString) {
        return (PIS) => {
          return (PIS.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      handleSelect(item) {
        console.log(item);
      }
    }
}
</script>
<style scoped>
    .page{
        padding: 20px;
        background: #e2e2e288;
    }
    .from{
        display: flex;
        padding: 20px;
       box-shadow: 5px 5px 5px 5px rgba(211, 211, 211, 0.411);
        background: #fff;

    }

    .down{
        display: flex;
        flex-direction: column;
        padding: 10px;
        margin-top:30px;
        background: #ffffff;
         border-radius: 10px;
        box-shadow: 5px 5px 5px 5px rgba(211, 211, 211, 0.411);
    }
    .echarts1{
        width: 100%;
        height: 500px;
        padding: 30px;
        margin-top: 20px;
        border-radius: 8px;
        background:#fff;

    }
    .bar2{
        width: 100%;
        height: 500px;
        padding: 30px;
        border-radius: 10px;
        background:#fff;
        margin-left: 0px;
    }
    .down2{
        display: flex;
        margin-top: 10px; height: 500px;
    }
    .search{
        width: 200px;

        background:#fff;
    }

    .search  .el-form-item  .el-button{
       margin-left: -20px;
   }
   .search  .el-form-item  .el-select{
       width: 150px;
       margin-left: -20px;
   }
   .search  .el-form-item .el-input{
       width: 150px;
       margin-left: -20px;;
   }

    .pies{
        display: flex;
    }
    .Pie{
        width: 600px;
         padding: 40px;

        border-radius: 10px;
        background:#fff;
        height: 500px;
    }
    .el-form-item{
        margin-left: 40px;

    }
   .el-form-item  .el-button{
       margin-left: 5px;
   }

    .circle{
        display: flex;
        height: 550px;
        width: 100%;
        margin-left: 0px;
        background: #fff;
        margin-top: 20px;
        border-radius: 10px;
        box-shadow: 5px 5px 5px 5px rgba(211, 211, 211, 0.411);
    }
    .box1{
        width: 350px;
        height: 350px;
        margin-left: 50px;
        margin-top: 50px;

    }
    .details{
        display: flex;

    }
    .detailsItem{
            margin-left: 40px;
            text-align: center;
        }
    .detailsItem .el-button{
        width: 100px;
        height: 50px;
    }
    .right_table{
        width: 60%;
        margin-left: 50px;
    }

</style>
