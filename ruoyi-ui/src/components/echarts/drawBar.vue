<!--月统计数据 折线图-->
<template>
  <div class="Bar">
    <div class="bar" id="month_echart"></div>
  </div>
</template>
<script>

let echarts = require('echarts/lib/echarts');
// 引入柱状图
require('echarts/lib/chart/bar');
// 引入饼图
require('echarts/lib/chart/pie');
require("echarts/lib/chart/line")
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');
export default {
    name:"MonthBar",
    data(){
        return{
            
        }
    },
    mounted(){
        this.drawBar()
    },
    methods:{
        drawBar(){
        // 基于dom，初始化echarts实例
     let data=[];
      for(var i=0;i<30;i++){
        data.push(Math.floor(Math.random()*1000+20))
      }
      let option = {
          title : {
              text: '（全组或者某成员）月设备使用费用',
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
    }
}
</script>